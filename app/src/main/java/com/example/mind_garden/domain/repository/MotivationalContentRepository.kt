package com.example.mind_garden.domain.repository

import com.example.mind_garden.domain.model.MotivationalContent
import kotlinx.coroutines.flow.Flow

interface MotivationalContentRepository {
    fun getAllContent(): Flow<List<MotivationalContent>>
    fun getContentByCategory(category: String): Flow<List<MotivationalContent>>
    suspend fun getRandomContent(): MotivationalContent?
    suspend fun insertContent(content: MotivationalContent)
    suspend fun insertAllContent(contents: List<MotivationalContent>)
    suspend fun deleteContent(content: MotivationalContent)
}
