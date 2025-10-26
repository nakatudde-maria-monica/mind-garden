package com.example.mind_garden.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "courses")
data class CourseEntity(
    @PrimaryKey
    val id: String,
    val code: String,
    val name: String,
    val credits: Int,
    val department: String,
    val level: String,
    val description: String,
    val difficulty: String, // "Easy", "Moderate", "Hard"
    val syllabus: String? = null,
    val instructor: String? = null,
    val semester: String? = null,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)
