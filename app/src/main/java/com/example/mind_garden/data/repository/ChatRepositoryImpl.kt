package com.example.mind_garden.data.repository

import com.example.mind_garden.data.local.dao.ChatMessageDao
import com.example.mind_garden.data.local.entity.ChatMessageEntity
import com.example.mind_garden.domain.model.ChatMessage
import com.example.mind_garden.domain.repository.ChatRepository
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.generationConfig
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ChatRepositoryImpl @Inject constructor(
    private val chatMessageDao: ChatMessageDao,
    private val generativeModel: GenerativeModel
) : ChatRepository {

    override fun getUserChatHistory(userId: String): Flow<List<ChatMessage>> {
        return chatMessageDao.getUserChatHistory(userId).map { entities ->
            entities.map { it.toDomainModel() }
        }
    }

    override fun getRecentChats(userId: String, limit: Int): Flow<List<ChatMessage>> {
        return chatMessageDao.getRecentChats(userId, limit).map { entities ->
            entities.map { it.toDomainModel() }
        }
    }

    override suspend fun insertMessage(message: ChatMessage) {
        chatMessageDao.insertMessage(message.toEntity())
    }

    override suspend fun clearUserChatHistory(userId: String) {
        chatMessageDao.clearUserChatHistory(userId)
    }

    override suspend fun deleteMessage(message: ChatMessage) {
        chatMessageDao.deleteMessage(message.toEntity())
    }

    override suspend fun sendMessageToAI(
        message: String,
        deepResearch: Boolean,
        extendedThinking: Boolean
    ): String {
        return try {
            val systemPrompt = buildSystemPrompt(deepResearch, extendedThinking)
            val fullPrompt = "$systemPrompt\n\nUser Question: $message"

            val response = generativeModel.generateContent(fullPrompt)
            response.text ?: "I apologize, but I couldn't generate a response. Please try again."
        } catch (e: Exception) {
            "Error: ${e.message ?: "Failed to get AI response. Please check your internet connection and try again."}"
        }
    }

    private fun buildSystemPrompt(deepResearch: Boolean, extendedThinking: Boolean): String {
        return buildString {
            append("You are MindGarden AI, an educational assistant designed to help students succeed academically and emotionally. ")
            append("Your role is to provide:\n")
            append("1. Academic guidance and course information\n")
            append("2. Effective study techniques and tips\n")
            append("3. Emotional support for students facing academic challenges\n")
            append("4. Motivation and encouragement\n\n")

            if (deepResearch) {
                append("DEEP RESEARCH MODE: Provide comprehensive, well-researched answers with detailed explanations. ")
                append("Include multiple perspectives and cite general educational principles where applicable.\n")
            }

            if (extendedThinking) {
                append("EXTENDED THINKING MODE: Show your reasoning process. Explain your thought process and provide step-by-step analysis.\n")
            }

            append("\nBe empathetic, encouraging, and supportive. ")
            append("If a student mentions failing or struggling, offer concrete help and remind them that setbacks are part of learning.")
        }
    }

    private fun ChatMessageEntity.toDomainModel() = ChatMessage(
        id = id,
        userId = userId,
        message = message,
        response = response,
        isUserMessage = isUserMessage,
        timestamp = timestamp,
        courseId = courseId
    )

    private fun ChatMessage.toEntity() = ChatMessageEntity(
        id = id,
        userId = userId,
        message = message,
        response = response,
        isUserMessage = isUserMessage,
        timestamp = timestamp,
        courseId = courseId
    )
}
