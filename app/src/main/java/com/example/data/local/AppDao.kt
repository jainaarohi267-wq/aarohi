package com.example.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface AppDao {
    // User progress
    @Query("SELECT * FROM user_progress WHERE id = 1 LIMIT 1")
    fun getUserProgress(): Flow<UserProgressEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateUserProgress(progress: UserProgressEntity)

    // Track progress
    @Query("SELECT * FROM track_progress")
    fun getAllTrackProgress(): Flow<List<TrackProgressEntity>>

    @Query("SELECT * FROM track_progress WHERE trackId = :trackId LIMIT 1")
    fun getTrackProgress(trackId: String): Flow<TrackProgressEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateTrackProgress(progress: TrackProgressEntity)

    // Portfolio projects
    @Query("SELECT * FROM portfolio_projects ORDER BY createdAt DESC")
    fun getAllPortfolioProjects(): Flow<List<PortfolioProjectEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPortfolioProject(project: PortfolioProjectEntity)

    @Query("DELETE FROM portfolio_projects WHERE id = :id")
    suspend fun deletePortfolioProject(id: Int)

    // Community posts
    @Query("SELECT * FROM community_posts ORDER BY timestamp DESC")
    fun getAllCommunityPosts(): Flow<List<CommunityPostEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCommunityPost(post: CommunityPostEntity)

    @Update
    suspend fun updateCommunityPost(post: CommunityPostEntity)

    @Query("UPDATE community_posts SET likesCount = likesCount + 1, isLikedByUser = 1 WHERE id = :id")
    suspend fun likePost(id: Int)
}
