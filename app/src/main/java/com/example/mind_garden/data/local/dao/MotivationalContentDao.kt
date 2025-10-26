package com.example.mind_garden.data.local.dao

import androidx.room.*
import com.example.mind_garden.data.local.entity.MotivationalContentEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MotivationalContentDao {
    @Query("SELECT * FROM motivational_content ORDER BY createdAt DESC")
    fun getAllContent(): Flow<List<MotivationalContentEntity>>

    @Query("SELECT * FROM motivational_content WHERE category = :category ORDER BY createdAt DESC")
    fun getContentByCategory(category: String): Flow<List<MotivationalContentEntity>>

    @Query("SELECT * FROM motivational_content ORDER BY RANDOM() LIMIT 1")
    suspend fun getRandomContent(): MotivationalContentEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertContent(content: MotivationalContentEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllContent(contents: List<MotivationalContentEntity>)

    @Delete
    suspend fun deleteContent(content: MotivationalContentEntity)
}
