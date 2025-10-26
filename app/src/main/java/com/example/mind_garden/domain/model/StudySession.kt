package com.example.mind_garden.domain.model

data class StudySession(
    val id: Long = 0,
    val userId: String,
    val courseId: String,
    val courseName: String,
    val startTime: Long,
    val endTime: Long? = null,
    val durationMinutes: Int = 0,
    val notes: String? = null,
    val completed: Boolean = false
)
