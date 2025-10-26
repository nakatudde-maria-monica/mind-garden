package com.example.mind_garden.domain.repository

import com.example.mind_garden.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getUserById(userId: String): Flow<User?>
    suspend fun insertUser(user: User)
    suspend fun updateUser(user: User)
    suspend fun deleteUser(user: User)
    suspend fun updateLastLogin(userId: String, timestamp: Long)
}
