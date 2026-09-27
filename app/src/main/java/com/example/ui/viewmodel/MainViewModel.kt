package com.example.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.local.*
import com.example.data.model.*
import com.example.data.repository.AppRepository
import com.example.data.repository.TrackDataRepository
import com.example.network.GeminiClient
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

enum class AppScreen {
    HOME,
    TRACK_DETAIL,
    LESSON_PLAYER,
    AI_MENTOR,
    EARNING_HUB,
    MARKETPLACE_GIGS,
    COMMUNITY,
    CERTIFICATES
}

data class ChatMessage(
    val id: String = java.util.UUID.randomUUID().toString(),
    val text: String,
    val isUser: Boolean,
    val timestamp: Long = System.currentTimeMillis()
)

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val db = AppDatabase.getDatabase(application)
    private val repository = AppRepository(db.appDao())

    // UI Navigation State
    private val _currentScreen = MutableStateFlow(AppScreen.HOME)
    val currentScreen: StateFlow<AppScreen> = _currentScreen.asStateFlow()

    private val _selectedTrackId = MutableStateFlow("ai-automation")
    val selectedTrackId: StateFlow<String> = _selectedTrackId.asStateFlow()

    val selectedTrack: StateFlow<LearningTrack> = _selectedTrackId.map { id ->
        TrackDataRepository.getTrackById(id) ?: TrackDataRepository.tracks.first()
    }.stateIn(viewModelScope, SharingStarted.Eagerly, TrackDataRepository.tracks.first())

    // Lesson Player Active Stage: "learn", "practice", "project", "portfolio", "earn"
    private val _activeStage = MutableStateFlow("learn")
    val activeStage: StateFlow<String> = _activeStage.asStateFlow()

    // Video Player State
    private val _isVideoPlaying = MutableStateFlow(false)
    val isVideoPlaying: StateFlow<Boolean> = _isVideoPlaying.asStateFlow()

    private val _videoCurrentSeconds = MutableStateFlow(124) // 2:04
    val videoCurrentSeconds: StateFlow<Int> = _videoCurrentSeconds.asStateFlow()

    private val _videoPlaybackSpeed = MutableStateFlow("1.0x")
    val videoPlaybackSpeed: StateFlow<String> = _videoPlaybackSpeed.asStateFlow()

    // Quiz State
    private val _userQuizAnswers = MutableStateFlow<Map<Int, Int>>(emptyMap())
    val userQuizAnswers: StateFlow<Map<Int, Int>> = _userQuizAnswers.asStateFlow()

    private val _isQuizSubmitted = MutableStateFlow(false)
    val isQuizSubmitted: StateFlow<Boolean> = _isQuizSubmitted.asStateFlow()

    // Notes Download State (set of downloaded note titles)
    private val _downloadedNotes = MutableStateFlow<Set<String>>(emptySet())
    val downloadedNotes: StateFlow<Set<String>> = _downloadedNotes.asStateFlow()

    // AI Mentor Chat
    private val _chatMessages = MutableStateFlow<List<ChatMessage>>(
        listOf(
            ChatMessage(
                text = "नमस्ते! मैं हूँ आपका **DSA AI Guru** 🚀। AI & Automation, Canva Design, Meta Ads, Freelancing या No-Code में आपका कोई भी डाउट हो, बेझिझक पूछें!",
                isUser = false
            )
        )
    )
    val chatMessages: StateFlow<List<ChatMessage>> = _chatMessages.asStateFlow()

    private val _isAiThinking = MutableStateFlow(false)
    val isAiThinking: StateFlow<Boolean> = _isAiThinking.asStateFlow()

    // Database flows
    val userProgress: StateFlow<UserProgressEntity?> = repository.userProgress
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

    val trackProgressList: StateFlow<List<TrackProgressEntity>> = repository.allTrackProgress
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val portfolioProjects: StateFlow<List<PortfolioProjectEntity>> = repository.allPortfolioProjects
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val communityPosts: StateFlow<List<CommunityPostEntity>> = repository.allCommunityPosts
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    // Feedback Toast / Snackbar
    private val _statusMessage = MutableStateFlow<String?>(null)
    val statusMessage: StateFlow<String?> = _statusMessage.asStateFlow()

    init {
        viewModelScope.launch {
            repository.seedInitialDataIfEmpty()
        }
    }

    fun navigateTo(screen: AppScreen) {
        _currentScreen.value = screen
    }

    fun selectTrack(trackId: String) {
        _selectedTrackId.value = trackId
        _activeStage.value = "learn"
        _currentScreen.value = AppScreen.TRACK_DETAIL
    }

    fun openLessonPlayer(trackId: String, stage: String = "learn") {
        _selectedTrackId.value = trackId
        _activeStage.value = stage
        _currentScreen.value = AppScreen.LESSON_PLAYER
    }

    fun setActiveStage(stage: String) {
        _activeStage.value = stage
    }

    fun toggleVideoPlay() {
        _isVideoPlaying.value = !_isVideoPlaying.value
    }

    fun setVideoPlaybackSpeed(speed: String) {
        _videoPlaybackSpeed.value = speed
    }

    fun seekVideoBy(seconds: Int) {
        val next = (_videoCurrentSeconds.value + seconds).coerceAtLeast(0)
        _videoCurrentSeconds.value = next
    }

    fun selectQuizAnswer(questionId: Int, optionIndex: Int) {
        if (!_isQuizSubmitted.value) {
            _userQuizAnswers.value = _userQuizAnswers.value + (questionId to optionIndex)
        }
    }

    fun submitQuiz(trackId: String, totalQuestions: Int, correctCount: Int) {
        _isQuizSubmitted.value = true
        val scorePercent = if (totalQuestions > 0) (correctCount * 100) / totalQuestions else 100
        markStageCompleted(trackId, "practice", xpEarned = 150)
        _statusMessage.value = "Quiz Completed! Score: $scorePercent% (+150 XP Earned! 🏆)"
    }

    fun resetQuiz() {
        _userQuizAnswers.value = emptyMap()
        _isQuizSubmitted.value = false
    }

    fun downloadNotes(title: String) {
        _downloadedNotes.value = _downloadedNotes.value + title
        _statusMessage.value = "$title saved to Downloads & Offline Vault! 📥"
    }

    fun markStageCompleted(trackId: String, stage: String, xpEarned: Int = 100) {
        viewModelScope.launch {
            val existing = trackProgressList.value.find { it.trackId == trackId }
            val stages = existing?.completedStages?.split(",")?.filter { it.isNotBlank() }?.toMutableSet()
                ?: mutableSetOf()
            stages.add(stage)

            val isCert = stages.containsAll(listOf("learn", "practice", "project", "portfolio", "earn"))

            val updatedTrack = (existing ?: TrackProgressEntity(trackId = trackId)).copy(
                completedStages = stages.joinToString(","),
                isCertificateClaimed = if (isCert) true else (existing?.isCertificateClaimed ?: false)
            )
            repository.saveTrackProgress(updatedTrack)

            // Update user XP
            userProgress.value?.let { user ->
                val updatedUser = user.copy(
                    xp = user.xp + xpEarned,
                    level = 1 + (user.xp + xpEarned) / 1000
                )
                repository.saveUserProgress(updatedUser)
            }
        }
    }

    fun sendAiMentorMessage(prompt: String) {
        val trimmed = prompt.trim()
        if (trimmed.isBlank()) return

        val userMsg = ChatMessage(text = trimmed, isUser = true)
        val currentList = _chatMessages.value + userMsg
        _chatMessages.value = currentList
        _isAiThinking.value = true

        viewModelScope.launch {
            try {
                val history = currentList.map { it.text to it.isUser }
                val reply = GeminiClient.askAiMentor(trimmed, history)
                _chatMessages.value = _chatMessages.value + ChatMessage(text = reply, isUser = false)
            } catch (e: Exception) {
                _chatMessages.value = _chatMessages.value + ChatMessage(
                    text = "माफ़ कीजिए, नेटवर्क एरर हुआ। लेकिन याद रखिए: कंसिस्टेंटली रोज़ 2 घंटे प्रैक्टिस करने से कोई भी स्किल सीखी जा सकती है!",
                    isUser = false
                )
            } finally {
                _isAiThinking.value = false
            }
        }
    }

    fun addPortfolioProject(
        title: String,
        category: String,
        description: String,
        clientOrSimulated: String,
        earningsOrValue: String,
        liveLink: String
    ) {
        viewModelScope.launch {
            repository.addPortfolioProject(
                title = title,
                category = category,
                description = description,
                clientOrSimulated = clientOrSimulated,
                earningsOrValue = earningsOrValue,
                liveLink = liveLink
            )
            _statusMessage.value = "Project added to your Live Portfolio! 🌟"
        }
    }

    fun deletePortfolioProject(id: Int) {
        viewModelScope.launch {
            repository.deletePortfolioProject(id)
            _statusMessage.value = "Project removed from Portfolio."
        }
    }

    fun postCommunityQuestion(tag: String, question: String, content: String) {
        viewModelScope.launch {
            repository.createCommunityPost(
                authorName = userProgress.value?.userName ?: "Aarav Sharma",
                tag = tag,
                question = question,
                content = content
            )
            _statusMessage.value = "Post shared in DSA Community! 💬"
        }
    }

    fun likeCommunityPost(postId: Int) {
        viewModelScope.launch {
            repository.likePost(postId)
        }
    }

    fun clearStatusMessage() {
        _statusMessage.value = null
    }
}
