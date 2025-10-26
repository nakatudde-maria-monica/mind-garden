package com.example.mind_garden.domain.repository

import com.example.mind_garden.domain.model.ChatMessage
import kotlinx.coroutines.flow.Flow

interface ChatRepository {
    fun getUserChatHistory(userId: String): Flow<List<ChatMessage>>
    fun getRecentChats(userId: String, limit: Int = 20): Flow<List<ChatMessage>>
    suspend fun insertMessage(message: ChatMessage)
    suspend fun clearUserChatHistory(userId: String)
    suspend fun deleteMessage(message: ChatMessage)
    suspend fun sendMessageToAI(message: String, deepResearch: Boolean, extendedThinking: Boolean): String
}
