package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.local.TrackProgressEntity
import com.example.data.local.UserProgressEntity
import com.example.data.model.LearningTrack
import com.example.data.repository.TrackDataRepository
import com.example.ui.components.GoldGradientButton
import com.example.ui.theme.*
import com.example.ui.viewmodel.AppScreen

@Composable
fun HomeScreen(
    userProgress: UserProgressEntity?,
    trackProgressList: List<TrackProgressEntity>,
    onSelectTrack: (String) -> Unit,
    onNavigate: (AppScreen) -> Unit,
    onStartLesson: (String, String) -> Unit,
    modifier: Modifier = Modifier
) {
    val tracks = TrackDataRepository.tracks
    val activeTrack = tracks.first() // AI & Automation default

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(ObsidianBackground)
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item { Spacer(modifier = Modifier.height(4.dp)) }

        // Hero Dashboard Card: Level, Streak & Monthly Earning Progress
        item {
            Card(
                colors = CardDefaults.cardColors(containerColor = ObsidianSurface),
                shape = RoundedCornerShape(18.dp),
                border = CardDefaults.outlinedCardBorder().copy(
                    brush = Brush.linearGradient(
                        listOf(GoldPrimary.copy(alpha = 0.6f), EmeraldEarn.copy(alpha = 0.3f), ObsidianCardBorder)
                    )
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("hero_dashboard_card")
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = "स्वागत है, ${userProgress?.userName ?: "Aarav"}! 🚀",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = TextWhite
                            )
                            Text(
                                text = userProgress?.userRole ?: "Zero to Hero Aspirant",
                                fontSize = 12.sp,
                                color = GoldPrimary,
                                fontWeight = FontWeight.Medium
                            )
                        }

                        // Level Badge
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(12.dp))
                                .background(GoldContainer)
                                .border(1.dp, GoldPrimary, RoundedCornerShape(12.dp))
                                .padding(horizontal = 10.dp, vertical = 6.dp)
                        ) {
                            Text(
                                text = "LEVEL ${userProgress?.level ?: 3}",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Black,
                                color = GoldPrimary
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Monthly Earning Goal Visualizer
                    val currentEarn = userProgress?.currentEstimatedEarnings ?: 32000
                    val goalEarn = userProgress?.targetMonthlyGoal ?: 75000
                    val progressFloat = (currentEarn.toFloat() / goalEarn.toFloat()).coerceIn(0f, 1f)

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Bottom
                    ) {
                        Column {
                            Text(
                                text = "Monthly Earning Goal (₹)",
                                fontSize = 11.sp,
                                color = TextGrayMuted
                            )
                            Text(
                                text = "₹${"%,d".format(currentEarn)} / ₹${"%,d".format(goalEarn)}",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = EmeraldEarn
                            )
                        }
                        Text(
                            text = "${(progressFloat * 100).toInt()}% Achieved",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = GoldSecondary
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                    LinearProgressIndicator(
                        progress = { progressFloat },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(8.dp)
                            .clip(RoundedCornerShape(4.dp)),
                        color = EmeraldEarn,
                        trackColor = ObsidianSurfaceVariant
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    // 5-Stage Banner
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(10.dp))
                            .background(ObsidianSurfaceVariant)
                            .padding(horizontal = 10.dp, vertical = 8.dp),
                        horizontalArrangement = Arrangement.SpaceAround,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        listOf("Learn", "Practice", "Project", "Portfolio", "Earn").forEachIndexed { i, stage ->
                            Text(
                                text = stage,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = if (i == 4) EmeraldEarn else if (i == 0) GoldPrimary else TextGrayLight
                            )
                            if (i < 4) {
                                Text(text = "→", fontSize = 11.sp, color = TextGrayDark)
                            }
                        }
                    }
                }
            }
        }

        // Continue Learning Card
        item {
            Card(
                colors = CardDefaults.cardColors(containerColor = Color(0xFF141724)),
                shape = RoundedCornerShape(16.dp),
                border = CardDefaults.outlinedCardBorder().copy(brush = Brush.horizontalGradient(listOf(GoldPrimary.copy(alpha = 0.5f), ObsidianCardBorder))),
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onSelectTrack("ai-automation") }
                    .testTag("continue_learning_card")
            ) {
                Row(
                    modifier = Modifier.padding(14.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(46.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(GoldContainer),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.PlayCircleFilled,
                            contentDescription = "Resume",
                            tint = GoldPrimary,
                            modifier = Modifier.size(28.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "CONTINUE LEARNING",
                            fontSize = 9.sp,
                            fontWeight = FontWeight.Bold,
                            color = GoldSecondary,
                            letterSpacing = 1.sp
                        )
                        Text(
                            text = activeTrack.title,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = TextWhite
                        )
                        Text(
                            text = activeTrack.subtitleHindi,
                            fontSize = 11.sp,
                            color = TextGrayMuted,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                    Button(
                        onClick = { onStartLesson("ai-automation", "learn") },
                        colors = ButtonDefaults.buttonColors(containerColor = GoldPrimary, contentColor = ObsidianBackground),
                        shape = RoundedCornerShape(10.dp),
                        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp)
                    ) {
                        Text("Resume", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }

        // Section Title: 8 High-Income Learning Tracks
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "8 High-Income Tracks (Zero to Hero)",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextWhite
                    )
                    Text(
                        text = "हिंदी में सीखें • हैंड्स-ऑन प्रोजेक्ट्स • सीधे क्लाइंट्स",
                        fontSize = 11.sp,
                        color = GoldSecondary
                    )
                }
            }
        }

        // Tracks List
        items(tracks.size) { index ->
            val track = tracks[index]
            val progress = trackProgressList.find { it.trackId == track.id }
            val completedCount = progress?.completedStages?.split(",")?.filter { it.isNotBlank() }?.size ?: 0
            val isCertEarned = progress?.isCertificateClaimed ?: false

            TrackListItemCard(
                track = track,
                completedStagesCount = completedCount,
                isCertificateEarned = isCertEarned,
                onClick = { onSelectTrack(track.id) }
            )
        }

        // Quick Tools & Accelerator Bar
        item {
            Card(
                colors = CardDefaults.cardColors(containerColor = ObsidianSurface),
                shape = RoundedCornerShape(16.dp),
                border = CardDefaults.outlinedCardBorder().copy(brush = Brush.linearGradient(listOf(ObsidianCardBorder, GoldPrimary.copy(alpha = 0.3f)))),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Text(
                        text = "FREELANCE ACCELERATOR TOOLS",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = GoldPrimary,
                        letterSpacing = 1.sp
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        QuickActionTile(
                            icon = Icons.Default.SmartToy,
                            label = "DSA AI Guru",
                            badge = "Instant Doubt",
                            onClick = { onNavigate(AppScreen.AI_MENTOR) }
                        )
                        QuickActionTile(
                            icon = Icons.Default.Calculate,
                            label = "Rate Calc",
                            badge = "Earn More",
                            onClick = { onNavigate(AppScreen.EARNING_HUB) }
                        )
                        QuickActionTile(
                            icon = Icons.Default.WorkOutline,
                            label = "Live Gigs",
                            badge = "Apply Now",
                            onClick = { onNavigate(AppScreen.MARKETPLACE_GIGS) }
                        )
                        QuickActionTile(
                            icon = Icons.Default.Verified,
                            label = "Certificates",
                            badge = "Verified",
                            onClick = { onNavigate(AppScreen.CERTIFICATES) }
                        )
                    }
                }
            }
        }

        item { Spacer(modifier = Modifier.height(20.dp)) }
    }
}

@Composable
fun TrackListItemCard(
    track: LearningTrack,
    completedStagesCount: Int,
    isCertificateEarned: Boolean,
    onClick: () -> Unit
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = ObsidianSurface),
        shape = RoundedCornerShape(16.dp),
        border = CardDefaults.outlinedCardBorder().copy(brush = Brush.linearGradient(listOf(ObsidianCardBorder, ObsidianSurfaceVariant))),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .testTag("track_item_${track.id}")
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Row(modifier = Modifier.weight(1f), verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(42.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(
                                Brush.linearGradient(
                                    listOf(GoldPrimary.copy(alpha = 0.8f), GoldDark)
                                )
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = when (track.id) {
                                "ai-automation" -> Icons.Default.SmartToy
                                "graphic-design-canva" -> Icons.Default.Palette
                                "digital-marketing" -> Icons.Default.Campaign
                                "data-analytics" -> Icons.Default.BarChart
                                "freelancing" -> Icons.Default.AttachMoney
                                "personal-branding" -> Icons.Default.Person
                                "digital-products" -> Icons.Default.Storefront
                                else -> Icons.Default.Code
                            },
                            contentDescription = track.title,
                            tint = ObsidianBackground,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text(
                            text = track.title,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = TextWhite
                        )
                        Text(
                            text = track.subtitleHindi,
                            fontSize = 11.sp,
                            color = GoldSecondary,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }

                if (isCertificateEarned) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(EmeraldContainer)
                            .border(1.dp, EmeraldEarn, RoundedCornerShape(8.dp))
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.Verified, contentDescription = null, tint = EmeraldEarn, modifier = Modifier.size(12.dp))
                            Spacer(modifier = Modifier.width(2.dp))
                            Text("Certified", fontSize = 10.sp, color = EmeraldEarn, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = track.description,
                fontSize = 12.sp,
                color = TextGrayLight,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                lineHeight = 16.sp
            )

            Spacer(modifier = Modifier.height(12.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Earning Potential Badge
                Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color(0xFF0D2517))
                        .border(1.dp, EmeraldEarn.copy(alpha = 0.5f), RoundedCornerShape(8.dp))
                        .padding(horizontal = 8.dp, vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Payments,
                        contentDescription = null,
                        tint = EmeraldEarn,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = track.earningPotential,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = EmeraldEarn
                    )
                }

                // Progress Step Pill
                Text(
                    text = "$completedStagesCount / 5 Stages",
                    fontSize = 11.sp,
                    color = if (completedStagesCount > 0) GoldPrimary else TextGrayMuted,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun QuickActionTile(
    icon: ImageVector,
    label: String,
    badge: String,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .clickable { onClick() }
            .padding(6.dp)
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(ObsidianSurfaceVariant)
                .border(1.dp, ObsidianCardBorder, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = GoldPrimary,
                modifier = Modifier.size(20.dp)
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = label, fontSize = 11.sp, fontWeight = FontWeight.Bold, color = TextWhite)
        Text(text = badge, fontSize = 9.sp, color = EmeraldEarn, fontWeight = FontWeight.Medium)
    }
}
