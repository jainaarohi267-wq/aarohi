package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ui.components.DsaBottomNav
import com.example.ui.components.DsaTopBar
import com.example.ui.screens.*
import com.example.ui.theme.MyApplicationTheme
import com.example.ui.theme.ObsidianBackground
import com.example.ui.viewmodel.AppScreen
import com.example.ui.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                val viewModel: MainViewModel = viewModel()
                val currentScreen by viewModel.currentScreen.collectAsStateWithLifecycle()
                val userProgress by viewModel.userProgress.collectAsStateWithLifecycle()
                val trackProgressList by viewModel.trackProgressList.collectAsStateWithLifecycle()
                val selectedTrack by viewModel.selectedTrack.collectAsStateWithLifecycle()
                val activeStage by viewModel.activeStage.collectAsStateWithLifecycle()
                val isVideoPlaying by viewModel.isVideoPlaying.collectAsStateWithLifecycle()
                val videoSeconds by viewModel.videoCurrentSeconds.collectAsStateWithLifecycle()
                val videoSpeed by viewModel.videoPlaybackSpeed.collectAsStateWithLifecycle()
                val downloadedNotes by viewModel.downloadedNotes.collectAsStateWithLifecycle()
                val userQuizAnswers by viewModel.userQuizAnswers.collectAsStateWithLifecycle()
                val isQuizSubmitted by viewModel.isQuizSubmitted.collectAsStateWithLifecycle()
                val chatMessages by viewModel.chatMessages.collectAsStateWithLifecycle()
                val isAiThinking by viewModel.isAiThinking.collectAsStateWithLifecycle()
                val portfolioProjects by viewModel.portfolioProjects.collectAsStateWithLifecycle()
                val communityPosts by viewModel.communityPosts.collectAsStateWithLifecycle()
                val statusMessage by viewModel.statusMessage.collectAsStateWithLifecycle()

                val snackbarHostState = remember { SnackbarHostState() }

                LaunchedEffect(statusMessage) {
                    statusMessage?.let { msg ->
                        snackbarHostState.showSnackbar(msg)
                        viewModel.clearStatusMessage()
                    }
                }

                // Handle back press
                BackHandler(enabled = currentScreen != AppScreen.HOME) {
                    when (currentScreen) {
                        AppScreen.LESSON_PLAYER -> viewModel.navigateTo(AppScreen.TRACK_DETAIL)
                        AppScreen.TRACK_DETAIL -> viewModel.navigateTo(AppScreen.HOME)
                        else -> viewModel.navigateTo(AppScreen.HOME)
                    }
                }

                val currentTrackProgress = trackProgressList.find { it.trackId == selectedTrack.id }

                Scaffold(
                    topBar = {
                        DsaTopBar(
                            xp = userProgress?.xp ?: 1450,
                            streak = userProgress?.streakDays ?: 8,
                            onCertificateClick = { viewModel.navigateTo(AppScreen.CERTIFICATES) }
                        )
                    },
                    bottomBar = {
                        DsaBottomNav(
                            currentScreen = currentScreen,
                            onNavigate = { screen -> viewModel.navigateTo(screen) }
                        )
                    },
                    snackbarHost = { SnackbarHost(snackbarHostState) },
                    containerColor = ObsidianBackground,
                    contentWindowInsets = WindowInsets.safeDrawing
                ) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(ObsidianBackground)
                            .padding(innerPadding)
                    ) {
                        when (currentScreen) {
                            AppScreen.HOME -> HomeScreen(
                                userProgress = userProgress,
                                trackProgressList = trackProgressList,
                                onSelectTrack = { trackId -> viewModel.selectTrack(trackId) },
                                onNavigate = { screen -> viewModel.navigateTo(screen) },
                                onStartLesson = { trackId, stage -> viewModel.openLessonPlayer(trackId, stage) }
                            )

                            AppScreen.TRACK_DETAIL -> TrackDetailScreen(
                                track = selectedTrack,
                                trackProgress = currentTrackProgress,
                                onBack = { viewModel.navigateTo(AppScreen.HOME) },
                                onOpenStage = { stage -> viewModel.openLessonPlayer(selectedTrack.id, stage) }
                            )

                            AppScreen.LESSON_PLAYER -> LessonPlayerScreen(
                                track = selectedTrack,
                                activeStage = activeStage,
                                trackProgress = currentTrackProgress,
                                isVideoPlaying = isVideoPlaying,
                                videoCurrentSeconds = videoSeconds,
                                videoPlaybackSpeed = videoSpeed,
                                downloadedNotes = downloadedNotes,
                                userQuizAnswers = userQuizAnswers,
                                isQuizSubmitted = isQuizSubmitted,
                                onBack = { viewModel.navigateTo(AppScreen.TRACK_DETAIL) },
                                onStageSelect = { stage -> viewModel.setActiveStage(stage) },
                                onTogglePlay = { viewModel.toggleVideoPlay() },
                                onSeek = { secs -> viewModel.seekVideoBy(secs) },
                                onSetSpeed = { speed -> viewModel.setVideoPlaybackSpeed(speed) },
                                onSelectQuizAnswer = { qId, optIdx -> viewModel.selectQuizAnswer(qId, optIdx) },
                                onSubmitQuiz = { total, correct -> viewModel.submitQuiz(selectedTrack.id, total, correct) },
                                onResetQuiz = { viewModel.resetQuiz() },
                                onDownloadNotes = { title -> viewModel.downloadNotes(title) },
                                onMarkStageComplete = { stage, xp -> viewModel.markStageCompleted(selectedTrack.id, stage, xp) },
                                onAddToPortfolio = { title, cat, desc, client, value ->
                                    viewModel.addPortfolioProject(title, cat, desc, client, value, "https://dsa.learn-earn/p/${selectedTrack.id}")
                                },
                                onAskAiAboutStage = { query ->
                                    viewModel.sendAiMentorMessage(query)
                                    viewModel.navigateTo(AppScreen.AI_MENTOR)
                                }
                            )

                            AppScreen.AI_MENTOR -> AiMentorScreen(
                                messages = chatMessages,
                                isThinking = isAiThinking,
                                onSendMessage = { text -> viewModel.sendAiMentorMessage(text) }
                            )

                            AppScreen.EARNING_HUB -> EarningHubScreen(
                                portfolioProjects = portfolioProjects,
                                onAddPortfolioProject = { title, cat, desc, client, value, link ->
                                    viewModel.addPortfolioProject(title, cat, desc, client, value, link)
                                },
                                onDeletePortfolioProject = { id -> viewModel.deletePortfolioProject(id) }
                            )

                            AppScreen.MARKETPLACE_GIGS -> MarketplaceGigsScreen(
                                onApplyGig = { gig ->
                                    viewModel.sendAiMentorMessage("Draft a winning proposal in Hindi for this gig: ${gig.title} (${gig.clientCompany})")
                                    viewModel.navigateTo(AppScreen.AI_MENTOR)
                                },
                                onPurchaseProduct = { product ->
                                    viewModel.downloadNotes(product.name)
                                }
                            )

                            AppScreen.COMMUNITY -> CommunityScreen(
                                posts = communityPosts,
                                onLikePost = { id -> viewModel.likeCommunityPost(id) },
                                onNewPost = { tag, title, content -> viewModel.postCommunityQuestion(tag, title, content) }
                            )

                            AppScreen.CERTIFICATES -> CertificatesScreen(
                                userProgress = userProgress,
                                trackProgressList = trackProgressList,
                                downloadedNotes = downloadedNotes,
                                onSelectTrack = { trackId -> viewModel.selectTrack(trackId) }
                            )
                        }
                    }
                }
            }
        }
    }
}
