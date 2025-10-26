package com.example.mind_garden.domain.model

data class MotivationalContent(
    val id: Long = 0,
    val title: String,
    val content: String,
    val category: String,
    val author: String? = null,
    val imageUrl: String? = null,
    val createdAt: Long = System.currentTimeMillis()
)

enum class MotivationalCategory(val displayName: String) {
    STRESS_MANAGEMENT("Stress Management"),
    EXAM_TIPS("Exam Tips"),
    MOTIVATION("Motivation"),
    SUCCESS_STORIES("Success Stories")
}
