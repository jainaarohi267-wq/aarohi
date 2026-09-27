package com.example.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_progress")
data class UserProgressEntity(
    @PrimaryKey val id: Int = 1,
    val userName: String = "Aarav Sharma",
    val userRole: String = "AI & Digital Skills Hustler",
    val xp: Int = 1450,
    val streakDays: Int = 8,
    val level: Int = 3,
    val levelTitle: String = "Rising Freelancer",
    val targetMonthlyGoal: Int = 50000,
    val currentEstimatedEarnings: Int = 18500
)

@Entity(tableName = "track_progress")
data class TrackProgressEntity(
    @PrimaryKey val trackId: String,
    val completedLessons: String = "", // Comma-separated lesson IDs
    val completedStages: String = "",  // e.g. "learn,practice"
    val quizScore: Int = 0,
    val isCertificateClaimed: Boolean = false,
    val lastAccessedTimestamp: Long = System.currentTimeMillis()
)

@Entity(tableName = "portfolio_projects")
data class PortfolioProjectEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val trackCategory: String,
    val description: String,
    val clientNameOrSimulated: String,
    val earningsOrValue: String,
    val liveLink: String,
    val createdAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "community_posts")
data class CommunityPostEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val authorName: String,
    val authorBadge: String,
    val categoryTag: String,
    val questionOrTitle: String,
    val content: String,
    val likesCount: Int = 0,
    val repliesCount: Int = 0,
    val isLikedByUser: Boolean = false,
    val timestamp: Long = System.currentTimeMillis()
)
