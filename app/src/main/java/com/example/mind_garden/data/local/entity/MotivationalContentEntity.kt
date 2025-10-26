package com.example.mind_garden.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "motivational_content")
data class MotivationalContentEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val content: String,
    val category: String, // "stress_management", "exam_tips", "motivation", "success_stories"
    val author: String? = null,
    val imageUrl: String? = null,
    val createdAt: Long = System.currentTimeMillis()
)
