package com.example.mind_garden.data.local.dao

import androidx.room.*
import com.example.mind_garden.data.local.entity.CourseEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CourseDao {
    @Query("SELECT * FROM courses")
    fun getAllCourses(): Flow<List<CourseEntity>>

    @Query("SELECT * FROM courses WHERE id = :courseId")
    fun getCourseById(courseId: String): Flow<CourseEntity?>

    @Query("SELECT * FROM courses WHERE department = :department")
    fun getCoursesByDepartment(department: String): Flow<List<CourseEntity>>

    @Query("SELECT * FROM courses WHERE level = :level")
    fun getCoursesByLevel(level: String): Flow<List<CourseEntity>>

    @Query("SELECT * FROM courses WHERE code LIKE '%' || :query || '%' OR name LIKE '%' || :query || '%'")
    fun searchCourses(query: String): Flow<List<CourseEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCourse(course: CourseEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCourses(courses: List<CourseEntity>)

    @Update
    suspend fun updateCourse(course: CourseEntity)

    @Delete
    suspend fun deleteCourse(course: CourseEntity)
}
