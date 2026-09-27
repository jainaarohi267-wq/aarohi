package com.example.ui.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.local.TrackProgressEntity
import com.example.data.local.UserProgressEntity
import com.example.data.repository.TrackDataRepository
import com.example.ui.components.GoldGradientButton
import com.example.ui.theme.*

@Composable
fun CertificatesScreen(
    userProgress: UserProgressEntity?,
    trackProgressList: List<TrackProgressEntity>,
    downloadedNotes: Set<String>,
    onSelectTrack: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    var selectedCertificateTrackId by remember { mutableStateOf<String?>("graphic-design-canva") }

    val completedTrackIds = trackProgressList
        .filter { it.isCertificateClaimed }
        .map { it.trackId }
        .toSet()

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
                    text = "ACCREDITATION & CREDENTIALS",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = GoldPrimary,
                    letterSpacing = 1.sp
                )
                Text(
                    text = "Verifiable DSA Certificates",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextWhite
                )
                Text(
                    text = "शेयरेबल डिजिटल सर्टिफिकेट्स • अपवर्क & लिंक्डइन रेडी",
                    fontSize = 11.sp,
                    color = GoldSecondary
                )
            }
        }

        // Live Verifiable Certificate Showcase Card (Gold & Obsidian Border)
        item {
            val trackToShow = TrackDataRepository.getTrackById(selectedCertificateTrackId ?: "graphic-design-canva")
                ?: TrackDataRepository.tracks.first()

            Card(
                colors = CardDefaults.cardColors(containerColor = Color(0xFF0C0E17)),
                shape = RoundedCornerShape(18.dp),
                border = CardDefaults.outlinedCardBorder().copy(
                    brush = Brush.linearGradient(
                        listOf(GoldPrimary, GoldDark, GoldSecondary, GoldPrimary)
                    ),
                    width = 2.dp
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("verifiable_certificate_card")
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Certificate Header
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(32.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(GoldPrimary),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("DSA", fontWeight = FontWeight.Black, fontSize = 11.sp, color = ObsidianBackground)
                        }

                        Text(
                            text = "CERTIFICATE OF EXCELLENCE",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = GoldPrimary,
                            letterSpacing = 2.sp
                        )

                        Icon(Icons.Default.Verified, contentDescription = null, tint = EmeraldEarn, modifier = Modifier.size(24.dp))
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "This is proudly awarded to",
                        fontSize = 11.sp,
                        color = TextGrayMuted
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = userProgress?.userName ?: "Aarav Sharma",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Black,
                        color = TextWhite,
                        textAlign = TextAlign.Center
                    )
                    Box(
                        modifier = Modifier
                            .width(160.dp)
                            .height(1.dp)
                            .background(GoldPrimary.copy(alpha = 0.6f))
                    )

                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "for successfully mastering the Zero to Hero curriculum in",
                        fontSize = 10.sp,
                        color = TextGrayLight,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = trackToShow.title,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = GoldSecondary,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(14.dp))
                    // 5-Stage Completion Badges
                    Row(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(ObsidianSurfaceVariant)
                            .padding(horizontal = 12.dp, vertical = 6.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text("Learn ✓", fontSize = 10.sp, color = EmeraldEarn, fontWeight = FontWeight.Bold)
                        Text("Practice ✓", fontSize = 10.sp, color = EmeraldEarn, fontWeight = FontWeight.Bold)
                        Text("Project ✓", fontSize = 10.sp, color = EmeraldEarn, fontWeight = FontWeight.Bold)
                        Text("Portfolio ✓", fontSize = 10.sp, color = EmeraldEarn, fontWeight = FontWeight.Bold)
                        Text("Earn ✓", fontSize = 10.sp, color = EmeraldEarn, fontWeight = FontWeight.Bold)
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Certificate ID & Simulated QR
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(text = "VERIFICATION ID", fontSize = 8.sp, color = TextGrayMuted, fontWeight = FontWeight.Bold)
                            Text(text = "DSA-2026-${trackToShow.id.take(4).uppercase()}-9481", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = GoldPrimary, fontFamily = FontFamily.Monospace)
                            Text(text = "Issue Date: September 2026", fontSize = 9.sp, color = TextGrayDark)
                        }

                        // Simulated QR Code Icon Box
                        Box(
                            modifier = Modifier
                                .size(44.dp)
                                .clip(RoundedCornerShape(6.dp))
                                .background(Color.White)
                                .padding(4.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(Icons.Default.QrCode, contentDescription = "Verification QR", tint = Color.Black, modifier = Modifier.size(36.dp))
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    // Share & Download Buttons
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Button(
                            onClick = {
                                Toast.makeText(context, "Certificate link copied for LinkedIn & Upwork!", Toast.LENGTH_SHORT).show()
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = GoldPrimary, contentColor = ObsidianBackground),
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier.weight(1f)
                        ) {
                            Icon(Icons.Default.Share, contentDescription = null, modifier = Modifier.size(14.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("Share on LinkedIn", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                        }

                        Button(
                            onClick = {
                                Toast.makeText(context, "Certificate PDF downloaded to device storage!", Toast.LENGTH_SHORT).show()
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = ObsidianSurfaceVariant, contentColor = TextWhite),
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier.weight(1f)
                        ) {
                            Icon(Icons.Default.FileDownload, contentDescription = null, modifier = Modifier.size(14.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("Download PDF", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }

        // Available & Locked Certificates List
        item {
            Text(
                text = "TRACK CERTIFICATION STATUS (8 TRACKS)",
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = GoldPrimary,
                letterSpacing = 1.sp
            )
        }

        items(TrackDataRepository.tracks) { track ->
            val isUnlocked = completedTrackIds.contains(track.id)
            val isSelected = selectedCertificateTrackId == track.id

            Card(
                colors = CardDefaults.cardColors(containerColor = ObsidianSurface),
                shape = RoundedCornerShape(14.dp),
                border = CardDefaults.outlinedCardBorder().copy(
                    brush = if (isSelected) Brush.horizontalGradient(listOf(GoldPrimary, GoldDark))
                    else Brush.horizontalGradient(listOf(ObsidianCardBorder, ObsidianSurfaceVariant))
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        if (isUnlocked) {
                            selectedCertificateTrackId = track.id
                        } else {
                            onSelectTrack(track.id)
                        }
                    }
            ) {
                Row(
                    modifier = Modifier.padding(14.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .clip(CircleShape)
                            .background(if (isUnlocked) EmeraldContainer else ObsidianSurfaceVariant),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = if (isUnlocked) Icons.Default.WorkspacePremium else Icons.Default.Lock,
                            contentDescription = null,
                            tint = if (isUnlocked) EmeraldEarn else TextGrayDark,
                            modifier = Modifier.size(20.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(12.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(text = track.title, fontSize = 13.sp, fontWeight = FontWeight.Bold, color = TextWhite)
                        Text(
                            text = if (isUnlocked) "Certificate Verified & Issued" else "Complete all 5 stages to unlock",
                            fontSize = 11.sp,
                            color = if (isUnlocked) EmeraldEarn else TextGrayMuted
                        )
                    }

                    if (isUnlocked) {
                        Text(
                            text = if (isSelected) "Viewing" else "View",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = GoldPrimary
                        )
                    } else {
                        Button(
                            onClick = { onSelectTrack(track.id) },
                            colors = ButtonDefaults.buttonColors(containerColor = ObsidianCardBorder, contentColor = TextWhite),
                            shape = RoundedCornerShape(6.dp),
                            contentPadding = PaddingValues(horizontal = 8.dp, vertical = 2.dp)
                        ) {
                            Text("Resume", fontSize = 10.sp)
                        }
                    }
                }
            }
        }

        // Offline Downloaded Vault
        if (downloadedNotes.isNotEmpty()) {
            item {
                Text(
                    text = "DOWNLOADED STUDY NOTES & PDF VAULT",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = CyanAccent,
                    letterSpacing = 1.sp
                )
            }

            items(downloadedNotes.toList()) { noteTitle ->
                Card(
                    colors = CardDefaults.cardColors(containerColor = ObsidianSurface),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier.padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(Icons.Default.CheckCircle, contentDescription = null, tint = EmeraldEarn, modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.width(10.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(text = noteTitle, fontSize = 12.sp, fontWeight = FontWeight.Bold, color = TextWhite)
                            Text(text = "Available offline in app storage", fontSize = 10.sp, color = TextGrayMuted)
                        }
                    }
                }
            }
        }

        item { Spacer(modifier = Modifier.height(24.dp)) }
    }
}
