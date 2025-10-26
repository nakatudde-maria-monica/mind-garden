package com.example.mind_garden.domain.model

data class User(
    val id: String,
    val email: String,
    val username: String,
    val fullName: String,
    val studentId: String? = null,
    val programId: String? = null,
    val yearLevel: Int? = null,
    val avatarUrl: String? = null,
    val createdAt: Long = System.currentTimeMillis(),
    val lastLoginAt: Long = System.currentTimeMillis()
)
