package com.example.ui.screens

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowRight
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.local.PortfolioProjectEntity
import com.example.data.model.EarningMilestone
import com.example.data.repository.TrackDataRepository
import com.example.ui.components.GoldGradientButton
import com.example.ui.theme.*

@Composable
fun EarningHubScreen(
    portfolioProjects: List<PortfolioProjectEntity>,
    onAddPortfolioProject: (String, String, String, String, String, String) -> Unit,
    onDeletePortfolioProject: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val roadmaps = TrackDataRepository.earningRoadmaps
    var selectedTab by remember { mutableStateOf(0) } // 0: Roadmaps, 1: Rate Calc, 2: Client Pitch System, 3: Portfolio Builder
    var showAddProjectDialog by remember { mutableStateOf(false) }

    // Rate Calculator State
    var calcSkillIndex by remember { mutableStateOf(0) }
    var calcHoursPerWeek by remember { mutableStateOf(15f) }
    var isInternationalClient by remember { mutableStateOf(false) }

    val skillRates = listOf(
        Pair("Graphic Design & Canva", Pair(800, 20)),     // ₹800/hr India, $20/hr Global
        Pair("AI & Automation", Pair(1500, 35)),            // ₹1500/hr India, $35/hr Global
        Pair("Meta Ads & Marketing", Pair(1200, 30)),       // ₹1200/hr India, $30/hr Global
        Pair("Data Analytics & Power BI", Pair(1400, 35)),  // ₹1400/hr India, $35/hr Global
        Pair("No-Code App Development", Pair(1800, 45))     // ₹1800/hr India, $45/hr Global
    )

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(ObsidianBackground)
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item { Spacer(modifier = Modifier.height(4.dp)) }

        // Header Title
        item {
            Column {
                Text(
                    text = "EARNING ACCELERATOR HUB",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = EmeraldEarn,
                    letterSpacing = 1.sp
                )
                Text(
                    text = "Zero to ₹1,00,000+ / Month Roadmap",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextWhite
                )
                Text(
                    text = "रोडमैप्स • क्लाइंट एक्विजिशन • प्राइसिंग कैलकुलेटर • पोर्टफोलियो",
                    fontSize = 11.sp,
                    color = GoldSecondary
                )
            }
        }

        // Subtabs: Roadmaps | Rate Calc | Pitch Vault | Portfolio
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(ObsidianSurface)
                    .border(1.dp, ObsidianCardBorder, RoundedCornerShape(12.dp))
                    .padding(4.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                listOf("Roadmaps", "Rate Calc", "Pitch Vault", "Portfolio").forEachIndexed { index, label ->
                    val isSelected = selectedTab == index
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(8.dp))
                            .background(if (isSelected) GoldPrimary else Color.Transparent)
                            .clickable { selectedTab = index }
                            .padding(vertical = 8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = label,
                            fontSize = 11.sp,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                            color = if (isSelected) ObsidianBackground else TextGrayLight
                        )
                    }
                }
            }
        }

        when (selectedTab) {
            0 -> {
                // Earning Roadmaps
                items(roadmaps) { roadmap ->
                    RoadmapMilestoneCard(roadmap = roadmap)
                }
            }

            1 -> {
                // Interactive Rate Calculator
                item {
                    Card(
                        colors = CardDefaults.cardColors(containerColor = ObsidianSurface),
                        shape = RoundedCornerShape(16.dp),
                        border = CardDefaults.outlinedCardBorder().copy(brush = Brush.linearGradient(listOf(EmeraldEarn.copy(alpha = 0.5f), ObsidianCardBorder))),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                text = "MONTHLY INCOME CALCULATOR",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = EmeraldEarn,
                                letterSpacing = 1.sp
                            )
                            Spacer(modifier = Modifier.height(10.dp))

                            // Skill Selector Chips
                            Text("Select Your Primary Skill:", fontSize = 11.sp, color = TextGrayLight)
                            Spacer(modifier = Modifier.height(6.dp))
                            skillRates.forEachIndexed { idx, (skillName, _) ->
                                val isChosen = calcSkillIndex == idx
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 3.dp)
                                        .clip(RoundedCornerShape(8.dp))
                                        .background(if (isChosen) GoldContainer else ObsidianSurfaceVariant)
                                        .border(1.dp, if (isChosen) GoldPrimary else ObsidianCardBorder, RoundedCornerShape(8.dp))
                                        .clickable { calcSkillIndex = idx }
                                        .padding(horizontal = 10.dp, vertical = 6.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    RadioButton(
                                        selected = isChosen,
                                        onClick = { calcSkillIndex = idx },
                                        colors = RadioButtonDefaults.colors(selectedColor = GoldPrimary)
                                    )
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text(text = skillName, fontSize = 12.sp, color = TextWhite, fontWeight = if (isChosen) FontWeight.Bold else FontWeight.Normal)
                                }
                            }

                            Spacer(modifier = Modifier.height(14.dp))
                            // Hours Slider
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text("Weekly Hours Dedicated:", fontSize = 11.sp, color = TextGrayLight)
                                Text("${calcHoursPerWeek.toInt()} hrs / week", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = GoldPrimary)
                            }
                            Slider(
                                value = calcHoursPerWeek,
                                onValueChange = { calcHoursPerWeek = it },
                                valueRange = 5f..40f,
                                steps = 7,
                                colors = SliderDefaults.colors(
                                    thumbColor = GoldPrimary,
                                    activeTrackColor = GoldPrimary,
                                    inactiveTrackColor = ObsidianCardBorder
                                )
                            )

                            Spacer(modifier = Modifier.height(8.dp))
                            // Client Geo Toggle
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column {
                                    Text("Client Market:", fontSize = 11.sp, color = TextGrayLight)
                                    Text(if (isInternationalClient) "Global / US / UK ($ USD)" else "Indian Domestic Market (₹ INR)", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = TextWhite)
                                }
                                Switch(
                                    checked = isInternationalClient,
                                    onCheckedChange = { isInternationalClient = it },
                                    colors = SwitchDefaults.colors(
                                        checkedThumbColor = EmeraldEarn,
                                        checkedTrackColor = EmeraldContainer
                                    )
                                )
                            }

                            Spacer(modifier = Modifier.height(16.dp))
                            // Result Box
                            val selectedRate = skillRates[calcSkillIndex].second
                            val estimatedMonthlyInr = if (!isInternationalClient) {
                                (selectedRate.first * calcHoursPerWeek * 4.2).toInt()
                            } else {
                                (selectedRate.second * 84 * calcHoursPerWeek * 4.2).toInt() // $ to INR ~84
                            }

                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(Color(0xFF072715))
                                    .border(1.5.dp, EmeraldEarn, RoundedCornerShape(12.dp))
                                    .padding(14.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    Text("POTENTIAL MONTHLY EARNING", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = EmeraldEarn, letterSpacing = 1.sp)
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(
                                        text = "₹${"%,d".format(estimatedMonthlyInr)} / mo",
                                        fontSize = 24.sp,
                                        fontWeight = FontWeight.Black,
                                        color = EmeraldEarn
                                    )
                                    Text(
                                        text = if (isInternationalClient) "Based on $${selectedRate.second}/hr global rate" else "Based on ₹${selectedRate.first}/hr domestic rate",
                                        fontSize = 10.sp,
                                        color = TextGrayMuted
                                    )
                                }
                            }
                        }
                    }
                }
            }

            2 -> {
                // Pitch Vault: High-Converting Templates
                item {
                    PitchVaultSection(context = context)
                }
            }

            3 -> {
                // Portfolio Builder
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "LIVE STUDENT PORTFOLIO (${portfolioProjects.size} Pieces)",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = GoldPrimary,
                            letterSpacing = 1.sp
                        )
                        Button(
                            onClick = { showAddProjectDialog = true },
                            colors = ButtonDefaults.buttonColors(containerColor = GoldPrimary, contentColor = ObsidianBackground),
                            shape = RoundedCornerShape(8.dp),
                            contentPadding = PaddingValues(horizontal = 10.dp, vertical = 4.dp),
                            modifier = Modifier.testTag("add_custom_project_button")
                        ) {
                            Icon(Icons.Default.Add, contentDescription = null, modifier = Modifier.size(14.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("Add Project", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }

                if (portfolioProjects.isEmpty()) {
                    item {
                        Card(
                            colors = CardDefaults.cardColors(containerColor = ObsidianSurface),
                            shape = RoundedCornerShape(14.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(
                                modifier = Modifier.padding(24.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Icon(Icons.Default.FolderOpen, contentDescription = null, tint = TextGrayDark, modifier = Modifier.size(48.dp))
                                Spacer(modifier = Modifier.height(8.dp))
                                Text("No Projects Added Yet", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = TextWhite)
                                Text("Complete track projects to automatically showcase your work to high-paying clients!", fontSize = 11.sp, color = TextGrayMuted, textAlign = androidx.compose.ui.text.style.TextAlign.Center)
                            }
                        }
                    }
                } else {
                    items(portfolioProjects) { project ->
                        PortfolioItemCard(
                            project = project,
                            onDelete = { onDeletePortfolioProject(project.id) }
                        )
                    }
                }
            }
        }

        item { Spacer(modifier = Modifier.height(24.dp)) }
    }

    // Add Project Dialog
    if (showAddProjectDialog) {
        AddProjectDialog(
            onDismiss = { showAddProjectDialog = false },
            onAdd = { title, cat, desc, client, value, link ->
                onAddPortfolioProject(title, cat, desc, client, value, link)
                showAddProjectDialog = false
            }
        )
    }
}

@Composable
fun RoadmapMilestoneCard(roadmap: EarningMilestone) {
    Card(
        colors = CardDefaults.cardColors(containerColor = ObsidianSurface),
        shape = RoundedCornerShape(16.dp),
        border = CardDefaults.outlinedCardBorder().copy(brush = Brush.linearGradient(listOf(GoldPrimary.copy(alpha = 0.5f), ObsidianCardBorder))),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = roadmap.levelName, fontSize = 14.sp, fontWeight = FontWeight.Bold, color = GoldPrimary)
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(EmeraldContainer)
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(text = roadmap.durationToAchieve, fontSize = 10.sp, color = EmeraldEarn, fontWeight = FontWeight.Bold)
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Target Income: ${roadmap.targetMonthlyIncome} (${roadmap.weeklyHoursRecommended} hrs/week)",
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                color = TextWhite
            )

            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "REQUIRED SKILLS:", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = TextGrayMuted)
            Spacer(modifier = Modifier.height(4.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                roadmap.skillsNeeded.forEach { skill ->
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(6.dp))
                            .background(ObsidianSurfaceVariant)
                            .padding(horizontal = 6.dp, vertical = 3.dp)
                    ) {
                        Text(text = skill, fontSize = 10.sp, color = TextGrayLight)
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))
            Text(text = "ACTIONABLE WEEKLY STEPS:", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = GoldSecondary)
            Spacer(modifier = Modifier.height(6.dp))
            roadmap.weeklyMilestones.forEach { step ->
                Row(
                    verticalAlignment = Alignment.Top,
                    modifier = Modifier.padding(vertical = 3.dp)
                ) {
                    Icon(Icons.AutoMirrored.Filled.ArrowRight, contentDescription = null, tint = EmeraldEarn, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = step, fontSize = 11.sp, color = TextGrayLight, lineHeight = 15.sp)
                }
            }
        }
    }
}

@Composable
fun PitchVaultSection(context: Context) {
    val pitches = listOf(
        Pair(
            "The 4-Sentence Upwork Winning Formula",
            "Hey [Client],\nI saw your job for [Task]. Instead of giving generic promises, here is a 60-second video showing how I'll solve it: [Loom Link].\nI recently delivered a similar solution that reduced processing time by 40%.\nWould love to deploy this for you today. Let's chat!"
        ),
        Pair(
            "Cold WhatsApp / Instagram DM to YouTubers",
            "नमस्ते [Name] भाई! आपका लेटेस्ट वीडियो बहुत इंफॉर्मेटिव था लेकिन थंबनेल में कंट्रास्ट कम होने से CTR 4-5% पर अटक सकता है। मैंने आपका थंबनेल Canva Pro में रीडिज़ाइन किया है (बिलकुल फ्री)। क्या मैं सैंपल भेज सकता हूँ?"
        ),
        Pair(
            "Local Real Estate Business Cold Email",
            "Subject: Quick idea to qualify leads in 30 seconds for [Agency Name]\n\nHi [Founder],\nNoticed you run active Meta Ads. Most agencies lose 40% leads due to delayed WhatsApp replies. I built an AI Make.com workflow that qualifies buyer budget in 30 seconds automatically. Here is a 90-second demo: [Link]. Mind if I share more details?"
        )
    )

    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        pitches.forEach { (title, script) ->
            Card(
                colors = CardDefaults.cardColors(containerColor = ObsidianSurface),
                shape = RoundedCornerShape(14.dp),
                border = CardDefaults.outlinedCardBorder().copy(brush = Brush.linearGradient(listOf(GoldPrimary.copy(alpha = 0.4f), ObsidianCardBorder))),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = title, fontSize = 13.sp, fontWeight = FontWeight.Bold, color = GoldPrimary)
                        IconButton(
                            onClick = {
                                val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                                clipboard.setPrimaryClip(ClipData.newPlainText(title, script))
                            },
                            modifier = Modifier.size(28.dp)
                        ) {
                            Icon(Icons.Default.ContentCopy, contentDescription = "Copy", tint = GoldSecondary, modifier = Modifier.size(16.dp))
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp))
                            .background(ObsidianSurfaceVariant)
                            .padding(10.dp)
                    ) {
                        Text(text = script, fontSize = 11.sp, color = TextWhite, lineHeight = 16.sp)
                    }
                }
            }
        }
    }
}

@Composable
fun PortfolioItemCard(
    project: PortfolioProjectEntity,
    onDelete: () -> Unit
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = ObsidianSurface),
        shape = RoundedCornerShape(14.dp),
        border = CardDefaults.outlinedCardBorder().copy(brush = Brush.linearGradient(listOf(ObsidianCardBorder, ObsidianSurfaceVariant))),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(text = project.trackCategory, fontSize = 10.sp, fontWeight = FontWeight.Bold, color = GoldSecondary)
                    Text(text = project.title, fontSize = 14.sp, fontWeight = FontWeight.Bold, color = TextWhite)
                }
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(6.dp))
                        .background(EmeraldContainer)
                        .padding(horizontal = 6.dp, vertical = 3.dp)
                ) {
                    Text(text = project.earningsOrValue, fontSize = 10.sp, color = EmeraldEarn, fontWeight = FontWeight.Bold)
                }
            }

            Spacer(modifier = Modifier.height(6.dp))
            Text(text = project.description, fontSize = 11.sp, color = TextGrayLight, lineHeight = 15.sp)

            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Client: ${project.clientNameOrSimulated}", fontSize = 10.sp, color = TextGrayMuted)
                IconButton(onClick = onDelete, modifier = Modifier.size(24.dp)) {
                    Icon(Icons.Default.DeleteOutline, contentDescription = "Delete", tint = TextGrayDark, modifier = Modifier.size(16.dp))
                }
            }
        }
    }
}

@Composable
fun AddProjectDialog(
    onDismiss: () -> Unit,
    onAdd: (String, String, String, String, String, String) -> Unit
) {
    var title by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("AI & Automation") }
    var description by remember { mutableStateOf("") }
    var client by remember { mutableStateOf("Freelance Client") }
    var value by remember { mutableStateOf("₹20,000") }
    var link by remember { mutableStateOf("https://github.com/my-project") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Add Live Portfolio Piece", color = GoldPrimary, fontWeight = FontWeight.Bold) },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Project Title") },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = TextWhite,
                        unfocusedTextColor = TextWhite,
                        focusedBorderColor = GoldPrimary
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("What you delivered (Outcome)") },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = TextWhite,
                        unfocusedTextColor = TextWhite,
                        focusedBorderColor = GoldPrimary
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = value,
                    onValueChange = { value = it },
                    label = { Text("Project Value / Earnings (e.g. ₹25,000)") },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = TextWhite,
                        unfocusedTextColor = TextWhite,
                        focusedBorderColor = GoldPrimary
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    if (title.isNotBlank()) {
                        onAdd(title, category, description, client, value, link)
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = GoldPrimary, contentColor = ObsidianBackground)
            ) {
                Text("Save to Portfolio", fontWeight = FontWeight.Bold)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) { Text("Cancel", color = TextGrayMuted) }
        },
        containerColor = ObsidianSurface
    )
}
