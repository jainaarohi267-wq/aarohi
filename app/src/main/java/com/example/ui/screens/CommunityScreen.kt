package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
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
import com.example.data.local.CommunityPostEntity
import com.example.ui.theme.*

@Composable
fun CommunityScreen(
    posts: List<CommunityPostEntity>,
    onLikePost: (Int) -> Unit,
    onNewPost: (String, String, String) -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedTag by remember { mutableStateOf("All") }
    var showNewPostDialog by remember { mutableStateOf(false) }

    val tags = listOf("All", "First Client Win 🎉", "Doubt Solving ❓", "Project Showcase 🚀", "Gigs & Collabs 🤝")

    val filteredPosts = if (selectedTag == "All") posts else posts.filter { it.categoryTag.contains(selectedTag.take(6)) }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(ObsidianBackground)
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        item { Spacer(modifier = Modifier.height(4.dp)) }

        // Header Title
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "DSA CHARCHA (COMMUNITY)",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = GoldPrimary,
                        letterSpacing = 1.sp
                    )
                    Text(
                        text = "Learn & Grow Together",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextWhite
                    )
                    Text(
                        text = "डाउट्स पूछें • क्लाइंट विन्स शेयर करें • फीडबैक पाएं",
                        fontSize = 11.sp,
                        color = GoldSecondary
                    )
                }

                Button(
                    onClick = { showNewPostDialog = true },
                    colors = ButtonDefaults.buttonColors(containerColor = GoldPrimary, contentColor = ObsidianBackground),
                    shape = RoundedCornerShape(10.dp),
                    contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp),
                    modifier = Modifier.testTag("community_new_post_button")
                ) {
                    Icon(Icons.Default.Add, contentDescription = null, modifier = Modifier.size(14.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Post", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                }
            }
        }

        // Tag Filters
        item {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(tags) { tag ->
                    val isSelected = selectedTag == tag
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .background(if (isSelected) GoldPrimary else ObsidianSurface)
                            .border(1.dp, if (isSelected) GoldPrimary else ObsidianCardBorder, RoundedCornerShape(20.dp))
                            .clickable { selectedTag = tag }
                            .padding(horizontal = 12.dp, vertical = 6.dp)
                    ) {
                        Text(
                            text = tag,
                            fontSize = 11.sp,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                            color = if (isSelected) ObsidianBackground else TextGrayLight
                        )
                    }
                }
            }
        }

        // Posts
        items(filteredPosts) { post ->
            CommunityPostCard(post = post, onLike = { onLikePost(post.id) })
        }

        item { Spacer(modifier = Modifier.height(24.dp)) }
    }

    if (showNewPostDialog) {
        CreatePostDialog(
            onDismiss = { showNewPostDialog = false },
            onSubmit = { tag, title, content ->
                onNewPost(tag, title, content)
                showNewPostDialog = false
            }
        )
    }
}

@Composable
fun CommunityPostCard(
    post: CommunityPostEntity,
    onLike: () -> Unit
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = ObsidianSurface),
        shape = RoundedCornerShape(14.dp),
        border = CardDefaults.outlinedCardBorder().copy(brush = Brush.linearGradient(listOf(ObsidianCardBorder, ObsidianSurfaceVariant))),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            // Author info row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(32.dp)
                            .clip(CircleShape)
                            .background(GoldContainer)
                            .border(1.dp, GoldPrimary, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = post.authorName.take(1),
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp,
                            color = GoldPrimary
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Column {
                        Text(text = post.authorName, fontSize = 12.sp, fontWeight = FontWeight.Bold, color = TextWhite)
                        Text(text = post.authorBadge, fontSize = 10.sp, color = EmeraldEarn, fontWeight = FontWeight.Medium)
                    }
                }

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(6.dp))
                        .background(ObsidianSurfaceVariant)
                        .padding(horizontal = 6.dp, vertical = 2.dp)
                ) {
                    Text(text = post.categoryTag, fontSize = 9.sp, color = GoldSecondary, fontWeight = FontWeight.SemiBold)
                }
            }

            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = post.questionOrTitle,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = TextWhite
            )

            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = post.content,
                fontSize = 12.sp,
                color = TextGrayLight,
                lineHeight = 17.sp
            )

            Spacer(modifier = Modifier.height(12.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    // Like button
                    Row(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(if (post.isLikedByUser) Color(0xFF331618) else ObsidianSurfaceVariant)
                            .clickable { onLike() }
                            .padding(horizontal = 8.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = if (post.isLikedByUser) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                            contentDescription = "Like",
                            tint = if (post.isLikedByUser) ErrorRed else TextGrayMuted,
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "${post.likesCount}",
                            fontSize = 11.sp,
                            color = if (post.isLikedByUser) ErrorRed else TextGrayLight,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(modifier = Modifier.width(10.dp))
                    Row(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(ObsidianSurfaceVariant)
                            .padding(horizontal = 8.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(Icons.Default.ChatBubbleOutline, contentDescription = "Replies", tint = TextGrayMuted, modifier = Modifier.size(14.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(text = "${post.repliesCount} replies", fontSize = 11.sp, color = TextGrayLight)
                    }
                }

                Text(text = "Active now", fontSize = 10.sp, color = TextGrayDark)
            }
        }
    }
}

@Composable
fun CreatePostDialog(
    onDismiss: () -> Unit,
    onSubmit: (String, String, String) -> Unit
) {
    var title by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }
    var tag by remember { mutableStateOf("First Client Win 🎉") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Start Discussion / Share Win", color = GoldPrimary, fontWeight = FontWeight.Bold) },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Headline / Question") },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = TextWhite,
                        unfocusedTextColor = TextWhite,
                        focusedBorderColor = GoldPrimary
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = content,
                    onValueChange = { content = it },
                    label = { Text("Details (Hindi/English)") },
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
                    if (title.isNotBlank() && content.isNotBlank()) {
                        onSubmit(tag, title, content)
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = GoldPrimary, contentColor = ObsidianBackground)
            ) {
                Text("Publish Post", fontWeight = FontWeight.Bold)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) { Text("Cancel", color = TextGrayMuted) }
        },
        containerColor = ObsidianSurface
    )
}
