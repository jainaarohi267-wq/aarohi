package com.example.data.model

data class LearningTrack(
    val id: String,
    val title: String,
    val subtitleHindi: String,
    val description: String,
    val earningPotential: String,
    val averageStartingRate: String,
    val level: String = "Zero to Hero",
    val durationHours: Int,
    val lessonsCount: Int,
    val tags: List<String>,
    val stages: TrackStages,
    val toolsCovered: List<String>
)

data class TrackStages(
    val learn: LearnStage,
    val practice: PracticeStage,
    val project: ProjectStage,
    val portfolio: PortfolioStage,
    val earn: EarnStage
)

data class LearnStage(
    val title: String,
    val videoTitleHindi: String,
    val videoDuration: String,
    val instructor: String,
    val videoChapters: List<VideoChapter>,
    val transcriptHindiExcerpt: String,
    val keyConcepts: List<String>
)

data class VideoChapter(
    val timestamp: String,
    val title: String
)

data class PracticeStage(
    val title: String,
    val infographicTitle: String,
    val infographicDescription: String,
    val infographicDiagramSteps: List<DiagramStep>,
    val downloadableNotesTitle: String,
    val downloadableNotesSize: String,
    val downloadableNotesKeyPoints: List<String>,
    val quizQuestions: List<QuizQuestion>
)

data class DiagramStep(
    val stepNumber: Int,
    val title: String,
    val description: String,
    val highlightColor: Long
)

data class QuizQuestion(
    val id: Int,
    val questionHindi: String,
    val questionEnglish: String,
    val options: List<String>,
    val correctIndex: Int,
    val explanationHindi: String
)

data class ProjectStage(
    val projectTitle: String,
    val clientContext: String,
    val problemStatementHindi: String,
    val deliverableChecklist: List<String>,
    val expectedOutcome: String,
    val estimatedProjectValue: String
)

data class PortfolioStage(
    val title: String,
    val portfolioPieceTitle: String,
    val mockupDescription: String,
    val clientPitchHindi: String,
    val recommendedTags: List<String>
)

data class EarnStage(
    val title: String,
    val gigTitleExample: String,
    val beginnerPriceRange: String,
    val proPriceRange: String,
    val topPlatforms: List<String>,
    val proposalScriptHindi: String,
    val coldEmailPitch: String
)

// Opportunities & Marketplace
data class GigOpportunity(
    val id: String,
    val title: String,
    val clientCompany: String,
    val location: String = "Remote / India",
    val stipendOrBudget: String,
    val requiredTrack: String,
    val deadline: String,
    val description: String,
    val tags: List<String>
)

data class DigitalProductItem(
    val id: String,
    val name: String,
    val category: String,
    val priceInInr: Int,
    val salesCount: Int,
    val rating: Float,
    val authorName: String,
    val description: String,
    val previewBadge: String
)

data class EarningMilestone(
    val levelName: String,
    val targetMonthlyIncome: String,
    val durationToAchieve: String,
    val skillsNeeded: List<String>,
    val weeklyHoursRecommended: Int,
    val weeklyMilestones: List<String>
)
