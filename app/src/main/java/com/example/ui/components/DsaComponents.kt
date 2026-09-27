package com.example.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.*
import com.example.ui.viewmodel.AppScreen

@Composable
fun DsaTopBar(
    xp: Int,
    streak: Int,
    onCertificateClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        color = ObsidianSurface,
        modifier = modifier
            .fillMaxWidth()
            .border(
                width = 0.5.dp,
                color = ObsidianCardBorder,
                shape = RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp)
            ),
        shadowElevation = 4.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Brand Logo & Tagline
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(38.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(
                            Brush.linearGradient(
                                listOf(GoldPrimary, GoldDark)
                            )
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "DSA",
                        fontWeight = FontWeight.Black,
                        fontSize = 13.sp,
                        color = ObsidianBackground
                    )
                }
                Spacer(modifier = Modifier.width(10.dp))
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "DSA",
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            color = GoldPrimary
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "Learn & Earn",
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            color = TextWhite
                        )
                    }
                    Text(
                        text = "Learn → Build → Earn → Grow",
                        fontSize = 9.sp,
                        fontWeight = FontWeight.Medium,
                        color = GoldSecondary
                    )
                }
            }

            // Gamification Badges: Streak & XP
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Streak Badge
                Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .background(Color(0xFF2B1C0B))
                        .border(1.dp, AmberAccent.copy(alpha = 0.6f), RoundedCornerShape(20.dp))
                        .padding(horizontal = 8.dp, vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.LocalFireDepartment,
                        contentDescription = "Streak",
                        tint = AmberAccent,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(2.dp))
                    Text(
                        text = "${streak}d",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = AmberAccent
                    )
                }

                // XP Badge
                Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .background(GoldContainer)
                        .border(1.dp, GoldPrimary.copy(alpha = 0.5f), RoundedCornerShape(20.dp))
                        .padding(horizontal = 8.dp, vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Stars,
                        contentDescription = "XP",
                        tint = GoldPrimary,
                        modifier = Modifier.size(15.dp)
                    )
                    Spacer(modifier = Modifier.width(3.dp))
                    Text(
                        text = "${xp} XP",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = GoldPrimary
                    )
                }

                // Certificate Action
                IconButton(
                    onClick = onCertificateClick,
                    modifier = Modifier
                        .size(34.dp)
                        .clip(CircleShape)
                        .background(ObsidianSurfaceVariant)
                        .testTag("top_bar_certificates_button")
                ) {
                    Icon(
                        imageVector = Icons.Default.WorkspacePremium,
                        contentDescription = "Certificates",
                        tint = GoldPrimary,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun DsaBottomNav(
    currentScreen: AppScreen,
    onNavigate: (AppScreen) -> Unit,
    modifier: Modifier = Modifier
) {
    NavigationBar(
        containerColor = ObsidianSurface,
        contentColor = TextWhite,
        tonalElevation = 8.dp,
        modifier = modifier.border(0.5.dp, ObsidianCardBorder, RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
    ) {
        val navItems = listOf(
            Triple(AppScreen.HOME, "Home", Icons.Default.Home),
            Triple(AppScreen.AI_MENTOR, "AI Guru", Icons.Default.Psychology),
            Triple(AppScreen.EARNING_HUB, "Earn Hub", Icons.Default.MonetizationOn),
            Triple(AppScreen.MARKETPLACE_GIGS, "Gigs", Icons.Default.Work),
            Triple(AppScreen.COMMUNITY, "Charcha", Icons.Default.Forum)
        )

        navItems.forEach { (screen, label, icon) ->
            val isSelected = currentScreen == screen
            NavigationBarItem(
                selected = isSelected,
                onClick = { onNavigate(screen) },
                icon = {
                    Icon(
                        imageVector = icon,
                        contentDescription = label,
                        modifier = Modifier.size(22.dp)
                    )
                },
                label = {
                    Text(
                        text = label,
                        fontSize = 10.sp,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = ObsidianBackground,
                    selectedTextColor = GoldPrimary,
                    indicatorColor = GoldPrimary,
                    unselectedIconColor = TextGrayMuted,
                    unselectedTextColor = TextGrayMuted
                ),
                modifier = Modifier.testTag("nav_item_${screen.name.lowercase()}")
            )
        }
    }
}

@Composable
fun StageWorkflowStepper(
    completedStages: Set<String>,
    activeStage: String,
    onStageClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val stages = listOf(
        Pair("learn", "1. Learn"),
        Pair("practice", "2. Practice"),
        Pair("project", "3. Project"),
        Pair("portfolio", "4. Portfolio"),
        Pair("earn", "5. Earn")
    )

    Card(
        colors = CardDefaults.cardColors(containerColor = ObsidianSurface),
        shape = RoundedCornerShape(14.dp),
        border = CardDefaults.outlinedCardBorder().copy(brush = Brush.horizontalGradient(listOf(GoldPrimary.copy(alpha = 0.4f), EmeraldEarn.copy(alpha = 0.4f)))),
        modifier = modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(
                text = "ZERO TO HERO FRAMEWORK",
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                color = GoldPrimary,
                letterSpacing = 1.sp
            )
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                stages.forEachIndexed { index, (key, label) ->
                    val isDone = completedStages.contains(key)
                    val isActive = activeStage == key

                    val circleColor by animateColorAsState(
                        targetValue = when {
                            isDone -> EmeraldEarn
                            isActive -> GoldPrimary
                            else -> ObsidianSurfaceVariant
                        }
                    )

                    val textColor = when {
                        isActive -> GoldPrimary
                        isDone -> EmeraldEarn
                        else -> TextGrayDark
                    }

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .clickable { onStageClick(key) }
                            .padding(horizontal = 4.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(28.dp)
                                .clip(CircleShape)
                                .background(circleColor)
                                .border(
                                    width = if (isActive) 2.dp else 1.dp,
                                    color = if (isActive) GoldLight else Color.Transparent,
                                    shape = CircleShape
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            if (isDone) {
                                Icon(
                                    imageVector = Icons.Default.Check,
                                    contentDescription = "Done",
                                    tint = ObsidianBackground,
                                    modifier = Modifier.size(16.dp)
                                )
                            } else {
                                Text(
                                    text = "${index + 1}",
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = if (isActive) ObsidianBackground else TextWhite
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = label.substringAfter(". "),
                            fontSize = 10.sp,
                            fontWeight = if (isActive) FontWeight.Bold else FontWeight.Medium,
                            color = textColor
                        )
                    }

                    if (index < stages.size - 1) {
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .height(2.dp)
                                .padding(horizontal = 2.dp)
                                .background(if (isDone) EmeraldEarn.copy(alpha = 0.8f) else ObsidianCardBorder)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun GoldGradientButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: ImageVector? = null,
    enabled: Boolean = true
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = ObsidianBackground
        ),
        contentPadding = PaddingValues(0.dp),
        modifier = modifier
            .height(48.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(
                Brush.horizontalGradient(
                    listOf(GoldPrimary, GoldDark)
                )
            )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            if (icon != null) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = ObsidianBackground,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
            }
            Text(
                text = text,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                color = ObsidianBackground
            )
        }
    }
}
