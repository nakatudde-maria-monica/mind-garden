package com.example.mind_garden.domain.repository

import com.example.mind_garden.Course
import kotlinx.coroutines.flow.Flow

interface CourseRepository {
    fun getAllCourses(): Flow<List<Course>>
    fun getCourseById(courseId: String): Flow<Course?>
    fun getCoursesByDepartment(department: String): Flow<List<Course>>
    fun getCoursesByLevel(level: String): Flow<List<Course>>
    fun searchCourses(query: String): Flow<List<Course>>
    suspend fun insertCourse(course: Course)
    suspend fun insertCourses(courses: List<Course>)
    suspend fun updateCourse(course: Course)
    suspend fun deleteCourse(course: Course)
}
