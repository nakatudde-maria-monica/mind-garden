package com.example.mind_garden.data.local.dao

import androidx.room.*
import com.example.mind_garden.data.local.entity.ResourceEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ResourceDao {
    @Query("SELECT * FROM resources")
    fun getAllResources(): Flow<List<ResourceEntity>>

    @Query("SELECT * FROM resources WHERE category = :category")
    fun getResourcesByCategory(category: String): Flow<List<ResourceEntity>>

    @Query("SELECT * FROM resources WHERE courseId = :courseId")
    fun getCourseResources(courseId: String): Flow<List<ResourceEntity>>

    @Query("SELECT * FROM resources WHERE type = :type")
    fun getResourcesByType(type: String): Flow<List<ResourceEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertResource(resource: ResourceEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertResources(resources: List<ResourceEntity>)

    @Update
    suspend fun updateResource(resource: ResourceEntity)

    @Query("UPDATE resources SET viewCount = viewCount + 1 WHERE id = :resourceId")
    suspend fun incrementViewCount(resourceId: String)

    @Query("UPDATE resources SET downloadCount = downloadCount + 1 WHERE id = :resourceId")
    suspend fun incrementDownloadCount(resourceId: String)
}
