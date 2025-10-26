package com.example.mind_garden.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "chat_messages")
data class ChatMessageEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val userId: String,
    val message: String,
    val response: String,
    val isUserMessage: Boolean,
    val timestamp: Long = System.currentTimeMillis(),
    val courseId: String? = null
)
