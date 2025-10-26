package com.example.mind_garden.data.local.dao

import androidx.room.*
import com.example.mind_garden.data.local.entity.StudySessionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface StudySessionDao {
    @Query("SELECT * FROM study_sessions WHERE userId = :userId ORDER BY startTime DESC")
    fun getUserStudySessions(userId: String): Flow<List<StudySessionEntity>>

    @Query("SELECT * FROM study_sessions WHERE userId = :userId AND courseId = :courseId ORDER BY startTime DESC")
    fun getCourseStudySessions(userId: String, courseId: String): Flow<List<StudySessionEntity>>

    @Query("SELECT * FROM study_sessions WHERE userId = :userId AND startTime >= :startDate AND startTime <= :endDate")
    fun getSessionsInDateRange(userId: String, startDate: Long, endDate: Long): Flow<List<StudySessionEntity>>

    @Query("SELECT SUM(durationMinutes) FROM study_sessions WHERE userId = :userId AND completed = 1")
    fun getTotalStudyMinutes(userId: String): Flow<Int?>

    @Query("SELECT SUM(durationMinutes) FROM study_sessions WHERE userId = :userId AND courseId = :courseId AND completed = 1")
    fun getCourseStudyMinutes(userId: String, courseId: String): Flow<Int?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSession(session: StudySessionEntity): Long

    @Update
    suspend fun updateSession(session: StudySessionEntity)

    @Delete
    suspend fun deleteSession(session: StudySessionEntity)
}
