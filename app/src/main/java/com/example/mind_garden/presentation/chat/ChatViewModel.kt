package com.example.mind_garden.presentation.chat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mind_garden.domain.model.ChatMessage
import com.example.mind_garden.domain.repository.ChatRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val chatRepository: ChatRepository
) : ViewModel() {

    private val _chatState = MutableStateFlow(ChatState())
    val chatState: StateFlow<ChatState> = _chatState.asStateFlow()

    // Temporary user ID - will be replaced with actual authentication
    private val currentUserId = "temp_user_001"

    init {
        loadChatHistory()
    }

    private fun loadChatHistory() {
        viewModelScope.launch {
            chatRepository.getRecentChats(currentUserId, 50)
                .catch { e ->
                    _chatState.update { it.copy(
                        error = "Failed to load chat history: ${e.message}"
                    )}
                }
                .collect { messages ->
                    _chatState.update { it.copy(
                        chatHistory = messages
                    )}
                }
        }
    }

    fun sendMessage(
        message: String,
        deepResearch: Boolean = false,
        extendedThinking: Boolean = false
    ) {
        if (message.isBlank()) return

        viewModelScope.launch {
            _chatState.update { it.copy(
                isLoading = true,
                error = null
            )}

            try {
                // Save user message
                val userMessage = ChatMessage(
                    userId = currentUserId,
                    message = message,
                    response = "",
                    isUserMessage = true
                )
                chatRepository.insertMessage(userMessage)

                // Get AI response
                val aiResponse = chatRepository.sendMessageToAI(
                    message = message,
                    deepResearch = deepResearch,
                    extendedThinking = extendedThinking
                )

                // Save AI response
                val aiMessage = ChatMessage(
                    userId = currentUserId,
                    message = message,
                    response = aiResponse,
                    isUserMessage = false
                )
                chatRepository.insertMessage(aiMessage)

                _chatState.update { it.copy(
                    isLoading = false,
                    lastAIResponse = aiResponse
                )}

            } catch (e: Exception) {
                _chatState.update { it.copy(
                    isLoading = false,
                    error = "Failed to send message: ${e.message}"
                )}
            }
        }
    }

    fun clearError() {
        _chatState.update { it.copy(error = null) }
    }

    fun clearChatHistory() {
        viewModelScope.launch {
            try {
                chatRepository.clearUserChatHistory(currentUserId)
                _chatState.update { it.copy(
                    chatHistory = emptyList()
                )}
            } catch (e: Exception) {
                _chatState.update { it.copy(
                    error = "Failed to clear chat history: ${e.message}"
                )}
            }
        }
    }
}

data class ChatState(
    val chatHistory: List<ChatMessage> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val lastAIResponse: String? = null
)
