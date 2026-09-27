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
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.*
import com.example.ui.viewmodel.ChatMessage
import kotlinx.coroutines.launch

@Composable
fun AiMentorScreen(
    messages: List<ChatMessage>,
    isThinking: Boolean,
    onSendMessage: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    var inputText by remember { mutableStateOf("") }
    val listState = rememberLazyListState()
    val scope = rememberCoroutineScope()

    val quickPrompts = listOf(
        "Client ko Upwork proposal kaise likhu?",
        "Canva me 10% CTR thumbnail formula kya hai?",
        "Make.com me WhatsApp automation kaise banaye?",
        "Zero to ₹50k/month ka roadmap batao",
        "Meta Ads ka high ROAS copy kaise likhe?"
    )

    LaunchedEffect(messages.size, isThinking) {
        if (messages.isNotEmpty()) {
            listState.animateScrollToItem(messages.size - 1)
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(ObsidianBackground)
    ) {
        // AI Mentor Header
        Surface(
            color = ObsidianSurface,
            modifier = Modifier
                .fillMaxWidth()
                .border(0.5.dp, ObsidianCardBorder, RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp))
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(42.dp)
                        .clip(CircleShape)
                        .background(GoldContainer)
                        .border(1.5.dp, GoldPrimary, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.SmartToy,
                        contentDescription = "AI Guru",
                        tint = GoldPrimary,
                        modifier = Modifier.size(24.dp)
                    )
                }
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "DSA AI Guru",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = TextWhite
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(4.dp))
                                .background(EmeraldContainer)
                                .padding(horizontal = 6.dp, vertical = 2.dp)
                        ) {
                            Text("Online • Gemini 3.5", fontSize = 9.sp, color = EmeraldEarn, fontWeight = FontWeight.Bold)
                        }
                    }
                    Text(
                        text = "Hindi & English Mentor for Code, Design & Client Deals",
                        fontSize = 11.sp,
                        color = GoldSecondary
                    )
                }
            }
        }

        // Quick Suggestion Chips
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            quickPrompts.take(3).forEach { prompt ->
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(14.dp))
                        .background(ObsidianSurfaceVariant)
                        .border(1.dp, ObsidianCardBorder, RoundedCornerShape(14.dp))
                        .clickable {
                            onSendMessage(prompt)
                        }
                        .padding(horizontal = 10.dp, vertical = 6.dp)
                ) {
                    Text(text = prompt, fontSize = 10.sp, color = TextGrayLight, maxLines = 1)
                }
            }
        }

        // Chat Message History
        LazyColumn(
            state = listState,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item { Spacer(modifier = Modifier.height(4.dp)) }

            items(messages) { message ->
                ChatBubble(message = message, onCopy = {
                    val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                    clipboard.setPrimaryClip(ClipData.newPlainText("AI Guru", message.text))
                })
            }

            if (isThinking) {
                item {
                    Row(
                        modifier = Modifier.padding(start = 8.dp, bottom = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(16.dp),
                            color = GoldPrimary,
                            strokeWidth = 2.dp
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "DSA Guru सोच रहे हैं...",
                            fontSize = 12.sp,
                            color = GoldSecondary,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }

            item { Spacer(modifier = Modifier.height(8.dp)) }
        }

        // Input Field Bar
        Surface(
            color = ObsidianSurface,
            tonalElevation = 6.dp,
            modifier = Modifier
                .fillMaxWidth()
                .border(0.5.dp, ObsidianCardBorder, RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextField(
                    value = inputText,
                    onValueChange = { inputText = it },
                    placeholder = {
                        Text(
                            text = "पूछें: 'क्लाइंट को क्या मैसेज भेजूं?' या कोई डाउट...",
                            fontSize = 12.sp,
                            color = TextGrayMuted
                        )
                    },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = ObsidianSurfaceVariant,
                        unfocusedContainerColor = ObsidianSurfaceVariant,
                        disabledContainerColor = ObsidianSurfaceVariant,
                        focusedTextColor = TextWhite,
                        unfocusedTextColor = TextWhite,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier
                        .weight(1f)
                        .testTag("ai_mentor_input_field")
                )

                Spacer(modifier = Modifier.width(8.dp))

                IconButton(
                    onClick = {
                        val textToSend = inputText.trim()
                        if (textToSend.isNotBlank()) {
                            onSendMessage(textToSend)
                            inputText = ""
                        }
                    },
                    modifier = Modifier
                        .size(44.dp)
                        .clip(CircleShape)
                        .background(GoldPrimary)
                        .testTag("ai_mentor_send_button")
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.Send,
                        contentDescription = "Send",
                        tint = ObsidianBackground,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun ChatBubble(
    message: ChatMessage,
    onCopy: () -> Unit
) {
    val isUser = message.isUser

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = if (isUser) Arrangement.End else Arrangement.Start
    ) {
        if (!isUser) {
            Box(
                modifier = Modifier
                    .size(28.dp)
                    .clip(CircleShape)
                    .background(GoldContainer),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.SmartToy, contentDescription = null, tint = GoldPrimary, modifier = Modifier.size(16.dp))
            }
            Spacer(modifier = Modifier.width(8.dp))
        }

        Box(
            modifier = Modifier
                .widthIn(max = 280.dp)
                .clip(
                    RoundedCornerShape(
                        topStart = 16.dp,
                        topEnd = 16.dp,
                        bottomStart = if (isUser) 16.dp else 4.dp,
                        bottomEnd = if (isUser) 4.dp else 16.dp
                    )
                )
                .background(
                    if (isUser) Brush.linearGradient(listOf(GoldPrimary, GoldDark))
                    else Brush.linearGradient(listOf(ObsidianSurface, ObsidianSurfaceVariant))
                )
                .border(
                    width = 1.dp,
                    color = if (isUser) GoldLight.copy(alpha = 0.5f) else ObsidianCardBorder,
                    shape = RoundedCornerShape(16.dp)
                )
                .padding(12.dp)
        ) {
            Column {
                Text(
                    text = message.text,
                    fontSize = 13.sp,
                    color = if (isUser) ObsidianBackground else TextWhite,
                    lineHeight = 18.sp
                )

                if (!isUser) {
                    Spacer(modifier = Modifier.height(6.dp))
                    Row(
                        modifier = Modifier
                            .align(Alignment.End)
                            .clickable { onCopy() },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(Icons.Default.ContentCopy, contentDescription = "Copy", tint = GoldSecondary, modifier = Modifier.size(12.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("Copy", fontSize = 10.sp, color = GoldSecondary, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}
