package com.example.network

import com.example.BuildConfig
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

@JsonClass(generateAdapter = true)
data class GeminiRequest(
    @Json(name = "contents") val contents: List<GeminiContent>,
    @Json(name = "systemInstruction") val systemInstruction: GeminiContent? = null,
    @Json(name = "generationConfig") val generationConfig: GeminiGenConfig? = null
)

@JsonClass(generateAdapter = true)
data class GeminiContent(
    @Json(name = "parts") val parts: List<GeminiPart>,
    @Json(name = "role") val role: String? = null
)

@JsonClass(generateAdapter = true)
data class GeminiPart(
    @Json(name = "text") val text: String
)

@JsonClass(generateAdapter = true)
data class GeminiGenConfig(
    @Json(name = "temperature") val temperature: Float = 0.7f,
    @Json(name = "topP") val topP: Float = 0.95f
)

@JsonClass(generateAdapter = true)
data class GeminiResponse(
    @Json(name = "candidates") val candidates: List<GeminiCandidate>?
)

@JsonClass(generateAdapter = true)
data class GeminiCandidate(
    @Json(name = "content") val content: GeminiContent?
)

interface GeminiApi {
    @POST("v1beta/models/gemini-3.5-flash:generateContent")
    suspend fun generateContent(
        @Query("key") apiKey: String,
        @Body request: GeminiRequest
    ): GeminiResponse
}

object GeminiClient {
    private const val BASE_URL = "https://generativelanguage.googleapis.com/"

    private val moshi = Moshi.Builder()
        .addLast(KotlinJsonAdapterFactory())
        .build()

    private val okHttpClient = OkHttpClient.Builder()
        .connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    val api: GeminiApi = retrofit.create(GeminiApi::class.java)

    suspend fun askAiMentor(
        userMessage: String,
        history: List<Pair<String, Boolean>> = emptyList() // Pair of (Text, isUser)
    ): String = withContext(Dispatchers.IO) {
        val apiKey = try {
            BuildConfig.GEMINI_API_KEY
        } catch (e: Exception) {
            ""
        }

        if (apiKey.isBlank() || apiKey == "MY_GEMINI_API_KEY") {
            // Intelligent domain-specific Hinglish fallback mentor responses
            return@withContext generateSmartMentorFallback(userMessage)
        }

        val systemPrompt = """
            आप 'DSA Learn & Earn' के ऑफिशियल AI मेंटॉर (DSA Guru) हैं। 
            आपका काम स्टूडेंट्स और फ्रीलांसर्स को 8 स्किल्स (AI & Automation, Graphic Design/Canva, Digital Marketing, Data Analytics, Freelancing, Personal Branding, Digital Products, No-Code Dev) में गाइड करना है।
            हमेशा उत्साही, फ्रेंडली और प्रैक्टिकल 'Hinglish' (हिंदी + इंग्लिश) में बात करें।
            रोडमैप हमेशा: Learn → Practice → Project → Portfolio → Earn के फ्रेमवर्क में समझाएं।
            अगर यूजर क्लाइंट प्रपोजल, रेट या पोर्टफोलियो के बारे में पूछे, तो रेडी-टू-यूज़ टेम्प्लेट दें।
        """.trimIndent()

        val contentsList = mutableListOf<GeminiContent>()
        // Append last 4 messages for context
        history.takeLast(4).forEach { (msg, isUser) ->
            contentsList.add(
                GeminiContent(
                    parts = listOf(GeminiPart(text = msg)),
                    role = if (isUser) "user" else "model"
                )
            )
        }
        contentsList.add(
            GeminiContent(
                parts = listOf(GeminiPart(text = userMessage)),
                role = "user"
            )
        )

        val request = GeminiRequest(
            contents = contentsList,
            systemInstruction = GeminiContent(parts = listOf(GeminiPart(text = systemPrompt))),
            generationConfig = GeminiGenConfig(temperature = 0.7f)
        )

        try {
            val response = api.generateContent(apiKey, request)
            val reply = response.candidates?.firstOrNull()?.content?.parts?.firstOrNull()?.text
            if (!reply.isNullOrBlank()) {
                reply
            } else {
                generateSmartMentorFallback(userMessage)
            }
        } catch (e: Exception) {
            generateSmartMentorFallback(userMessage)
        }
    }

    private fun generateSmartMentorFallback(query: String): String {
        val q = query.lowercase()
        return when {
            q.contains("proposal") || q.contains("upwork") || q.contains("bid") -> {
                "🎯 **Upwork & Freelance Winning Proposal Formula:**\n\n" +
                        "1. **Line 1 (Direct Hook):** 'Hey [Client], I saw your requirement for [Task]. Instead of giving generic promises, here is how I will solve it...'\n" +
                        "2. **Line 2 (Proof):** 'I recently completed a similar project for a client that saved them 15+ hours weekly.'\n" +
                        "3. **Line 3 (Video Audit):** 'I recorded a 60-second Loom demo showing the prototype: [Link].'\n" +
                        "4. **Line 4 (Call to Action):** 'Are you available for a quick 5-min chat today?'\n\n" +
                        "💡 *DSA Tip: कभी भी 'I am a hardworking person' से शुरुआत न करें! सीधे प्रॉब्लम पर हिट करें।*"
            }
            q.contains("pricing") || q.contains("rate") || q.contains("kitna charge") || q.contains("paise") -> {
                "💰 **DSA Pricing Guide (Zero to Hero):**\n\n" +
                        "• **Beginner (First 3 Clients):** ₹800 - ₹1,500 प्रति थंबनेल / ₹15,000 प्रति वेबसाइट या ऑटोमेशन। (मकसद: टेस्टीमोनियल और 5-स्टार रेटिंग पाना)।\n" +
                        "• **Intermediate (10+ Projects):** ₹25,000 - ₹45,000 प्रति मंथली रिटेनर।\n" +
                        "• **Pro Level (International Clients):** \$25 - \$50/hr (लगभग ₹2,100 - ₹4,200 प्रति घंटा)।\n\n" +
                        "⚡ *DSA Rule:* हमेशा क्लाइंट को 'Hourly' की जगह 'Value-based Fixed Price' पिच करें!"
            }
            q.contains("client") || q.contains("cold email") || q.contains("kaise dhunde") || q.contains("lead") -> {
                "🚀 **First High-Paying Client Acquisition Strategy:**\n\n" +
                        "1. **Audit Strategy:** अपने टारगेट क्लाइंट (यूट्यूबर या D2C ब्रांड) का कंटेंट देखें और उनकी 1 बड़ी गलती निकालें।\n" +
                        "2. **Free Sample:** बिना पैसे मांगे एक रीडिज़ाइन या 1-मिनट का ऑटोमेशन वीडियो बनाकर भेजें।\n" +
                        "3. **Platform:** लिंक्डइन इनबॉक्स और इंस्टाग्राम डीएम सबसे बेस्ट कन्वर्ट करते हैं।\n" +
                        "4. **Consistency:** रोज़ाना 10 कस्टमाइज्ड आउटरीच = 1 महीने में 3 से 5 पेइंग क्लाइंट्स!"
            }
            q.contains("canva") || q.contains("thumbnail") || q.contains("design") -> {
                "🎨 **Canva Pro High-CTR Thumbnail Secret:**\n\n" +
                        "• **Rule of 3 Elements:** 1 फेस एक्सप्रेशन + 3 से 4 बड़े शब्द + 1 क्यूरियोसिटी एलिमेंट (एरो/हाइलाइट)।\n" +
                        "• **Colors:** बैकग्राउंड हमेशा डार्क (Obsidian #0D0E15) रखें और टेक्स्ट को ब्राइट गोल्ड या नियॉन येलो दें।\n" +
                        "• **Mobile Check:** थंबनेल को 15% जूम-आउट करके देखें—क्या फोन की स्क्रीन पर टेक्स्ट साफ पढ़ा जा रहा है?"
            }
            q.contains("ai") || q.contains("automation") || q.contains("make") -> {
                "🤖 **AI & Automation Roadmap:**\n\n" +
                        "1. **Step 1:** ChatGPT में 'Role, Context, Constraints' के साथ प्रॉम्प्ट इंजीनियरिंग सीखें।\n" +
                        "2. **Step 2:** Make.com पर फ्री अकाउंट बनाकर Google Sheet → Gmail ऑटोमेशन बनाएं।\n" +
                        "3. **Step 3:** WhatsApp Business API और OpenAI को कनेक्ट करके ऑटोमेटेड लीड बॉट तैयार करें।\n" +
                        "4. **Step 4:** लोकल रियल एस्टेट एजेंट्स और ई-कॉमर्स ब्रांड्स को ₹25k का वन-टाइम पैकेज पिच करें।"
            }
            else -> {
                "शानदार सवाल! DSA फ्रेमवर्क के तहत याद रखें: **Learn → Practice → Project → Portfolio → Earn**।\n\n" +
                        "आप जिस भी स्किल (AI, Canva, Ads, SQL, No-Code) पर काम कर रहे हैं, उस पर तुरंत 1 रियल-वर्ल्ड प्रोजेक्ट बनाएं और हमारे **Portfolio Builder** में ऐड करें।\n\n" +
                        "बताइए क्या आप किसी स्पेसिफिक क्लाइंट प्रोजेक्ट या प्रपोजल ड्राफ्ट करने में मेरी मदद चाहते हैं?"
            }
        }
    }
}
