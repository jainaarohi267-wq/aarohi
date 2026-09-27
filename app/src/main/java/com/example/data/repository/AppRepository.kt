package com.example.data.repository

import com.example.data.local.*
import kotlinx.coroutines.flow.Flow

class AppRepository(private val appDao: AppDao) {

    val userProgress: Flow<UserProgressEntity?> = appDao.getUserProgress()
    val allTrackProgress: Flow<List<TrackProgressEntity>> = appDao.getAllTrackProgress()
    val allPortfolioProjects: Flow<List<PortfolioProjectEntity>> = appDao.getAllPortfolioProjects()
    val allCommunityPosts: Flow<List<CommunityPostEntity>> = appDao.getAllCommunityPosts()

    fun getTrackProgress(trackId: String): Flow<TrackProgressEntity?> =
        appDao.getTrackProgress(trackId)

    suspend fun saveUserProgress(progress: UserProgressEntity) {
        appDao.insertOrUpdateUserProgress(progress)
    }

    suspend fun saveTrackProgress(progress: TrackProgressEntity) {
        appDao.insertOrUpdateTrackProgress(progress)
    }

    suspend fun markStageComplete(trackId: String, stage: String, xpToAdd: Int = 100) {
        // Update track progress
        val currentProgress = appDao.getTrackProgress(trackId)
        // We will handle this in ViewModel or transaction
    }

    suspend fun addPortfolioProject(
        title: String,
        category: String,
        description: String,
        clientOrSimulated: String,
        earningsOrValue: String,
        liveLink: String
    ) {
        appDao.insertPortfolioProject(
            PortfolioProjectEntity(
                title = title,
                trackCategory = category,
                description = description,
                clientNameOrSimulated = clientOrSimulated,
                earningsOrValue = earningsOrValue,
                liveLink = liveLink
            )
        )
    }

    suspend fun deletePortfolioProject(id: Int) {
        appDao.deletePortfolioProject(id)
    }

    suspend fun createCommunityPost(
        authorName: String,
        tag: String,
        question: String,
        content: String
    ) {
        appDao.insertCommunityPost(
            CommunityPostEntity(
                authorName = authorName,
                authorBadge = "DSA Hustler",
                categoryTag = tag,
                questionOrTitle = question,
                content = content,
                likesCount = 1,
                repliesCount = 0
            )
        )
    }

    suspend fun likePost(id: Int) {
        appDao.likePost(id)
    }

    suspend fun seedInitialDataIfEmpty() {
        // Seed initial user if not present
        val defaultUser = UserProgressEntity(
            id = 1,
            userName = "Aarav Sharma",
            userRole = "DSA Pro Hustler (Zero to Hero)",
            xp = 2450,
            streakDays = 9,
            level = 4,
            levelTitle = "Emerging Hero Earner",
            targetMonthlyGoal = 75000,
            currentEstimatedEarnings = 32000
        )
        appDao.insertOrUpdateUserProgress(defaultUser)

        // Seed initial sample track progress
        appDao.insertOrUpdateTrackProgress(
            TrackProgressEntity(
                trackId = "ai-automation",
                completedStages = "learn,practice",
                quizScore = 100,
                isCertificateClaimed = false
            )
        )
        appDao.insertOrUpdateTrackProgress(
            TrackProgressEntity(
                trackId = "graphic-design-canva",
                completedStages = "learn,practice,project,portfolio,earn",
                quizScore = 100,
                isCertificateClaimed = true
            )
        )

        // Seed initial portfolio projects
        appDao.insertPortfolioProject(
            PortfolioProjectEntity(
                title = "FinTech Simplified YouTube CTR Overhaul",
                trackCategory = "Graphic Design & Canva",
                description = "Redesigned 10 YouTube thumbnails in Canva Pro with obsidian background & neon text, boosting CTR from 4.1% to 9.2%.",
                clientNameOrSimulated = "FinTech Simplified (Client)",
                earningsOrValue = "₹12,000 Earned",
                liveLink = "https://behance.net/sample-dsa-showcase"
            )
        )
        appDao.insertPortfolioProject(
            PortfolioProjectEntity(
                title = "UrbanNest 24/7 AI Lead Qualification Scenario",
                trackCategory = "AI & Automation",
                description = "Make.com workflow with OpenAI ChatGPT 4o qualifying real estate leads via WhatsApp webhook into Google Sheets.",
                clientNameOrSimulated = "UrbanNest Realty",
                earningsOrValue = "₹25,000 Setup Value",
                liveLink = "https://github.com/dsa-learn-earn/make-scenario"
            )
        )

        // Seed community posts
        appDao.insertCommunityPost(
            CommunityPostEntity(
                authorName = "Rohan Verma",
                authorBadge = "Top Earner ⭐",
                categoryTag = "First Client Win 🎉",
                questionOrTitle = "Got my first $250 Upwork project using the 4-Sentence Proposal!",
                content = "Bhai log, thank you DSA AI Guru & Kunal Sir! Maine Upwork par generic bid chhod kar 60-second Loom video bheja tha. Client ne 1 ghante mein hire kar liya. Keep hustling!",
                likesCount = 42,
                repliesCount = 18,
                isLikedByUser = true
            )
        )
        appDao.insertCommunityPost(
            CommunityPostEntity(
                authorName = "Priya Deshmukh",
                authorBadge = "AI Enthusiast",
                categoryTag = "Doubt Solving ❓",
                questionOrTitle = "Make.com mein Google Sheets ka webhook disconnect ho raha hai, solution?",
                content = "Jab bhi nayi row add hoti hai to kabhi kabhi trigger fail ho jata hai. Error handling mein router kaise lagaye?",
                likesCount = 15,
                repliesCount = 7,
                isLikedByUser = false
            )
        )
        appDao.insertCommunityPost(
            CommunityPostEntity(
                authorName = "Kabir Singh",
                authorBadge = "No-Code Builder",
                categoryTag = "Project Showcase 🚀",
                questionOrTitle = "FlutterFlow mein QuickFix Services MVP complete kiya!",
                content = "DSA ke No-Code track ka project follow karke Razorpay integration aur booking screens banayi hain. Feedback dijiye please!",
                likesCount = 28,
                repliesCount = 9,
                isLikedByUser = false
            )
        )
    }
}
