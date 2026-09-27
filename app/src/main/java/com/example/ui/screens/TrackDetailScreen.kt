package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.local.TrackProgressEntity
import com.example.data.model.LearningTrack
import com.example.ui.components.GoldGradientButton
import com.example.ui.components.StageWorkflowStepper
import com.example.ui.theme.*

@Composable
fun TrackDetailScreen(
    track: LearningTrack,
    trackProgress: TrackProgressEntity?,
    onBack: () -> Unit,
    onOpenStage: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val completedStages = trackProgress?.completedStages?.split(",")?.filter { it.isNotBlank() }?.toSet()
        ?: emptySet()

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(ObsidianBackground)
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item { Spacer(modifier = Modifier.height(4.dp)) }

        // Top Navigation Header
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = onBack,
                    modifier = Modifier
                        .size(38.dp)
                        .clip(CircleShape)
                        .background(ObsidianSurface)
                        .testTag("track_detail_back_button")
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = GoldPrimary
                    )
                }
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(
                        text = track.title,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextWhite
                    )
                    Text(
                        text = track.level,
                        fontSize = 11.sp,
                        color = GoldSecondary,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }

        // Track Overview Hero
        item {
            Card(
                colors = CardDefaults.cardColors(containerColor = ObsidianSurface),
                shape = RoundedCornerShape(18.dp),
                border = CardDefaults.outlinedCardBorder().copy(brush = Brush.linearGradient(listOf(GoldPrimary.copy(alpha = 0.5f), ObsidianCardBorder))),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = track.subtitleHindi,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = GoldPrimary
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = track.description,
                        fontSize = 13.sp,
                        color = TextGrayLight,
                        lineHeight = 18.sp
                    )
                    Spacer(modifier = Modifier.height(14.dp))

                    // Metrics row
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        MetricPill(
                            icon = Icons.Default.Payments,
                            title = "Earning Potential",
                            value = track.earningPotential,
                            tint = EmeraldEarn
                        )
                        MetricPill(
                            icon = Icons.Default.Speed,
                            title = "Starting Rate",
                            value = track.averageStartingRate,
                            tint = GoldPrimary
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // Tools Covered
                    Text(
                        text = "TOOLS COVERED:",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextGrayMuted,
                        letterSpacing = 1.sp
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        track.toolsCovered.take(4).forEach { tool ->
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(6.dp))
                                    .background(ObsidianSurfaceVariant)
                                    .border(1.dp, ObsidianCardBorder, RoundedCornerShape(6.dp))
                                    .padding(horizontal = 8.dp, vertical = 4.dp)
                            ) {
                                Text(text = tool, fontSize = 10.sp, color = TextWhite, fontWeight = FontWeight.Medium)
                            }
                        }
                    }
                }
            }
        }

        // Stepper Overview
        item {
            StageWorkflowStepper(
                completedStages = completedStages,
                activeStage = "learn",
                onStageClick = { stageKey -> onOpenStage(stageKey) }
            )
        }

        // Section: 5 Detailed Modules
        item {
            Text(
                text = "COURSE CURRICULUM (5 PHASES)",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = GoldPrimary,
                letterSpacing = 1.sp
            )
        }

        // 1. Learn Module Card
        item {
            StageModuleCard(
                stageNumber = "1",
                stageTitle = "Learn (सीखें)",
                heading = track.stages.learn.videoTitleHindi,
                subtext = "${track.stages.learn.videoDuration} • Instructor: ${track.stages.learn.instructor}",
                icon = Icons.Default.PlayCircleFilled,
                isCompleted = completedStages.contains("learn"),
                accentColor = GoldPrimary,
                onClick = { onOpenStage("learn") }
            )
        }

        // 2. Practice Module Card
        item {
            StageModuleCard(
                stageNumber = "2",
                stageTitle = "Practice (प्रैक्टिस)",
                heading = track.stages.practice.infographicTitle,
                subtext = "Interactive Diagram + ${track.stages.practice.downloadableNotesTitle}",
                icon = Icons.Default.Quiz,
                isCompleted = completedStages.contains("practice"),
                accentColor = CyanAccent,
                onClick = { onOpenStage("practice") }
            )
        }

        // 3. Project Module Card
        item {
            StageModuleCard(
                stageNumber = "3",
                stageTitle = "Project (क्लाइंट प्रोजेक्ट)",
                heading = track.stages.project.projectTitle,
                subtext = "Estimated Project Value: ${track.stages.project.estimatedProjectValue}",
                icon = Icons.Default.Build,
                isCompleted = completedStages.contains("project"),
                accentColor = AmberAccent,
                onClick = { onOpenStage("project") }
            )
        }

        // 4. Portfolio Module Card
        item {
            StageModuleCard(
                stageNumber = "4",
                stageTitle = "Portfolio (लाइव पोर्टफोलियो)",
                heading = track.stages.portfolio.portfolioPieceTitle,
                subtext = "Showcase Case Study & Indian Client Pitch Script",
                icon = Icons.Default.FolderSpecial,
                isCompleted = completedStages.contains("portfolio"),
                accentColor = PurpleAccent,
                onClick = { onOpenStage("portfolio") }
            )
        }

        // 5. Earn Module Card
        item {
            StageModuleCard(
                stageNumber = "5",
                stageTitle = "Earn (कमाई शुरू करें)",
                heading = track.stages.earn.gigTitleExample,
                subtext = "Platforms: ${track.stages.earn.topPlatforms.joinToString(", ")}",
                icon = Icons.Default.MonetizationOn,
                isCompleted = completedStages.contains("earn"),
                accentColor = EmeraldEarn,
                onClick = { onOpenStage("earn") }
            )
        }

        // Bottom CTA
        item {
            GoldGradientButton(
                text = if (completedStages.isEmpty()) "Start Learning (Learn Phase)" else "Resume Next Stage",
                icon = Icons.Default.RocketLaunch,
                onClick = {
                    val next = listOf("learn", "practice", "project", "portfolio", "earn").find { !completedStages.contains(it) } ?: "learn"
                    onOpenStage(next)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("track_detail_resume_button")
            )
        }

        item { Spacer(modifier = Modifier.height(20.dp)) }
    }
}

@Composable
fun MetricPill(
    icon: ImageVector,
    title: String,
    value: String,
    tint: Color
) {
    Column {
        Text(text = title, fontSize = 10.sp, color = TextGrayMuted)
        Spacer(modifier = Modifier.height(2.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(imageVector = icon, contentDescription = null, tint = tint, modifier = Modifier.size(14.dp))
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = value, fontSize = 12.sp, fontWeight = FontWeight.Bold, color = tint)
        }
    }
}

@Composable
fun StageModuleCard(
    stageNumber: String,
    stageTitle: String,
    heading: String,
    subtext: String,
    icon: ImageVector,
    isCompleted: Boolean,
    accentColor: Color,
    onClick: () -> Unit
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = ObsidianSurface),
        shape = RoundedCornerShape(14.dp),
        border = CardDefaults.outlinedCardBorder().copy(
            brush = if (isCompleted) Brush.horizontalGradient(listOf(EmeraldEarn.copy(alpha = 0.5f), ObsidianCardBorder))
            else Brush.horizontalGradient(listOf(ObsidianCardBorder, ObsidianSurfaceVariant))
        ),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        Row(
            modifier = Modifier.padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(if (isCompleted) EmeraldContainer else ObsidianSurfaceVariant)
                    .border(1.dp, if (isCompleted) EmeraldEarn else accentColor, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                if (isCompleted) {
                    Icon(imageVector = Icons.Default.Check, contentDescription = "Done", tint = EmeraldEarn, modifier = Modifier.size(20.dp))
                } else {
                    Icon(imageVector = icon, contentDescription = null, tint = accentColor, modifier = Modifier.size(20.dp))
                }
            }

            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = stageTitle,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = accentColor
                    )
                    if (isCompleted) {
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(text = "• Done", fontSize = 10.sp, color = EmeraldEarn, fontWeight = FontWeight.Bold)
                    }
                }
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = heading,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextWhite,
                    maxLines = 1
                )
                Text(
                    text = subtext,
                    fontSize = 11.sp,
                    color = TextGrayMuted,
                    maxLines = 1
                )
            }
            Icon(
                imageVector = Icons.Default.ChevronRight,
                contentDescription = "Open",
                tint = TextGrayMuted,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}
