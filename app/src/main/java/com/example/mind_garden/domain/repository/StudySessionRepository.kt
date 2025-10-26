package com.example.mind_garden.domain.repository

import com.example.mind_garden.domain.model.StudySession
import kotlinx.coroutines.flow.Flow

interface StudySessionRepository {
    fun getUserStudySessions(userId: String): Flow<List<StudySession>>
    fun getCourseStudySessions(userId: String, courseId: String): Flow<List<StudySession>>
    fun getSessionsInDateRange(userId: String, startDate: Long, endDate: Long): Flow<List<StudySession>>
    fun getTotalStudyMinutes(userId: String): Flow<Int?>
    fun getCourseStudyMinutes(userId: String, courseId: String): Flow<Int?>
    suspend fun insertSession(session: StudySession): Long
    suspend fun updateSession(session: StudySession)
    suspend fun deleteSession(session: StudySession)
}
