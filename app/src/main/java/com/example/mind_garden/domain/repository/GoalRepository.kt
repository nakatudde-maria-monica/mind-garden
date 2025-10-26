package com.example.mind_garden.domain.repository

import com.example.mind_garden.domain.model.Goal
import kotlinx.coroutines.flow.Flow

interface GoalRepository {
    fun getUserGoals(userId: String): Flow<List<Goal>>
    fun getActiveGoals(userId: String): Flow<List<Goal>>
    fun getCompletedGoals(userId: String): Flow<List<Goal>>
    fun getCourseGoals(userId: String, courseId: String): Flow<List<Goal>>
    suspend fun insertGoal(goal: Goal)
    suspend fun updateGoal(goal: Goal)
    suspend fun deleteGoal(goal: Goal)
    suspend fun updateGoalCompletion(goalId: Long, completed: Boolean, completedAt: Long?)
}
