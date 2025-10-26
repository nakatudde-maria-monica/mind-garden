package com.example.mind_garden.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "resources")
data class ResourceEntity(
    @PrimaryKey
    val id: String,
    val title: String,
    val description: String,
    val type: String, // "EXAM_PAPER", "STUDY_GUIDE", "VIDEO", "PRACTICE"
    val category: String,
    val courseId: String? = null,
    val url: String? = null,
    val filePathLocal: String? = null,
    val viewCount: Int = 0,
    val downloadCount: Int = 0,
    val rating: Float = 0f,
    val createdAt: Long = System.currentTimeMillis()
)
