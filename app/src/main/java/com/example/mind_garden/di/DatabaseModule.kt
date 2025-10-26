package com.example.mind_garden.di

import android.content.Context
import androidx.room.Room
import com.example.mind_garden.data.local.MindGardenDatabase
import com.example.mind_garden.data.local.dao.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideMindGardenDatabase(
        @ApplicationContext context: Context
    ): MindGardenDatabase {
        return Room.databaseBuilder(
            context,
            MindGardenDatabase::class.java,
            "mind_garden_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideUserDao(database: MindGardenDatabase): UserDao {
        return database.userDao()
    }

    @Provides
    fun provideCourseDao(database: MindGardenDatabase): CourseDao {
        return database.courseDao()
    }

    @Provides
    fun provideStudySessionDao(database: MindGardenDatabase): StudySessionDao {
        return database.studySessionDao()
    }

    @Provides
    fun provideGoalDao(database: MindGardenDatabase): GoalDao {
        return database.goalDao()
    }

    @Provides
    fun provideChatMessageDao(database: MindGardenDatabase): ChatMessageDao {
        return database.chatMessageDao()
    }

    @Provides
    fun provideMotivationalContentDao(database: MindGardenDatabase): MotivationalContentDao {
        return database.motivationalContentDao()
    }

    @Provides
    fun provideResourceDao(database: MindGardenDatabase): ResourceDao {
        return database.resourceDao()
    }
}
