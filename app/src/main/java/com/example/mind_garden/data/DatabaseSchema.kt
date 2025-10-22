package com.example.mind_garden.data

/**
 * Database Schema for Mind Garden App
 *
 * This file documents the database structure for storing courses, resources,
 * user data, and other app-related information.
 *
 * Future Implementation: Use Room Database or SQLite
 */

/**
 * Course Table
 * Stores all available courses
 */
data class CourseEntity(
    val id: Long,
    val code: String,              // e.g., "CS101"
    val name: String,              // e.g., "Introduction to Programming"
    val credits: Int,              // e.g., 3
    val departmentId: Long,        // Foreign key to Department
    val level: String,             // e.g., "100", "200", "300", "400"
    val description: String,
    val difficulty: String,        // "Easy", "Moderate", "Hard"
    val syllabus: String?,         // Optional syllabus content
    val instructor: String?,       // Optional instructor name
    val semester: String?,         // e.g., "Fall 2024"
    val createdAt: Long,           // Timestamp
    val updatedAt: Long            // Timestamp
)

/**
 * Department Table
 * Stores academic departments
 */
data class DepartmentEntity(
    val id: Long,
    val name: String,              // e.g., "Computer Science"
    val description: String,
    val facultyCount: Int,
    val studentCount: Int
)

/**
 * Prerequisite Table
 * Maps course prerequisites (Many-to-Many relationship)
 */
data class PrerequisiteEntity(
    val id: Long,
    val courseId: Long,            // Course that has prerequisites
    val prerequisiteCourseId: Long // Required course code
)

/**
 * Resource Table
 * Stores study resources (past papers, guides, videos, etc.)
 */
data class ResourceEntity(
    val id: Long,
    val title: String,
    val description: String,
    val type: String,              // "EXAM_PAPER", "STUDY_GUIDE", "VIDEO", "PRACTICE"
    val categoryId: Long,          // Foreign key to ResourceCategory
    val courseId: Long?,           // Optional - linked to specific course
    val url: String?,              // External link if applicable
    val filePathLocal: String?,    // Local file path if stored locally
    val uploadedBy: Long?,         // User ID who uploaded
    val viewCount: Int,
    val downloadCount: Int,
    val rating: Float,             // Average rating (0-5)
    val createdAt: Long,
    val updatedAt: Long
)

/**
 * Resource Category Table
 */
data class ResourceCategoryEntity(
    val id: Long,
    val name: String,              // e.g., "Past Exam Papers"
    val description: String,
    val iconName: String
)

/**
 * User Table
 * Stores student/user information
 */
data class UserEntity(
    val id: Long,
    val email: String,
    val username: String,
    val fullName: String,
    val studentId: String?,        // Optional student ID
    val programId: Long?,          // Foreign key to Program/Major
    val yearLevel: Int?,           // 1, 2, 3, 4
    val avatarUrl: String?,
    val createdAt: Long,
    val lastLoginAt: Long
)

/**
 * Enrolled Courses Table
 * Tracks which courses a user is enrolled in
 */
data class EnrolledCourseEntity(
    val id: Long,
    val userId: Long,
    val courseId: Long,
    val semester: String,          // e.g., "Fall 2024"
    val grade: String?,            // Final grade if completed
    val status: String,            // "ENROLLED", "COMPLETED", "FAILED", "WITHDRAWN"
    val enrolledAt: Long,
    val completedAt: Long?
)

/**
 * Saved Courses Table
 * Courses bookmarked/saved by users
 */
data class SavedCourseEntity(
    val id: Long,
    val userId: Long,
    val courseId: Long,
    val savedAt: Long
)

/**
 * Study Plan Table
 * User-created study plans
 */
data class StudyPlanEntity(
    val id: Long,
    val userId: Long,
    val courseId: Long,
    val title: String,             // e.g., "Week 5 Study Plan"
    val description: String,
    val startDate: Long,
    val endDate: Long,
    val completed: Boolean,
    val createdAt: Long,
    val updatedAt: Long
)

/**
 * Study Plan Tasks Table
 * Individual tasks within a study plan
 */
data class StudyPlanTaskEntity(
    val id: Long,
    val studyPlanId: Long,
    val title: String,
    val description: String,
    val completed: Boolean,
    val dueDate: Long?,
    val priority: Int,             // 1=High, 2=Medium, 3=Low
    val estimatedHours: Float?,
    val createdAt: Long,
    val completedAt: Long?
)

/**
 * AI Chat History Table
 * Stores user conversations with AI assistant
 */
data class AIChatHistoryEntity(
    val id: Long,
    val userId: Long,
    val query: String,
    val response: String,
    val deepResearchEnabled: Boolean,
    val extendedThinkingEnabled: Boolean,
    val courseId: Long?,           // If related to specific course
    val helpful: Boolean?,         // User feedback
    val createdAt: Long
)

/**
 * Notifications Table
 * App notifications for users
 */
data class NotificationEntity(
    val id: Long,
    val userId: Long,
    val title: String,
    val message: String,
    val type: String,              // "EXAM", "DEADLINE", "ANNOUNCEMENT", "RESOURCE"
    val relatedCourseId: Long?,
    val read: Boolean,
    val actionUrl: String?,
    val createdAt: Long,
    val readAt: Long?
)

/**
 * Exam Schedule Table
 * Stores exam information
 */
data class ExamScheduleEntity(
    val id: Long,
    val courseId: Long,
    val examType: String,          // "MIDTERM", "FINAL", "QUIZ"
    val examDate: Long,
    val duration: Int,             // Duration in minutes
    val location: String,
    val instructions: String?,
    val createdAt: Long
)

/**
 * User Notes Table
 * Personal notes for courses
 */
data class UserNoteEntity(
    val id: Long,
    val userId: Long,
    val courseId: Long?,           // Optional - general notes if null
    val title: String,
    val content: String,
    val tags: String,              // Comma-separated tags
    val createdAt: Long,
    val updatedAt: Long
)

/**
 * Resource Ratings Table
 * User ratings for resources
 */
data class ResourceRatingEntity(
    val id: Long,
    val resourceId: Long,
    val userId: Long,
    val rating: Int,               // 1-5 stars
    val review: String?,
    val createdAt: Long
)

/*
 * Recommended Database: Room Database (Android Jetpack)
 *
 * To implement this schema:
 * 1. Add Room dependencies to build.gradle
 * 2. Convert data classes to @Entity annotations
 * 3. Create @Dao interfaces for each entity
 * 4. Create AppDatabase class extending RoomDatabase
 * 5. Implement Repository pattern for data access
 *
 * Alternative: Firebase Firestore for cloud-based solution
 */
