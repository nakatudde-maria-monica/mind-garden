package com.example.mind_garden.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mind_garden.data.local.dao.*
import com.example.mind_garden.data.local.entity.*

@Database(
    entities = [
        UserEntity::class,
        CourseEntity::class,
        StudySessionEntity::class,
        GoalEntity::class,
        ChatMessageEntity::class,
        MotivationalContentEntity::class,
        ResourceEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class MindGardenDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun courseDao(): CourseDao
    abstract fun studySessionDao(): StudySessionDao
    abstract fun goalDao(): GoalDao
    abstract fun chatMessageDao(): ChatMessageDao
    abstract fun motivationalContentDao(): MotivationalContentDao
    abstract fun resourceDao(): ResourceDao
}
