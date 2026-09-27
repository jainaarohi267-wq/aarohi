package com.example.ui.screens

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.local.TrackProgressEntity
import com.example.data.model.DiagramStep
import com.example.data.model.LearningTrack
import com.example.data.model.QuizQuestion
import com.example.ui.components.GoldGradientButton
import com.example.ui.theme.*

@Composable
fun LessonPlayerScreen(
    track: LearningTrack,
    activeStage: String,
    trackProgress: TrackProgressEntity?,
    isVideoPlaying: Boolean,
    videoCurrentSeconds: Int,
    videoPlaybackSpeed: String,
    downloadedNotes: Set<String>,
    userQuizAnswers: Map<Int, Int>,
    isQuizSubmitted: Boolean,
    onBack: () -> Unit,
    onStageSelect: (String) -> Unit,
    onTogglePlay: () -> Unit,
    onSeek: (Int) -> Unit,
    onSetSpeed: (String) -> Unit,
    onSelectQuizAnswer: (Int, Int) -> Unit,
    onSubmitQuiz: (Int, Int) -> Unit,
    onResetQuiz: () -> Unit,
    onDownloadNotes: (String) -> Unit,
    onMarkStageComplete: (String, Int) -> Unit,
    onAddToPortfolio: (String, String, String, String, String) -> Unit,
    onAskAiAboutStage: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val completedStages = trackProgress?.completedStages?.split(",")?.filter { it.isNotBlank() }?.toSet()
        ?: emptySet()

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(ObsidianBackground)
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        item { Spacer(modifier = Modifier.height(4.dp)) }

        // Top Navigation Bar
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(
                        onClick = onBack,
                        modifier = Modifier
                            .size(36.dp)
                            .clip(CircleShape)
                            .background(ObsidianSurface)
                            .testTag("lesson_player_back_button")
                    ) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = GoldPrimary)
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Text(
                            text = track.title,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = TextWhite
                        )
                        Text(
                            text = "Phase: ${activeStage.uppercase()}",
                            fontSize = 11.sp,
                            color = GoldSecondary,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }

                // AI Guru Quick Help button
                IconButton(
                    onClick = { onAskAiAboutStage("Help me understand ${track.title} in Hindi") },
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape)
                        .background(GoldContainer)
                        .border(1.dp, GoldPrimary, CircleShape)
                        .testTag("ask_ai_guru_shortcut")
                ) {
                    Icon(Icons.Default.SmartToy, contentDescription = "Ask AI", tint = GoldPrimary, modifier = Modifier.size(18.dp))
                }
            }
        }

        // Horizontal Phase Selector Tabs
        item {
            ScrollableTabRow(
                selectedTabIndex = when (activeStage) {
                    "learn" -> 0
                    "practice" -> 1
                    "project" -> 2
                    "portfolio" -> 3
                    else -> 4
                },
                containerColor = ObsidianSurface,
                contentColor = GoldPrimary,
                edgePadding = 8.dp,
                indicator = {},
                divider = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .border(1.dp, ObsidianCardBorder, RoundedCornerShape(12.dp))
            ) {
                listOf(
                    Pair("learn", "1. Learn (HD Video)"),
                    Pair("practice", "2. Practice (Quiz & Notes)"),
                    Pair("project", "3. Client Project"),
                    Pair("portfolio", "4. Portfolio"),
                    Pair("earn", "5. Earn & Gigs")
                ).forEach { (stageKey, title) ->
                    val isSelected = activeStage == stageKey
                    val isDone = completedStages.contains(stageKey)

                    Tab(
                        selected = isSelected,
                        onClick = { onStageSelect(stageKey) },
                        modifier = Modifier
                            .padding(horizontal = 4.dp, vertical = 6.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(
                                if (isSelected) GoldPrimary.copy(alpha = 0.2f)
                                else Color.Transparent
                            )
                            .border(
                                width = 1.dp,
                                color = if (isSelected) GoldPrimary else Color.Transparent,
                                shape = RoundedCornerShape(8.dp)
                            )
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
                        ) {
                            if (isDone) {
                                Icon(Icons.Default.CheckCircle, contentDescription = null, tint = EmeraldEarn, modifier = Modifier.size(14.dp))
                                Spacer(modifier = Modifier.width(4.dp))
                            }
                            Text(
                                text = title,
                                fontSize = 11.sp,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                                color = if (isSelected) GoldPrimary else if (isDone) EmeraldEarn else TextGrayMuted
                            )
                        }
                    }
                }
            }
        }

        // Stage Content Switcher
        when (activeStage) {
            "learn" -> {
                // Video Player UI
                item {
                    VideoLecturePlayer(
                        learn = track.stages.learn,
                        isPlaying = isVideoPlaying,
                        currentSeconds = videoCurrentSeconds,
                        speed = videoPlaybackSpeed,
                        onTogglePlay = onTogglePlay,
                        onSeek = onSeek,
                        onSetSpeed = onSetSpeed
                    )
                }

                // Chapters & Timestamps
                item {
                    Card(
                        colors = CardDefaults.cardColors(containerColor = ObsidianSurface),
                        shape = RoundedCornerShape(14.dp),
                        border = CardDefaults.outlinedCardBorder().copy(brush = Brush.horizontalGradient(listOf(ObsidianCardBorder, ObsidianSurfaceVariant))),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(14.dp)) {
                            Text(
                                text = "LECTURE CHAPTERS & TIMESTAMPS",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = GoldPrimary,
                                letterSpacing = 1.sp
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            track.stages.learn.videoChapters.forEach { chapter ->
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 4.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = chapter.timestamp,
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = GoldSecondary,
                                        modifier = Modifier.width(48.dp)
                                    )
                                    Text(
                                        text = chapter.title,
                                        fontSize = 12.sp,
                                        color = TextWhite,
                                        fontWeight = FontWeight.Medium
                                    )
                                }
                            }
                        }
                    }
                }

                // Hindi Transcript & Key Concepts
                item {
                    Card(
                        colors = CardDefaults.cardColors(containerColor = ObsidianSurface),
                        shape = RoundedCornerShape(14.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(14.dp)) {
                            Text(
                                text = "AUDIO TRANSCRIPT EXCERPT (HINDI)",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = TextGrayMuted,
                                letterSpacing = 1.sp
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = track.stages.learn.transcriptHindiExcerpt,
                                fontSize = 13.sp,
                                color = TextGrayLight,
                                lineHeight = 19.sp
                            )

                            Spacer(modifier = Modifier.height(12.dp))
                            Text(
                                text = "KEY CONCEPTS MASTERED:",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = GoldPrimary,
                                letterSpacing = 1.sp
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            track.stages.learn.keyConcepts.forEach { concept ->
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.padding(vertical = 2.dp)
                                ) {
                                    Icon(Icons.Default.Check, contentDescription = null, tint = EmeraldEarn, modifier = Modifier.size(14.dp))
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text(text = concept, fontSize = 12.sp, color = TextWhite)
                                }
                            }
                        }
                    }
                }

                // Complete Stage Button
                item {
                    val isDone = completedStages.contains("learn")
                    GoldGradientButton(
                        text = if (isDone) "Learn Phase Completed ✓" else "Mark Learn Phase as Completed (+100 XP)",
                        icon = Icons.Default.CheckCircle,
                        onClick = {
                            onMarkStageComplete("learn", 100)
                            onStageSelect("practice")
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("mark_learn_done_button")
                    )
                }
            }

            "practice" -> {
                // Interactive Infographic Section
                item {
                    Text(
                        text = "INTERACTIVE INFOGRAPHIC & BLUEPRINT",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = GoldPrimary,
                        letterSpacing = 1.sp
                    )
                }

                item {
                    InfographicDiagramView(
                        title = track.stages.practice.infographicTitle,
                        description = track.stages.practice.infographicDescription,
                        steps = track.stages.practice.infographicDiagramSteps
                    )
                }

                // Downloadable Notes & Cheatsheet
                item {
                    val isDownloaded = downloadedNotes.contains(track.stages.practice.downloadableNotesTitle)
                    Card(
                        colors = CardDefaults.cardColors(containerColor = ObsidianSurface),
                        shape = RoundedCornerShape(14.dp),
                        border = CardDefaults.outlinedCardBorder().copy(brush = Brush.horizontalGradient(listOf(GoldPrimary.copy(alpha = 0.4f), ObsidianCardBorder))),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(14.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(Icons.Default.PictureAsPdf, contentDescription = "PDF", tint = ErrorRed, modifier = Modifier.size(24.dp))
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Column {
                                        Text(
                                            text = track.stages.practice.downloadableNotesTitle,
                                            fontSize = 13.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = TextWhite
                                        )
                                        Text(
                                            text = track.stages.practice.downloadableNotesSize,
                                            fontSize = 10.sp,
                                            color = TextGrayMuted
                                        )
                                    }
                                }

                                Button(
                                    onClick = { onDownloadNotes(track.stages.practice.downloadableNotesTitle) },
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = if (isDownloaded) EmeraldContainer else GoldPrimary,
                                        contentColor = if (isDownloaded) EmeraldEarn else ObsidianBackground
                                    ),
                                    shape = RoundedCornerShape(8.dp),
                                    contentPadding = PaddingValues(horizontal = 12.dp, vertical = 4.dp),
                                    modifier = Modifier.testTag("download_notes_button")
                                ) {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Icon(
                                            imageVector = if (isDownloaded) Icons.Default.Check else Icons.Default.Download,
                                            contentDescription = null,
                                            modifier = Modifier.size(14.dp)
                                        )
                                        Spacer(modifier = Modifier.width(4.dp))
                                        Text(
                                            text = if (isDownloaded) "Downloaded" else "Download PDF",
                                            fontSize = 11.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                    }
                                }
                            }

                            Spacer(modifier = Modifier.height(10.dp))
                            Text(
                                text = "What's inside this PDF:",
                                fontSize = 11.sp,
                                color = GoldSecondary,
                                fontWeight = FontWeight.SemiBold
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            track.stages.practice.downloadableNotesKeyPoints.forEach { point ->
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.padding(vertical = 2.dp)
                                ) {
                                    Text("•", color = GoldPrimary, fontSize = 12.sp)
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text(text = point, fontSize = 11.sp, color = TextGrayLight)
                                }
                            }
                        }
                    }
                }

                // Interactive Quiz & Assignment
                item {
                    Text(
                        text = "QUIZ & KNOWLEDGE CHECK (HINDI)",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = GoldPrimary,
                        letterSpacing = 1.sp
                    )
                }

                val questions = track.stages.practice.quizQuestions
                items(questions) { question ->
                    QuizQuestionCard(
                        question = question,
                        selectedOptionIndex = userQuizAnswers[question.id],
                        isSubmitted = isQuizSubmitted,
                        onOptionSelected = { idx -> onSelectQuizAnswer(question.id, idx) }
                    )
                }

                // Quiz Submission / Action
                item {
                    if (!isQuizSubmitted) {
                        val allAnswered = questions.all { userQuizAnswers.containsKey(it.id) }
                        GoldGradientButton(
                            text = "Submit Quiz & Verify Answers (+150 XP)",
                            icon = Icons.AutoMirrored.Filled.Send,
                            enabled = allAnswered,
                            onClick = {
                                val correctCount = questions.count { q -> userQuizAnswers[q.id] == q.correctIndex }
                                onSubmitQuiz(questions.size, correctCount)
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("submit_quiz_button")
                        )
                    } else {
                        Button(
                            onClick = onResetQuiz,
                            colors = ButtonDefaults.buttonColors(containerColor = ObsidianSurfaceVariant, contentColor = TextWhite),
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Retake Quiz", fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }

            "project" -> {
                // Real-World Client Project
                item {
                    Card(
                        colors = CardDefaults.cardColors(containerColor = ObsidianSurface),
                        shape = RoundedCornerShape(16.dp),
                        border = CardDefaults.outlinedCardBorder().copy(brush = Brush.linearGradient(listOf(AmberAccent.copy(alpha = 0.5f), ObsidianCardBorder))),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.Top
                            ) {
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        text = "REAL-WORLD CLIENT ASSIGNMENT",
                                        fontSize = 10.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = AmberAccent,
                                        letterSpacing = 1.sp
                                    )
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(
                                        text = track.stages.project.projectTitle,
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = TextWhite
                                    )
                                }
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(8.dp))
                                        .background(Color(0xFF2E2005))
                                        .border(1.dp, AmberAccent, RoundedCornerShape(8.dp))
                                        .padding(horizontal = 8.dp, vertical = 4.dp)
                                ) {
                                    Text(
                                        text = track.stages.project.estimatedProjectValue,
                                        fontSize = 10.sp,
                                        color = AmberAccent,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(10.dp))
                            Text(
                                text = "Client Context: ${track.stages.project.clientContext}",
                                fontSize = 12.sp,
                                color = GoldSecondary,
                                fontWeight = FontWeight.Medium
                            )

                            Spacer(modifier = Modifier.height(10.dp))
                            Text(
                                text = "समस्या और आवश्यकता (Problem Statement):",
                                fontSize = 11.sp,
                                color = TextGrayMuted,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = track.stages.project.problemStatementHindi,
                                fontSize = 13.sp,
                                color = TextWhite,
                                lineHeight = 18.sp
                            )

                            Spacer(modifier = Modifier.height(14.dp))
                            Text(
                                text = "DELIVERABLE CHECKLIST:",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = GoldPrimary,
                                letterSpacing = 1.sp
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            track.stages.project.deliverableChecklist.forEach { task ->
                                Row(
                                    verticalAlignment = Alignment.Top,
                                    modifier = Modifier.padding(vertical = 4.dp)
                                ) {
                                    Icon(Icons.Default.CheckCircleOutline, contentDescription = null, tint = EmeraldEarn, modifier = Modifier.size(16.dp))
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(text = task, fontSize = 12.sp, color = TextGrayLight)
                                }
                            }

                            Spacer(modifier = Modifier.height(12.dp))
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(ObsidianSurfaceVariant)
                                    .padding(10.dp)
                            ) {
                                Text(
                                    text = "🎯 Expected Outcome: ${track.stages.project.expectedOutcome}",
                                    fontSize = 11.sp,
                                    color = EmeraldEarn,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                        }
                    }
                }

                // Project Completion Action
                item {
                    val isDone = completedStages.contains("project")
                    GoldGradientButton(
                        text = if (isDone) "Project Completed ✓" else "Mark Project Complete & Add to Portfolio (+200 XP)",
                        icon = Icons.Default.CloudUpload,
                        onClick = {
                            onMarkStageComplete("project", 200)
                            onStageSelect("portfolio")
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("mark_project_done_button")
                    )
                }
            }

            "portfolio" -> {
                // Portfolio Showcase Card
                item {
                    Card(
                        colors = CardDefaults.cardColors(containerColor = ObsidianSurface),
                        shape = RoundedCornerShape(16.dp),
                        border = CardDefaults.outlinedCardBorder().copy(brush = Brush.linearGradient(listOf(PurpleAccent.copy(alpha = 0.5f), ObsidianCardBorder))),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                text = "PORTFOLIO PIECE READY TO SHOWCASE",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = PurpleAccent,
                                letterSpacing = 1.sp
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = track.stages.portfolio.portfolioPieceTitle,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = TextWhite
                            )

                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = track.stages.portfolio.mockupDescription,
                                fontSize = 12.sp,
                                color = TextGrayLight,
                                lineHeight = 16.sp
                            )

                            Spacer(modifier = Modifier.height(14.dp))
                            Text(
                                text = "CLIENT PITCH SCRIPT (HINDI/ENGLISH):",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = GoldPrimary,
                                letterSpacing = 1.sp
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(ObsidianSurfaceVariant)
                                    .padding(12.dp)
                            ) {
                                Text(
                                    text = track.stages.portfolio.clientPitchHindi,
                                    fontSize = 12.sp,
                                    color = TextWhite,
                                    lineHeight = 17.sp
                                )
                            }

                            Spacer(modifier = Modifier.height(14.dp))
                            Text(
                                text = "RECOMMENDED TAGS:",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = TextGrayMuted
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                track.stages.portfolio.recommendedTags.forEach { tag ->
                                    Box(
                                        modifier = Modifier
                                            .clip(RoundedCornerShape(6.dp))
                                            .background(Color(0xFF261238))
                                            .border(1.dp, PurpleAccent.copy(alpha = 0.5f), RoundedCornerShape(6.dp))
                                            .padding(horizontal = 8.dp, vertical = 4.dp)
                                    ) {
                                        Text(text = "#$tag", fontSize = 10.sp, color = PurpleAccent, fontWeight = FontWeight.Bold)
                                    }
                                }
                            }
                        }
                    }
                }

                // Add to live portfolio action
                item {
                    GoldGradientButton(
                        text = "Add Piece to Live Student Portfolio (+150 XP)",
                        icon = Icons.Default.AddToPhotos,
                        onClick = {
                            onAddToPortfolio(
                                track.stages.portfolio.portfolioPieceTitle,
                                track.title,
                                track.stages.portfolio.mockupDescription,
                                "Verified DSA Project",
                                track.stages.project.estimatedProjectValue
                            )
                            onMarkStageComplete("portfolio", 150)
                            onStageSelect("earn")
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("add_to_portfolio_button")
                    )
                }
            }

            "earn" -> {
                // Earn & Gig Roadmap
                item {
                    Card(
                        colors = CardDefaults.cardColors(containerColor = ObsidianSurface),
                        shape = RoundedCornerShape(16.dp),
                        border = CardDefaults.outlinedCardBorder().copy(brush = Brush.linearGradient(listOf(EmeraldEarn.copy(alpha = 0.5f), ObsidianCardBorder))),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                text = "MONETIZATION & GIG LAUNCH PAD",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = EmeraldEarn,
                                letterSpacing = 1.sp
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = track.stages.earn.gigTitleExample,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                color = TextWhite
                            )

                            Spacer(modifier = Modifier.height(12.dp))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Column {
                                    Text(text = "Beginner Pricing", fontSize = 10.sp, color = TextGrayMuted)
                                    Text(text = track.stages.earn.beginnerPriceRange, fontSize = 12.sp, fontWeight = FontWeight.Bold, color = GoldPrimary)
                                }
                                Column {
                                    Text(text = "Pro / Retainer Rate", fontSize = 10.sp, color = TextGrayMuted)
                                    Text(text = track.stages.earn.proPriceRange, fontSize = 12.sp, fontWeight = FontWeight.Bold, color = EmeraldEarn)
                                }
                            }

                            Spacer(modifier = Modifier.height(14.dp))
                            Text(
                                text = "UPWORK / LINKEDIN PROPOSAL SCRIPT (HINDI):",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = GoldPrimary,
                                letterSpacing = 1.sp
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(ObsidianSurfaceVariant)
                                    .padding(12.dp)
                            ) {
                                Text(
                                    text = track.stages.earn.proposalScriptHindi,
                                    fontSize = 12.sp,
                                    color = TextWhite,
                                    lineHeight = 17.sp
                                )
                            }

                            Spacer(modifier = Modifier.height(12.dp))
                            Button(
                                onClick = {
                                    val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                                    clipboard.setPrimaryClip(ClipData.newPlainText("DSA Proposal", track.stages.earn.proposalScriptHindi))
                                },
                                colors = ButtonDefaults.buttonColors(containerColor = ObsidianCardBorder, contentColor = GoldPrimary),
                                shape = RoundedCornerShape(8.dp),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Icon(Icons.Default.ContentCopy, contentDescription = "Copy", modifier = Modifier.size(14.dp))
                                Spacer(modifier = Modifier.width(6.dp))
                                Text("Copy Proposal Script to Clipboard", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                            }
                        }
                    }
                }

                // Complete Track & Claim Certificate
                item {
                    val isDone = completedStages.contains("earn")
                    GoldGradientButton(
                        text = if (isDone) "Track Fully Completed! Claim Certificate 🎓" else "Finish Zero to Hero Track & Unlock Certificate (+250 XP)",
                        icon = Icons.Default.WorkspacePremium,
                        onClick = {
                            onMarkStageComplete("earn", 250)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("finish_track_button")
                    )
                }
            }
        }

        item { Spacer(modifier = Modifier.height(24.dp)) }
    }
}

@Composable
fun VideoLecturePlayer(
    learn: com.example.data.model.LearnStage,
    isPlaying: Boolean,
    currentSeconds: Int,
    speed: String,
    onTogglePlay: () -> Unit,
    onSeek: (Int) -> Unit,
    onSetSpeed: (String) -> Unit
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color(0xFF0F111A)),
        shape = RoundedCornerShape(16.dp),
        border = CardDefaults.outlinedCardBorder().copy(brush = Brush.linearGradient(listOf(GoldPrimary.copy(alpha = 0.7f), ObsidianCardBorder))),
        modifier = Modifier
            .fillMaxWidth()
            .testTag("video_player_card")
    ) {
        Column {
            // Simulated Player Canvas
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(Brush.verticalGradient(listOf(Color(0xFF1E2235), Color(0xFF0A0C12)))),
                contentAlignment = Alignment.Center
            ) {
                // HD Badge & Instructor Tag
                Row(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(12.dp),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(4.dp))
                            .background(GoldPrimary)
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Text("1080p HD", fontSize = 9.sp, fontWeight = FontWeight.Black, color = ObsidianBackground)
                    }
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(4.dp))
                            .background(Color(0xFF222638))
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Text("Hindi Audio", fontSize = 9.sp, fontWeight = FontWeight.Bold, color = TextWhite)
                    }
                }

                // Play / Pause Circle in Center
                IconButton(
                    onClick = onTogglePlay,
                    modifier = Modifier
                        .size(60.dp)
                        .clip(CircleShape)
                        .background(GoldPrimary.copy(alpha = 0.9f))
                ) {
                    Icon(
                        imageVector = if (isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
                        contentDescription = "Play/Pause",
                        tint = ObsidianBackground,
                        modifier = Modifier.size(34.dp)
                    )
                }

                // Bottom Controls Bar inside Player
                Row(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                        .background(Color.Black.copy(alpha = 0.6f))
                        .padding(horizontal = 12.dp, vertical = 6.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        IconButton(onClick = { onSeek(-10) }, modifier = Modifier.size(28.dp)) {
                            Icon(Icons.Default.Replay10, contentDescription = "-10s", tint = TextWhite, modifier = Modifier.size(18.dp))
                        }
                        IconButton(onClick = { onSeek(10) }, modifier = Modifier.size(28.dp)) {
                            Icon(Icons.Default.Forward10, contentDescription = "+10s", tint = TextWhite, modifier = Modifier.size(18.dp))
                        }
                        Spacer(modifier = Modifier.width(6.dp))
                        val mins = currentSeconds / 60
                        val secs = currentSeconds % 60
                        Text(
                            text = "%02d:%02d / 42:15".format(mins, secs),
                            fontSize = 11.sp,
                            color = TextWhite,
                            fontWeight = FontWeight.Medium
                        )
                    }

                    // Speed Toggle
                    Row(
                        modifier = Modifier
                            .clip(RoundedCornerShape(6.dp))
                            .background(Color(0xFF2A2E40))
                            .clickable {
                                val next = when (speed) {
                                    "1.0x" -> "1.25x"
                                    "1.25x" -> "1.5x"
                                    "1.5x" -> "2.0x"
                                    else -> "1.0x"
                                }
                                onSetSpeed(next)
                            }
                            .padding(horizontal = 8.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = speed, fontSize = 11.sp, color = GoldPrimary, fontWeight = FontWeight.Bold)
                    }
                }
            }

            // Video Info
            Column(modifier = Modifier.padding(14.dp)) {
                Text(
                    text = learn.videoTitleHindi,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextWhite
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Instructor: ${learn.instructor}",
                    fontSize = 12.sp,
                    color = GoldSecondary
                )
            }
        }
    }
}

@Composable
fun InfographicDiagramView(
    title: String,
    description: String,
    steps: List<DiagramStep>
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = ObsidianSurface),
        shape = RoundedCornerShape(16.dp),
        border = CardDefaults.outlinedCardBorder().copy(brush = Brush.linearGradient(listOf(CyanAccent.copy(alpha = 0.5f), ObsidianCardBorder))),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Text(text = title, fontSize = 14.sp, fontWeight = FontWeight.Bold, color = TextWhite)
            Text(text = description, fontSize = 11.sp, color = TextGrayMuted)

            Spacer(modifier = Modifier.height(14.dp))
            steps.forEachIndexed { index, step ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    verticalAlignment = Alignment.Top
                ) {
                    Box(
                        modifier = Modifier
                            .size(28.dp)
                            .clip(CircleShape)
                            .background(Color(step.highlightColor).copy(alpha = 0.2f))
                            .border(1.dp, Color(step.highlightColor), CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "${step.stepNumber}",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(step.highlightColor)
                        )
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(text = step.title, fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color(step.highlightColor))
                        Text(text = step.description, fontSize = 11.sp, color = TextGrayLight, lineHeight = 15.sp)
                    }
                }
                if (index < steps.size - 1) {
                    Box(
                        modifier = Modifier
                            .padding(start = 13.dp)
                            .width(2.dp)
                            .height(14.dp)
                            .background(Color(step.highlightColor).copy(alpha = 0.4f))
                    )
                }
            }
        }
    }
}

@Composable
fun QuizQuestionCard(
    question: QuizQuestion,
    selectedOptionIndex: Int?,
    isSubmitted: Boolean,
    onOptionSelected: (Int) -> Unit
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = ObsidianSurface),
        shape = RoundedCornerShape(14.dp),
        border = CardDefaults.outlinedCardBorder().copy(brush = Brush.horizontalGradient(listOf(ObsidianCardBorder, ObsidianSurfaceVariant))),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Text(
                text = "Question ${question.id}: ${question.questionHindi}",
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = TextWhite
            )
            Text(
                text = question.questionEnglish,
                fontSize = 11.sp,
                color = TextGrayMuted
            )

            Spacer(modifier = Modifier.height(10.dp))
            question.options.forEachIndexed { index, option ->
                val isSelected = selectedOptionIndex == index
                val isCorrect = question.correctIndex == index

                val optionBgColor = when {
                    isSubmitted && isCorrect -> EmeraldContainer
                    isSubmitted && isSelected && !isCorrect -> Color(0xFF3B1214)
                    isSelected -> GoldContainer
                    else -> ObsidianSurfaceVariant
                }

                val optionBorderColor = when {
                    isSubmitted && isCorrect -> EmeraldEarn
                    isSubmitted && isSelected && !isCorrect -> ErrorRed
                    isSelected -> GoldPrimary
                    else -> ObsidianCardBorder
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(optionBgColor)
                        .border(1.dp, optionBorderColor, RoundedCornerShape(8.dp))
                        .clickable(enabled = !isSubmitted) { onOptionSelected(index) }
                        .padding(horizontal = 12.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = isSelected,
                        onClick = if (!isSubmitted) { { onOptionSelected(index) } } else null,
                        colors = RadioButtonDefaults.colors(
                            selectedColor = if (isSubmitted && isCorrect) EmeraldEarn else GoldPrimary,
                            unselectedColor = TextGrayDark
                        )
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = option,
                        fontSize = 12.sp,
                        color = TextWhite,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                    )
                }
            }

            if (isSubmitted) {
                Spacer(modifier = Modifier.height(8.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                        .background(ObsidianSurfaceVariant)
                        .padding(8.dp)
                ) {
                    Text(
                        text = "💡 व्याख्या (Explanation): ${question.explanationHindi}",
                        fontSize = 11.sp,
                        color = EmeraldEarn,
                        lineHeight = 15.sp
                    )
                }
            }
        }
    }
}
