package com.example.mind_garden.domain.model

data class Goal(
    val id: Long = 0,
    val userId: String,
    val title: String,
    val description: String,
    val targetDate: Long,
    val courseId: String? = null,
    val completed: Boolean = false,
    val createdAt: Long = System.currentTimeMillis(),
    val completedAt: Long? = null
)
