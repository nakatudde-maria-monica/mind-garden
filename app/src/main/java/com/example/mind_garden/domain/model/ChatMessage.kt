package com.example.mind_garden.domain.model

data class ChatMessage(
    val id: Long = 0,
    val userId: String,
    val message: String,
    val response: String = "",
    val isUserMessage: Boolean,
    val timestamp: Long = System.currentTimeMillis(),
    val courseId: String? = null
)
