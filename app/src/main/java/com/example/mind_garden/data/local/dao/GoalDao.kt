package com.example.mind_garden.data.local.dao

import androidx.room.*
import com.example.mind_garden.data.local.entity.GoalEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GoalDao {
    @Query("SELECT * FROM goals WHERE userId = :userId ORDER BY targetDate ASC")
    fun getUserGoals(userId: String): Flow<List<GoalEntity>>

    @Query("SELECT * FROM goals WHERE userId = :userId AND completed = 0 ORDER BY targetDate ASC")
    fun getActiveGoals(userId: String): Flow<List<GoalEntity>>

    @Query("SELECT * FROM goals WHERE userId = :userId AND completed = 1 ORDER BY completedAt DESC")
    fun getCompletedGoals(userId: String): Flow<List<GoalEntity>>

    @Query("SELECT * FROM goals WHERE userId = :userId AND courseId = :courseId")
    fun getCourseGoals(userId: String, courseId: String): Flow<List<GoalEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGoal(goal: GoalEntity)

    @Update
    suspend fun updateGoal(goal: GoalEntity)

    @Delete
    suspend fun deleteGoal(goal: GoalEntity)

    @Query("UPDATE goals SET completed = :completed, completedAt = :completedAt WHERE id = :goalId")
    suspend fun updateGoalCompletion(goalId: Long, completed: Boolean, completedAt: Long?)
}
