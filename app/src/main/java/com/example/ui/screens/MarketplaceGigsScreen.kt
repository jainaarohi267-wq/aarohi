package com.example.ui.screens

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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.DigitalProductItem
import com.example.data.model.GigOpportunity
import com.example.data.repository.TrackDataRepository
import com.example.ui.components.GoldGradientButton
import com.example.ui.theme.*

@Composable
fun MarketplaceGigsScreen(
    onApplyGig: (GigOpportunity) -> Unit,
    onPurchaseProduct: (DigitalProductItem) -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedSection by remember { mutableStateOf(0) } // 0: Live Gigs & Jobs, 1: Digital Products Marketplace
    val gigs = TrackDataRepository.gigsAndOpportunities
    val products = TrackDataRepository.digitalProducts

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(ObsidianBackground)
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item { Spacer(modifier = Modifier.height(4.dp)) }

        // Screen Header
        item {
            Column {
                Text(
                    text = "OPPORTUNITIES & ASSETS",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = GoldPrimary,
                    letterSpacing = 1.sp
                )
                Text(
                    text = if (selectedSection == 0) "Freelance Gigs & Internships" else "Digital Product Marketplace",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextWhite
                )
                Text(
                    text = "वेरिफाइड क्लाइंट प्रोजेक्ट्स और रेडी-टू-सेल डिजिटल एसेट्स",
                    fontSize = 11.sp,
                    color = GoldSecondary
                )
            }
        }

        // Toggle Switch
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(ObsidianSurface)
                    .border(1.dp, ObsidianCardBorder, RoundedCornerShape(12.dp))
                    .padding(4.dp)
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(8.dp))
                        .background(if (selectedSection == 0) GoldPrimary else Color.Transparent)
                        .clickable { selectedSection = 0 }
                        .padding(vertical = 8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "💼 Live Gigs (${gigs.size})",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (selectedSection == 0) ObsidianBackground else TextWhite
                    )
                }
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(8.dp))
                        .background(if (selectedSection == 1) GoldPrimary else Color.Transparent)
                        .clickable { selectedSection = 1 }
                        .padding(vertical = 8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "🛍️ Digital Products (${products.size})",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (selectedSection == 1) ObsidianBackground else TextWhite
                    )
                }
            }
        }

        if (selectedSection == 0) {
            // Live Gigs List
            items(gigs) { gig ->
                GigOpportunityCard(gig = gig, onApply = { onApplyGig(gig) })
            }
        } else {
            // Digital Products List
            items(products) { product ->
                DigitalProductCard(product = product, onBuy = { onPurchaseProduct(product) })
            }
        }

        item { Spacer(modifier = Modifier.height(24.dp)) }
    }
}

@Composable
fun GigOpportunityCard(
    gig: GigOpportunity,
    onApply: () -> Unit
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = ObsidianSurface),
        shape = RoundedCornerShape(16.dp),
        border = CardDefaults.outlinedCardBorder().copy(brush = Brush.linearGradient(listOf(ObsidianCardBorder, GoldPrimary.copy(alpha = 0.3f)))),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(text = gig.clientCompany, fontSize = 11.sp, fontWeight = FontWeight.SemiBold, color = GoldSecondary)
                    Text(text = gig.title, fontSize = 15.sp, fontWeight = FontWeight.Bold, color = TextWhite)
                }
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(EmeraldContainer)
                        .border(1.dp, EmeraldEarn, RoundedCornerShape(8.dp))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(text = gig.stipendOrBudget, fontSize = 11.sp, fontWeight = FontWeight.Bold, color = EmeraldEarn)
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
            Text(text = gig.description, fontSize = 12.sp, color = TextGrayLight, lineHeight = 16.sp)

            Spacer(modifier = Modifier.height(12.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                gig.tags.forEach { tag ->
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(6.dp))
                            .background(ObsidianSurfaceVariant)
                            .padding(horizontal = 6.dp, vertical = 3.dp)
                    ) {
                        Text(text = tag, fontSize = 10.sp, color = TextGrayLight)
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "⏳ ${gig.deadline}", fontSize = 11.sp, color = AmberAccent, fontWeight = FontWeight.Medium)
                Button(
                    onClick = onApply,
                    colors = ButtonDefaults.buttonColors(containerColor = GoldPrimary, contentColor = ObsidianBackground),
                    shape = RoundedCornerShape(8.dp),
                    contentPadding = PaddingValues(horizontal = 14.dp, vertical = 6.dp)
                ) {
                    Text("Apply with Proposal", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Composable
fun DigitalProductCard(
    product: DigitalProductItem,
    onBuy: () -> Unit
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = ObsidianSurface),
        shape = RoundedCornerShape(16.dp),
        border = CardDefaults.outlinedCardBorder().copy(brush = Brush.linearGradient(listOf(ObsidianCardBorder, EmeraldEarn.copy(alpha = 0.3f)))),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(text = product.category, fontSize = 10.sp, fontWeight = FontWeight.Bold, color = GoldSecondary)
                    Text(text = product.name, fontSize = 15.sp, fontWeight = FontWeight.Bold, color = TextWhite)
                }
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(6.dp))
                        .background(GoldContainer)
                        .padding(horizontal = 6.dp, vertical = 2.dp)
                ) {
                    Text(text = product.previewBadge, fontSize = 9.sp, fontWeight = FontWeight.Bold, color = GoldPrimary)
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
            Text(text = product.description, fontSize = 12.sp, color = TextGrayLight, lineHeight = 16.sp)

            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Star, contentDescription = null, tint = GoldPrimary, modifier = Modifier.size(14.dp))
                    Spacer(modifier = Modifier.width(3.dp))
                    Text(text = "${product.rating} (${product.salesCount} sold)", fontSize = 11.sp, color = TextGrayLight)
                }
                Text(text = "Creator: ${product.authorName}", fontSize = 10.sp, color = TextGrayMuted)
            }

            Spacer(modifier = Modifier.height(14.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "₹${product.priceInInr}",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Black,
                    color = EmeraldEarn
                )
                Button(
                    onClick = onBuy,
                    colors = ButtonDefaults.buttonColors(containerColor = EmeraldEarn, contentColor = ObsidianBackground),
                    shape = RoundedCornerShape(8.dp),
                    contentPadding = PaddingValues(horizontal = 14.dp, vertical = 6.dp)
                ) {
                    Icon(Icons.Default.Download, contentDescription = null, modifier = Modifier.size(14.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Instant Download", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}
