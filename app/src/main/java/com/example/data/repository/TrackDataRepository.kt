package com.example.data.repository

import com.example.data.model.*

object TrackDataRepository {

    val tracks: List<LearningTrack> = listOf(
        LearningTrack(
            id = "ai-automation",
            title = "AI & Automation",
            subtitleHindi = "आर्टिफिशियल इंटेलिजेंस और वर्कफ़्लो ऑटोमेशन सीखें",
            description = "Master Prompt Engineering, ChatGPT, Make.com, Zapier, and Custom AI Agents to automate business tasks and earn top dollar.",
            earningPotential = "₹50,000 - ₹2,00,000 / month",
            averageStartingRate = "₹1,500 / hour ($20/hr)",
            durationHours = 24,
            lessonsCount = 18,
            tags = listOf("High Demand", "Future Ready", "AI Agents"),
            toolsCovered = listOf("ChatGPT 4o", "Make.com", "Zapier", "Claude", "Python AI APIs"),
            stages = TrackStages(
                learn = LearnStage(
                    title = "Learn: AI & Workflow Foundations",
                    videoTitleHindi = "AI ऑटोमेशन क्या है और इससे क्लाइंट्स के घंटे कैसे बचाएं?",
                    videoDuration = "42:15 mins HD",
                    instructor = "Vikram Aditya (Senior AI Solutions Architect)",
                    videoChapters = listOf(
                        VideoChapter("00:00", "Introduction & Indian Market Opportunity"),
                        VideoChapter("05:30", "Prompt Engineering: Zero-Shot to Few-Shot in Hindi"),
                        VideoChapter("16:45", "Make.com vs Zapier Workflow Architecture"),
                        VideoChapter("28:10", "Connecting Google Sheets to WhatsApp Bot via Webhooks"),
                        VideoChapter("37:00", "Packaging Automation Services for Small Businesses")
                    ),
                    transcriptHindiExcerpt = "नमस्ते दोस्तों! आज के लेक्चर में हम सीखेंगे कि कैसे किसी भी बिज़नेस के 80% रिपीटिटिव टास्क्स (जैसे लीड्स कैप्चर करना, इनवॉइस भेजना और ऑटोमेटेड ईमेल भेजना) को आप बिना कोडिंग के ऑटोमेट कर सकते हैं।",
                    keyConcepts = listOf(
                        "Context Window & System Instructions",
                        "Webhook Triggers & API Payloads",
                        "Data Parsing with Regex and JSON",
                        "Error Handling and Fallback Retries"
                    )
                ),
                practice = PracticeStage(
                    title = "Practice: Workflow Blueprint",
                    infographicTitle = "E-Commerce Auto-Lead Workflow Infographic",
                    infographicDescription = "Visual data-pipeline showing user inquiry turning into instant WhatsApp catalog and CRM row entry.",
                    infographicDiagramSteps = listOf(
                        DiagramStep(1, "Lead Inquiry", "Customer fills Instagram/Meta Ad lead form", 0xFFFFD700),
                        DiagramStep(2, "Webhook Trigger", "Make.com receives instant JSON payload", 0xFFFFB300),
                        DiagramStep(3, "AI Personalization", "GPT model crafts customized Hindi/English response", 0xFF00E676),
                        DiagramStep(4, "CRM & WhatsApp", "Lead stored in Notion/Sheet + WhatsApp ping sent", 0xFF38BDF8)
                    ),
                    downloadableNotesTitle = "DSA_AI_Automation_Master_Cheatsheet.pdf",
                    downloadableNotesSize = "4.2 MB (42 Pages)",
                    downloadableNotesKeyPoints = listOf(
                        "Top 25 high-converting prompt formulas for business copywriting",
                        "Step-by-step webhook integration checklist with screenshots",
                        "Cost calculator for OpenAI API tokens vs client retainer",
                        "Client handover documentation template"
                    ),
                    quizQuestions = listOf(
                        QuizQuestion(
                            id = 1,
                            questionHindi = "Webhook का मुख्य काम क्या होता है?",
                            questionEnglish = "What is the primary role of a Webhook in automation?",
                            options = listOf(
                                "वेबसाइट को डिलीट करना",
                                "एक ऐप से दूसरे ऐप में रियल-टाइम डेटा तुरंत भेजना",
                                "कंप्यूटर की स्पीड बढ़ाना",
                                "केवल पासवर्ड सेव करना"
                            ),
                            correctIndex = 1,
                            explanationHindi = "Webhook रियल-टाइम में डेटा ट्रिगर करता है जैसे ही कोई इवेंट (उदा. नया फॉर्म सबमिशन) होता है।"
                        ),
                        QuizQuestion(
                            id = 2,
                            questionHindi = "AI को सटीक उत्तर देने के लिए 'System Prompt' में क्या देना सबसे महत्वपूर्ण है?",
                            questionEnglish = "What is most crucial to specify in an AI's System Prompt?",
                            options = listOf(
                                "फाइल का साइज",
                                "AI का रोल, कॉन्टेक्स्ट और आउटपुट फॉर्मेट के नियम",
                                "इंटरनेट प्रोवाइडर का नाम",
                                "कंप्यूटर की बैटरी पर्सेंटेज"
                            ),
                            correctIndex = 1,
                            explanationHindi = "System Prompt में AI का Role, Persona, Constraints और Output Structure डिफाइन किया जाता है।"
                        )
                    )
                ),
                project = ProjectStage(
                    projectTitle = "Real Estate Automated Lead Qualification System",
                    clientContext = "Client: UrbanNest Realty (Gurugram). Receives 150 inquiries daily on WhatsApp & Ads.",
                    problemStatementHindi = "क्लाइंट को हर लीड को मैन्युअली कॉल करने में 4 घंटे लगते हैं और 60% लीड्स अनक्वालिफाइड होती हैं। आपको AI ऑटोमेशन बनाना है जो 2 मिनट में बजट और लोकेशन पूछकर स्कोर दे।",
                    deliverableChecklist = listOf(
                        "Make.com scenario connecting Webhook to Google Sheets",
                        "AI prompt that qualifies budget > ₹75 Lakhs",
                        "Instant confirmation WhatsApp message template",
                        "5-minute Loom video walking through the architecture"
                    ),
                    expectedOutcome = "Saves 3.5 human hours per day for the real estate agency.",
                    estimatedProjectValue = "₹25,000 - ₹40,000 one-time setup fee"
                ),
                portfolio = PortfolioStage(
                    title = "Portfolio: Showcase Ready Case Study",
                    portfolioPieceTitle = "UrbanNest 24/7 AI Lead Qualifier Case Study",
                    mockupDescription = "High-contrast dark mode dashboard graphic displaying flow diagram, time saved counter, and client testimonial.",
                    clientPitchHindi = "'मैंने UrbanNest के लिए एक AI ऑटोमेशन सिस्टम तैयार किया जिसने लीड रिस्पांस टाइम को 4 घंटे से घटाकर सिर्फ 30 सेकंड कर दिया। आइए आपके बिज़नेस के लिए भी ऐसा ही सिस्टम प्लान करें।'",
                    recommendedTags = listOf("Make.com", "AI Automation", "Prompt Engineering", "WhatsApp API")
                ),
                earn = EarnStage(
                    title = "Earn: Monetization Roadmap",
                    gigTitleExample = "I will build custom AI automation & Make.com workflows for your business",
                    beginnerPriceRange = "₹10,000 - ₹20,000 per workflow",
                    proPriceRange = "₹45,000 - ₹1,20,000 monthly retainer",
                    topPlatforms = listOf("Upwork", "Fiverr", "LinkedIn Cold DMs", "Direct Local Business Outreach"),
                    proposalScriptHindi = "नमस्ते [Client Name], मैंने देखा कि आप लीड्स मैन्युअली प्रोसेस करते हैं। मैंने सिमिलर बिज़नेसेज के 20+ घंटे बचाए हैं। क्या हम 10 मिनट की क्विक कॉल पर एक फ्री डेमो देख सकते हैं?",
                    coldEmailPitch = "Subject: Automate [Company] lead response from 4 hours to 30 secs\n\nHi [Name], I noticed you run active Meta Ads. Many real estate agencies lose 40% leads due to delayed follow-ups. I built an AI system that qualifies leads on WhatsApp in under 1 minute. Would love to send a 2-min Loom demo."
                )
            )
        ),
        LearningTrack(
            id = "graphic-design-canva",
            title = "Graphic Design & Canva",
            subtitleHindi = "प्रोफेशनल थंबनेल्स, ब्रांडिंग और सोशल मीडिया किट डिज़ाइन करें",
            description = "From YouTube viral thumbnails to full brand identity kits, color theory, typography, and monetization through freelance design.",
            earningPotential = "₹35,000 - ₹1,20,000 / month",
            averageStartingRate = "₹800 / thumbnail | ₹15,000 / brand kit",
            durationHours = 20,
            lessonsCount = 15,
            tags = listOf("High Volume", "Creativity", "Social Media"),
            toolsCovered = listOf("Canva Pro", "Photoshop Basics", "Figma", "Remove.bg", "Midjourney"),
            stages = TrackStages(
                learn = LearnStage(
                    title = "Learn: Visual Hierarchy & Design Rules",
                    videoTitleHindi = "हाई CTR थंबनेल और सोशल मीडिया विजुअल्स का विज्ञान",
                    videoDuration = "38:40 mins HD",
                    instructor = "Neha Soni (Senior Creative Lead & YouTuber)",
                    videoChapters = listOf(
                        VideoChapter("00:00", "Design Fundamentals in Hindi (Contrast, Hierarchy, Balance)"),
                        VideoChapter("08:15", "Canva Pro Advanced Shortcuts & Grid Systems"),
                        VideoChapter("18:30", "Color Psychology: Why Gold, Red & Yellow Convert"),
                        VideoChapter("27:45", "Creating 3D Depth, Shadows & Glow Effects in Canva"),
                        VideoChapter("34:20", "Exporting in Ultra HD and Packaging Brand Kits")
                    ),
                    transcriptHindiExcerpt = "एक अच्छा डिज़ाइन सिर्फ सुंदर नहीं होता, वो इंसान को रुकने और क्लिक करने पर मजबूर करता है। इस लेसन में हम 'Rule of Thirds' और 'Visual Tension' को हिंदी में प्रैक्टिकल बनाकर समझेंगे।",
                    keyConcepts = listOf("Contrast & Whitespace", "Color Emotion Mapping", "F-Pattern Scanning", "Typography Pairing")
                ),
                practice = PracticeStage(
                    title = "Practice: Anatomy of 15% CTR Thumbnail",
                    infographicTitle = "Viral YouTube Thumbnail Anatomy",
                    infographicDescription = "Breakdown of emotion focal point, 3-word bold title hook, and background dark vignette.",
                    infographicDiagramSteps = listOf(
                        DiagramStep(1, "Emotion Subject", "Cutout with rim light placed on the right side", 0xFFFFD700),
                        DiagramStep(2, "3-Word Hook", "Ultra bold contrasting font with gold drop shadow", 0xFFFFFFFF),
                        DiagramStep(3, "Curiosity Element", "Blurred preview arrow or mystery metric", 0xFFFF5252),
                        DiagramStep(4, "Depth Background", "Dark obsidian gradient to make foreground pop", 0xFF0D0E15)
                    ),
                    downloadableNotesTitle = "Canva_Design_Vault_&_Fonts.pdf",
                    downloadableNotesSize = "6.8 MB (55 Pages)",
                    downloadableNotesKeyPoints = listOf(
                        "Top 40 Free Google Fonts pairings for Indian brands",
                        "Hex code palettes for luxury, fitness, tech, and finance niches",
                        "Canva secret keyword search cheat sheet for 3D elements",
                        "Thumbnail template links with full commercial rights"
                    ),
                    quizQuestions = listOf(
                        QuizQuestion(
                            id = 1,
                            questionHindi = "YouTube थंबनेल में टेक्स्ट का आकार कितना होना चाहिए?",
                            questionEnglish = "What size should text be on a mobile-first thumbnail?",
                            options = listOf(
                                "बहुत छोटा और 15-20 शब्द",
                                "बड़ा और बोल्ड, मोबाइल स्क्रीन पर 3 सेकंड में पढ़ने योग्य (3-5 शब्द)",
                                "बिना किसी कंट्रास्ट के",
                                "सिर्फ पैराग्राफ फॉन्ट"
                            ),
                            correctIndex = 1,
                            explanationHindi = "80% से ज्यादा दर्शक मोबाइल पर होते हैं, इसलिए फॉन्ट बड़ा, बोल्ड और अधिकतम 3 से 5 शब्दों का होना चाहिए।"
                        )
                    )
                ),
                project = ProjectStage(
                    projectTitle = "Finance Creator Rebranding & 10 Thumbnail Pack",
                    clientContext = "Client: 'Paisa Wise' YouTube Channel (80,000 subscribers). Stagnant 4% CTR.",
                    problemStatementHindi = "क्रिएटर के थंबनेल बोरिंग हैं और व्यूज नहीं आ रहे। आपको 3 दिन में उनका लोगो, कलर पैलेट और 10 हाई-कन्वर्टिंग थंबनेल रीडिज़ाइन करने हैं।",
                    deliverableChecklist = listOf(
                        "Color Palette & Font Style Guide",
                        "10 Editable Canva Pro Thumbnail Templates",
                        "YouTube Channel Banner & Avatar",
                        "Before vs After CTR comparison breakdown"
                    ),
                    expectedOutcome = "Target CTR increase from 4.2% to 9.5%+",
                    estimatedProjectValue = "₹12,000 - ₹18,000"
                ),
                portfolio = PortfolioStage(
                    title = "Portfolio: YouTube Visuals Specialist",
                    portfolioPieceTitle = "Paisa Wise Channel Visual Transformation",
                    mockupDescription = "Behance-ready presentation slide showing 10 high-impact thumbnails on mobile mockups.",
                    clientPitchHindi = "'मैंने फाइनेंस और टेक यूट्यूबर्स के लिए 100+ थंबनेल बनाए हैं जिन्होंने एवरेज 8% CTR पार किया है। यहाँ मेरा लाइव पोर्टफोलियो है।' ",
                    recommendedTags = listOf("Canva Pro", "YouTube Thumbnails", "Social Media Branding", "Figma")
                ),
                earn = EarnStage(
                    title = "Earn: High-Paying Design Clients",
                    gigTitleExample = "I will design high CTR viral YouTube thumbnails in 24 hours",
                    beginnerPriceRange = "₹600 - ₹1,200 per thumbnail",
                    proPriceRange = "₹25,000 - ₹60,000 monthly retainer (20 thumbnails + banners)",
                    topPlatforms = listOf("Twitter/X DMs to Creators", "YouTube Community Outreach", "Fiverr", "Upwork"),
                    proposalScriptHindi = "नमस्ते भाई, मैंने आपके लेटेस्ट 3 वीडियो देखे। कंटेंट बहुत अच्छा है लेकिन थंबनेल में कंट्रास्ट कम है। मैंने एक सैंपल थंबनेल फ्री में बनाया है, क्या मैं भेज सकता हूँ?",
                    coldEmailPitch = "Hey [Creator], loved your video on Mutual Funds. Noticed the CTR might be hurt by the background contrast. I designed a concept thumbnail for free (attached). If you like it, we can work on a 10-pack!"
                )
            )
        ),
        LearningTrack(
            id = "digital-marketing",
            title = "Digital Marketing & Performance Ads",
            subtitleHindi = "मेटा ऐड्स, गूगल ऐड्स और सेल्स फनल से प्रॉफिट कमाएं",
            description = "Learn Meta Ads Manager, ROAS optimization, audience targeting, high-converting copywriting, and client acquisition funnels.",
            earningPotential = "₹45,000 - ₹1,80,000 / month",
            averageStartingRate = "₹20,000 / month per client retainer",
            durationHours = 26,
            lessonsCount = 16,
            tags = listOf("High ROI", "Direct Earning", "Growth"),
            toolsCovered = listOf("Meta Ads Manager", "Google Analytics 4", "Canva", "Shopify Pixel", "WhatsApp Business API"),
            stages = TrackStages(
                learn = LearnStage(
                    title = "Learn: Media Buying & Funnels in Hindi",
                    videoTitleHindi = "मेटा ऐड्स से 3x-5x ROAS कैसे हासिल करें?",
                    videoDuration = "46:10 mins HD",
                    instructor = "Rohan Malhotra (D2C Growth Marketer)",
                    videoChapters = listOf(
                        VideoChapter("00:00", "Meta Ads Algorithm Explained in Simple Hindi"),
                        VideoChapter("10:20", "CBO vs ABO: Budgeting Strategies for 2026"),
                        VideoChapter("21:40", "Hook-Story-Offer: Writing Copy that Converts"),
                        VideoChapter("33:00", "Setting up Conversions API & Pixel Tracking"),
                        VideoChapter("41:15", "Audience Retargeting & Scaling Without Burning Cash")
                    ),
                    transcriptHindiExcerpt = "डिजिटल मार्केटिंग में तुक्का नहीं चलता, डेटा चलता है। अगर आपका हुक पहले 3 सेकंड में ऑडियंस को नहीं रोक पाया तो आपका पूरा ऐड बजट बर्बाद हो जाएगा।",
                    keyConcepts = listOf("ROAS (Return on Ad Spend)", "CAC & LTV Calculation", "Broad vs Lookalike Targeting", "Creative Fatigue")
                ),
                practice = PracticeStage(
                    title = "Practice: Full Funnel Architecture",
                    infographicTitle = "E-Commerce Profit Funnel Blueprint",
                    infographicDescription = "Visual diagram representing Top of Funnel (TOF), Middle of Funnel (MOF), and Bottom of Funnel (BOF).",
                    infographicDiagramSteps = listOf(
                        DiagramStep(1, "Top of Funnel (TOF)", "Broad interest video ads introducing customer pain points", 0xFFFFD700),
                        DiagramStep(2, "Middle of Funnel (MOF)", "Social proof, unboxing videos, and founder story", 0xFF00E676),
                        DiagramStep(3, "Bottom of Funnel (BOF)", "Cart abandonment discounts via WhatsApp & Dynamic Ads", 0xFF38BDF8),
                        DiagramStep(4, "Post-Purchase Retention", "VIP upsell and referral programs for recurring profit", 0xFFA855F7)
                    ),
                    downloadableNotesTitle = "Meta_Ads_Mastery_Indian_Ecom.pdf",
                    downloadableNotesSize = "5.4 MB (48 Pages)",
                    downloadableNotesKeyPoints = listOf(
                        "Exact ad copy formulas: PAS (Problem-Agitate-Solution) in Hindi/English",
                        "Negative keyword master list for Indian eCommerce",
                        "Weekly ad scaling checklist (15% budget jump rule)",
                        "Client reporting dashboard spreadsheet template"
                    ),
                    quizQuestions = listOf(
                        QuizQuestion(
                            id = 1,
                            questionHindi = "ROAS (Return on Ad Spend) का क्या अर्थ है?",
                            questionEnglish = "What does ROAS stand for?",
                            options = listOf(
                                "रेडियो पर ऐड चलाने का खर्च",
                                "ऐड्स पर खर्च किए गए प्रत्येक रुपये पर हुई कुल बिक्री का अनुपात",
                                "इंटरनेट कनेक्शन का बिल",
                                "सोशल मीडिया फॉलोअर्स की संख्या"
                            ),
                            correctIndex = 1,
                            explanationHindi = "ROAS = Total Revenue Generated / Total Ad Spend. उदा. ₹10k खर्च करके ₹40k सेल हुई तो ROAS = 4x."
                        )
                    )
                ),
                project = ProjectStage(
                    projectTitle = "Launch Ad Campaign for Indian D2C Footwear Brand",
                    clientContext = "Client: 'UrbanSole' Shoes (Jaipur). Monthly ad budget ₹1,00,000.",
                    problemStatementHindi = "क्लाइंट का ROAS 1.4x पर अटका हुआ है जिससे लॉस हो रहा है। आपको 3 नए वीडियो ऐड एंगल्स और CBO कैंपेन प्लान करके ROAS को 3.2x तक पहुँचाना है।",
                    deliverableChecklist = listOf(
                        "Target Audience Personas & Demographic Matrix",
                        "6 Ad Creatives copy scripts in Hinglish",
                        "Full Campaign Setup Structure in Meta Ads Manager",
                        "Live weekly optimization spreadsheet"
                    ),
                    expectedOutcome = "Achieve break-even in 7 days and 3.2x ROAS in 30 days.",
                    estimatedProjectValue = "₹25,000 monthly retainer + 5% ad spend bonus"
                ),
                portfolio = PortfolioStage(
                    title = "Portfolio: Performance Marketer Profile",
                    portfolioPieceTitle = "UrbanSole 3.4x ROAS Scale Case Study",
                    mockupDescription = "Clean screenshot mockup of Meta Ads Manager dashboard with highlighted revenue metrics and low CPA.",
                    clientPitchHindi = "'मैंने D2C और लोकल बिज़नेसेज के लिए ₹15 लाख से अधिक का ऐड बजट मैनेज किया है और 3x+ ROAS कंसिस्टेंटली डिलीवर किया है।'",
                    recommendedTags = listOf("Meta Ads", "Performance Marketing", "Copywriting", "ROAS Optimization")
                ),
                earn = EarnStage(
                    title = "Earn: Sign Monthly Marketing Retainers",
                    gigTitleExample = "I will setup and scale high ROAS Meta Ads for your eCommerce brand",
                    beginnerPriceRange = "₹15,000 - ₹25,000 / month per client",
                    proPriceRange = "₹40,000 - ₹1,00,000 / month per client",
                    topPlatforms = listOf("Direct Instagram D2C Brand Outreach", "LinkedIn", "Upwork"),
                    proposalScriptHindi = "नमस्ते [Brand Founder], मैंने देखा कि आप इंस्टाग्राम पर ऐड्स चला रहे हैं लेकिन रीमार्केटिंग एक्टिव नहीं है। मैंने एक 3-मिनट का वीडियो ऑडिट बनाया है जिसमें बताया है कि आपका 30% बजट कहाँ लीक हो रहा है। क्या मैं लिंक शेयर करूँ?",
                    coldEmailPitch = "Hey [Founder], found your brand on Instagram. Noticed you don't have Facebook Conversions API configured, losing ~20% of purchase data. I made a 2-min video showing how to fix it and boost ROAS. Mind if I share?"
                )
            )
        ),
        LearningTrack(
            id = "data-analytics",
            title = "Data Analytics (Excel, SQL, Power BI)",
            subtitleHindi = "बिज़नेस डेटा एनालाइज़ करें और इंटरेक्टिव डैशबोर्ड बनाएं",
            description = "Advanced Excel formulas, SQL database querying, Power BI interactive visualization, and turning raw data into high-value executive dashboards.",
            earningPotential = "₹40,000 - ₹1,60,000 / month",
            averageStartingRate = "₹15,000 per dashboard project",
            durationHours = 28,
            lessonsCount = 20,
            tags = listOf("High Value", "Analytical", "Corporate & Freelance"),
            toolsCovered = listOf("Excel Advanced (XLOOKUP, Power Query)", "PostgreSQL / MySQL", "Power BI", "DAX", "Tableau Basics"),
            stages = TrackStages(
                learn = LearnStage(
                    title = "Learn: From Raw Data to Executive Insights",
                    videoTitleHindi = "Excel, SQL और Power BI से बिज़नेस डेटा को विजुअलाइज़ करना",
                    videoDuration = "50:00 mins HD",
                    instructor = "Amitabh Verma (Lead Data Scientist)",
                    videoChapters = listOf(
                        VideoChapter("00:00", "Data Analytics Career & Freelance Landscape in India"),
                        VideoChapter("09:15", "Advanced Excel: Power Query, XLOOKUP, Pivot Tables"),
                        VideoChapter("22:30", "SQL Essentials: SELECT, JOINs, GROUP BY, Window Functions"),
                        VideoChapter("36:10", "Building Your First Power BI Dynamic Dashboard"),
                        VideoChapter("44:20", "DAX Formulas: Total Sales, YoY Growth, KPI Cards")
                    ),
                    transcriptHindiExcerpt = "कंपनियों के पास डेटा का अंबार है, लेकिन उन्हें यह नहीं पता कि कौन सा प्रोडक्ट प्रॉफिट दे रहा है और कौन सा लॉस। एक डेटा एनालिस्ट वही रोशनी दिखाता है जिसके लिए क्लाइंट लाखों देने को तैयार होते हैं।",
                    keyConcepts = listOf("Data Cleaning & Normalization", "Relational Database Schema", "DAX Measures", "Interactive Drillthroughs")
                ),
                practice = PracticeStage(
                    title = "Practice: Interactive Sales Analytics Pipeline",
                    infographicTitle = "Business Intelligence Pipeline Infographic",
                    infographicDescription = "Visual walkthrough from raw CSV/SQL server through ETL to beautiful executive dashboard.",
                    infographicDiagramSteps = listOf(
                        DiagramStep(1, "Raw Data Ingestion", "CSV, Excel sheets, and SQL database feeds", 0xFFFFD700),
                        DiagramStep(2, "ETL in Power Query", "Removing nulls, formatting dates, merging tables", 0xFF00E676),
                        DiagramStep(3, "Data Modeling", "Creating Star Schema with Fact and Dimension tables", 0xFF38BDF8),
                        DiagramStep(4, "Interactive UI", "KPI cards, heatmaps, and slicers with gold dark theme", 0xFFA855F7)
                    ),
                    downloadableNotesTitle = "DSA_SQL_&_PowerBI_Handbook.pdf",
                    downloadableNotesSize = "7.2 MB (64 Pages)",
                    downloadableNotesKeyPoints = listOf(
                        "50 most frequently used SQL interview & freelance queries",
                        "DAX cheat sheet: CALCULATE, ALL, SAMEPERIODLASTYEAR",
                        "Top 10 executive color palettes for Power BI dashboards",
                        "Sample datasets (Retail, Healthcare, Finance) for practice"
                    ),
                    quizQuestions = listOf(
                        QuizQuestion(
                            id = 1,
                            questionHindi = "SQL में दो अलग-अलग टेबल्स का डेटा एक साथ देखने के लिए किसका उपयोग होता है?",
                            questionEnglish = "Which SQL clause is used to combine data from two related tables?",
                            options = listOf(
                                "JOIN",
                                "DELETE",
                                "STOP",
                                "PRINT"
                            ),
                            correctIndex = 0,
                            explanationHindi = "JOIN (जैसे INNER JOIN, LEFT JOIN) की मदद से कॉमन की (Foreign Key) के आधार पर दो टेबल्स का डेटा मिलाया जाता है।"
                        )
                    )
                ),
                project = ProjectStage(
                    projectTitle = "Omnichannel Retail Profit & Inventory Dashboard",
                    clientContext = "Client: 'MegaMart Retail' (12 offline stores + online store). 50,000 monthly transactions.",
                    problemStatementHindi = "क्लाइंट को यह पता नहीं चल रहा कि किस स्टोर में कौन सा स्टॉक खत्म हो रहा है और किस कैटेगरी का रिटर्न रेट सबसे ज्यादा है। आपको एक रियल-टाइम Power BI डैशबोर्ड बनाना है।",
                    deliverableChecklist = listOf(
                        "Cleaned Data Model in Power Query (Star Schema)",
                        "Executive Summary View (Revenue, Margin, Units Sold)",
                        "Inventory Health Matrix (Out of stock alert markers)",
                        "Recorded 5-minute Loom presentation for store managers"
                    ),
                    expectedOutcome = "Reduced dead inventory holding cost by 18%.",
                    estimatedProjectValue = "₹30,000 - ₹50,000 one-time"
                ),
                portfolio = PortfolioStage(
                    title = "Portfolio: Power BI Specialist Showcase",
                    portfolioPieceTitle = "MegaMart Retail Executive BI Suite",
                    mockupDescription = "Interactive web embed or video presentation of Power BI dashboard with sleek dark gold aesthetic.",
                    clientPitchHindi = "'मैंने रीटेल और ईकॉमर्स बिज़नेसेज के लिए एंड-टू-एंड डेटा पाइपलाइन्स और Power BI डैशबोर्ड्स बनाए हैं जो लाखों की इन्वेंट्री लॉस रोकते हैं।' ",
                    recommendedTags = listOf("Power BI", "SQL", "Advanced Excel", "Data Visualization")
                ),
                earn = EarnStage(
                    title = "Earn: Remote Analytics Consulting",
                    gigTitleExample = "I will build interactive Power BI and Excel dashboards with SQL insights",
                    beginnerPriceRange = "₹12,000 - ₹20,000 per dashboard",
                    proPriceRange = "₹35,000 - ₹80,000 per dashboard / $40/hr on Upwork",
                    topPlatforms = listOf("Upwork", "Fiverr Pro", "LinkedIn Analytics Communities", "Direct B2B Outreach"),
                    proposalScriptHindi = "नमस्ते! मैंने आपके प्रोजेक्ट का डेटा स्पेसिफिकेशन देखा। मैंने पहले भी 50k+ रोज़ के रीटेल डेटा को Power BI में कन्वर्ट किया है। मैं आपको 24 घंटे में एक प्रोटोटाइप मॉकअप भेज सकता हूँ।",
                    coldEmailPitch = "Hi [COO/Founder], noticed your team manages multiple sales channels. Many companies lose 10+ hours weekly compiling manual Excel sheets. I build automated Power BI dashboards that refresh daily. Here is a live sample link."
                )
            )
        ),
        LearningTrack(
            id = "freelancing",
            title = "Freelancing Mastery & Global Clients",
            subtitleHindi = "अपवर्क, फाइवर और कोल्ड ईमेलिंग से डॉलर में क्लाइंट्स जीतें",
            description = "Profile optimization, proposal writing, portfolio presentation, pricing psychology, client negotiation, and international payments.",
            earningPotential = "₹60,000 - ₹3,00,000 / month",
            averageStartingRate = "$25 / hour (₹2,100/hr)",
            durationHours = 22,
            lessonsCount = 14,
            tags = listOf("Income Booster", "Client Acquisition", "Remote Work"),
            toolsCovered = listOf("Upwork", "Fiverr", "LinkedIn Sales Navigator", "Payoneer / Wise", "Contract & Invoicing Tools"),
            stages = TrackStages(
                learn = LearnStage(
                    title = "Learn: High-Ticket Freelance Client Acquisition",
                    videoTitleHindi = "अपवर्क पर 80% प्रपोजल रिस्पॉन्स रेट कैसे पाएं?",
                    videoDuration = "44:30 mins HD",
                    instructor = "Kunal Kashyap (Top Rated Plus Upwork Freelancer - $150k+ Earned)",
                    videoChapters = listOf(
                        VideoChapter("00:00", "Freelancing Mindset: Vendor vs Partner"),
                        VideoChapter("07:40", "The 100% Upwork Profile Optimization Checklist"),
                        VideoChapter("18:15", "The 4-Sentence Proposal Formula that Clients Can't Ignore"),
                        VideoChapter("29:30", "Live Loom Audit Technique to Close High-Ticket Clients"),
                        VideoChapter("38:00", "Managing International Payments & Indian Taxes (44ADA)")
                    ),
                    transcriptHindiExcerpt = "क्लाइंट आपके बारे में नहीं, अपनी समस्या के समाधान के बारे में सोचना चाहता है। 'I am a passionate worker' लिखना बंद कीजिए और क्लाइंट की प्रॉब्लम पर पहले 2 वाक्यों में हिट कीजिए।",
                    keyConcepts = listOf("First 2 Lines Hook", "Social Proof Embeds", "Value-Based Pricing", "Scarcity & Guarantee")
                ),
                practice = PracticeStage(
                    title = "Practice: Proposal & Rate Calculator",
                    infographicTitle = "High-Ticket Client Acquisition Funnel",
                    infographicDescription = "Visual blueprint from job feed filter to winning contract signature in 5 stages.",
                    infographicDiagramSteps = listOf(
                        DiagramStep(1, "Job Filtering", "Filtering for Payment Verified + >$1,000 spent clients", 0xFFFFD700),
                        DiagramStep(2, "Instant Value Proposal", "Sending 60-second video Loom audit instead of text", 0xFF00E676),
                        DiagramStep(3, "Discovery Call", "Asking diagnostic questions in Hindi or English", 0xFF38BDF8),
                        DiagramStep(4, "Contract & Escrow", "100% milestone funded before starting any deliverable", 0xFFA855F7)
                    ),
                    downloadableNotesTitle = "DSA_Freelancer_Contract_&_Pitch_Vault.pdf",
                    downloadableNotesSize = "4.8 MB (45 Pages)",
                    downloadableNotesKeyPoints = listOf(
                        "15 Copy-paste winning proposal templates for Upwork & Fiverr",
                        "Standard Indian Freelancer Legal Contract Template",
                        "Tax saving guide under Section 44ADA (save 50% tax)",
                        "Payoneer/Wise low-fee setup step-by-step guide"
                    ),
                    quizQuestions = listOf(
                        QuizQuestion(
                            id = 1,
                            questionHindi = "अपवर्क प्रपोजल की पहली दो लाइनों में क्या लिखना सबसे असरदार होता है?",
                            questionEnglish = "What is most effective in the first two lines of an Upwork proposal?",
                            options = listOf(
                                "अपना पूरा बायोडाटा और स्कूल की डिग्री",
                                "क्लाइंट की मुख्य समस्या का सीधा समाधान और एक कस्टमाइज्ड प्रश्न",
                                "अपनी मजबूरी बताना",
                                "कॉपी-पेस्ट किया हुआ लंबा पैराग्राफ"
                            ),
                            correctIndex = 1,
                            explanationHindi = "क्लाइंट को नोटिफिकेशन में सिर्फ पहली 2 लाइनें दिखती हैं। अगर वहाँ सीधा सॉल्यूशन हो, तो वो प्रपोजल जरूर खोलता है।"
                        )
                    )
                ),
                project = ProjectStage(
                    projectTitle = "Profile Makeover & 5 Video Proposals Sprint",
                    clientContext = "Real World Simulation: Pitching 5 real verified international clients on Upwork/LinkedIn.",
                    problemStatementHindi = "आपको 7 दिनों के अंदर अपना प्रोफाइल 100% ऑप्टिमाइज़ करना है और 5 टारगेटेड क्लाइंट्स को कस्टमाइज्ड वीडियो प्रपोजल भेजकर 2 इंटरव्यू हासिल करने हैं।",
                    deliverableChecklist = listOf(
                        "Optimized Upwork/Fiverr Profile Title, Overview & Portfolio",
                        "Custom Loom video link recording for a real job brief",
                        "Written 4-sentence proposal drafted using DSA formula",
                        "Client communication tracker sheet"
                    ),
                    expectedOutcome = "Win your first or next $500+ client contract.",
                    estimatedProjectValue = "Unlimited lifetime earning potential ($1,000+ / mo)"
                ),
                portfolio = PortfolioStage(
                    title = "Portfolio: High-Conversion Freelance Profile",
                    portfolioPieceTitle = "Upwork Top-Rated Profile Blueprint",
                    mockupDescription = "Full visual showcase of a 100% complete Upwork agency & individual profile layout with badge.",
                    clientPitchHindi = "'I help overseas businesses streamline their design and automation pipelines with zero management overhead.'",
                    recommendedTags = listOf("Upwork", "Freelance Strategy", "Client Negotiation", "Proposal Writing")
                ),
                earn = EarnStage(
                    title = "Earn: Scaling to $2,000 / month",
                    gigTitleExample = "Hourly & Fixed Price Consulting Contract",
                    beginnerPriceRange = "₹30,000 - ₹50,000 / month",
                    proPriceRange = "₹1,50,000 - ₹3,50,000 / month ($2k - $4k)",
                    topPlatforms = listOf("Upwork", "Direct LinkedIn Outreach", "Contra", "Wellfound"),
                    proposalScriptHindi = "Hi [Client], I read your requirement for [Task]. Instead of telling you what I can do, I recorded a 60-second video showing exactly how I'll solve it for you today: [Loom Link]. Let me know your thoughts!",
                    coldEmailPitch = "Hey [First Name], saw your post on Upwork. Here is a quick 1-min Loom showing the prototype I built specifically for your project: [Link]. Ready to deploy whenever you are!"
                )
            )
        ),
        LearningTrack(
            id = "personal-branding",
            title = "Personal Branding & Content Creation",
            subtitleHindi = "लिंक्डइन, यूट्यूब और X पर अथॉरिटी बनाएं और इनबाउंड क्लाइंट्स पाएं",
            description = "Build a magnetic personal brand on LinkedIn and YouTube. Master short-form reels, storytelling, carousel design, and turning followers into paying clients.",
            earningPotential = "₹50,000 - ₹2,50,000 / month",
            averageStartingRate = "₹25,000 per brand consulting deal",
            durationHours = 18,
            lessonsCount = 12,
            tags = listOf("Authority", "Inbound Leads", "Audience"),
            toolsCovered = listOf("LinkedIn Creator Mode", "Notion Content OS", "Canva Carousels", "CapCut / Premiere", "X / Typefully"),
            stages = TrackStages(
                learn = LearnStage(
                    title = "Learn: The Inbound Lead Engine in Hindi",
                    videoTitleHindi = "बिना कोल्ड कॉलिंग के लिंक्डइन से हाई-पेइंग क्लाइंट्स कैसे आकर्षित करें?",
                    videoDuration = "36:45 mins HD",
                    instructor = "Pooja Singhania (LinkedIn Top Voice & Ghostwriter)",
                    videoChapters = listOf(
                        VideoChapter("00:00", "Why Personal Branding is the Ultimate Insurance in 2026"),
                        VideoChapter("08:30", "LinkedIn Profile Optimization: Banner, Headline & Featured Section"),
                        VideoChapter("17:40", "The Hook-Story-Lesson Content Framework"),
                        VideoChapter("26:15", "Carousels that get 50,000+ Impressions in Canva"),
                        VideoChapter("32:00", "DM Funnel: Converting Post Comments into Discovery Calls")
                    ),
                    transcriptHindiExcerpt = "जब लोग आपको एक फील्ड में एक्सपर्ट मानने लगते हैं, तो आपको काम माँगने की ज़रूरत नहीं पड़ती; लोग खुद आपके इनबॉक्स में आकर पूछते हैं, 'क्या आप हमारे लिए काम कर सकते हैं?'",
                    keyConcepts = listOf("Profile Real Estate", "Engagement Algorithms", "Storytelling Frameworks", "Comment-to-DM Funnel")
                ),
                practice = PracticeStage(
                    title = "Practice: 30-Day Content Calendar & Carousel System",
                    infographicTitle = "Inbound Authority Funnel",
                    infographicDescription = "How a single viral post converts casual readers into high-ticket inbound client calls.",
                    infographicDiagramSteps = listOf(
                        DiagramStep(1, "Hook Content", "High-value actionable breakdown or contrarian perspective", 0xFFFFD700),
                        DiagramStep(2, "Lead Magnet", "Offering free PDF / template in comments ('Comment TEMPLATE')", 0xFF00E676),
                        DiagramStep(3, "Automated DM", "Sending resource link with friendly intro in inbox", 0xFF38BDF8),
                        DiagramStep(4, "Discovery Call", "Booked calendar meeting with qualified high-ticket client", 0xFFA855F7)
                    ),
                    downloadableNotesTitle = "LinkedIn_Personal_Brand_OS.pdf",
                    downloadableNotesSize = "5.1 MB (40 Pages)",
                    downloadableNotesKeyPoints = listOf(
                        "50 Proven viral LinkedIn hooks for tech and freelance niches",
                        "30-Day Notion content calendar template",
                        "Carousel design dimensions & swipe optimization guide",
                        "DM closing scripts to convert fans to clients"
                    ),
                    quizQuestions = listOf(
                        QuizQuestion(
                            id = 1,
                            questionHindi = "लिंक्डइन हेडलाइन में केवल 'Student' लिखने की बजाय क्या लिखना बेहतर है?",
                            questionEnglish = "What is better to write in a LinkedIn headline instead of just 'Student'?",
                            options = listOf(
                                "सिर्फ अपना फोन नंबर",
                                "आप किस क्लाइंट की क्या समस्या हल करते हैं (Value Proposition)",
                                "खाली छोड़ देना",
                                "मूवी के डायलॉग"
                            ),
                            correctIndex = 1,
                            explanationHindi = "हेडलाइन में स्पष्ट होना चाहिए कि आप किस ऑडियंस के लिए क्या वैल्यू डिलीवर करते हैं (उदा. 'Helping D2C brands scale to 4x ROAS with Meta Ads')."
                        )
                    )
                ),
                project = ProjectStage(
                    projectTitle = "30-Day Thought Leadership Campaign & 3 Carousels",
                    clientContext = "Self or Client: Building an authoritative personal brand in your primary skill niche.",
                    problemStatementHindi = "आपको 30 दिनों का कंटेंट कैलेंडर तैयार करना है, लिंक्डइन प्रोफाइल को रीडिज़ाइन करना है और 3 हाई-कन्वर्टिंग कैरोसेल पब्लिश करने हैं।",
                    deliverableChecklist = listOf(
                        "Revamped Banner, Headline and About section",
                        "3 High-Quality Canva Carousels (PDF format)",
                        "30-Day Content Topic Matrix in Notion",
                        "Lead magnet giveaway post with automated comment trigger"
                    ),
                    expectedOutcome = "Gain 1,000+ targeted industry followers and 3-5 inbound client inquiries.",
                    estimatedProjectValue = "₹20,000 - ₹40,000 if ghostwriting for founders"
                ),
                portfolio = PortfolioStage(
                    title = "Portfolio: Brand Authority Case Study",
                    portfolioPieceTitle = "Zero to 10k Followers & Inbound Funnel Case Study",
                    mockupDescription = "Visual case study showing impressions graph, top posts, and inbound DM screenshots.",
                    clientPitchHindi = "'मैंने फाउंडर्स और फ्रीलांसर्स के लिंक्डइन को इनबाउंड लीड जनरेशन मशीन में बदला है। आइए आपका ब्रांड भी स्केल करें।' ",
                    recommendedTags = listOf("LinkedIn Growth", "Content Strategy", "Ghostwriting", "Personal Branding")
                ),
                earn = EarnStage(
                    title = "Earn: Founder Ghostwriting & Brand Consulting",
                    gigTitleExample = "I will ghostwrite viral LinkedIn content & build your founder personal brand",
                    beginnerPriceRange = "₹20,000 - ₹35,000 / month per client",
                    proPriceRange = "₹60,000 - ₹1,50,000 / month per founder",
                    topPlatforms = listOf("LinkedIn InMail", "Twitter/X", "Direct Founder Networking"),
                    proposalScriptHindi = "नमस्ते [Founder Name], आपका प्रोडक्ट बहुत शानदार है लेकिन आपकी पर्सनल प्रोफाइल पिछले 3 महीने से इनएक्टिव है। मैंने आपके लिए 2 कस्टमाइज्ड पोस्ट्स का ड्राफ्ट तैयार किया है। अगर पसंद आए तो हम महीने का कैलेंडर प्लान कर सकते हैं।",
                    coldEmailPitch = "Hey [Founder], your company is doing great things, but your LinkedIn profile is leaving thousands of inbound leads on the table. I ghostwrite for tech founders. Attached are 2 sample posts tailored to your voice. Care to take a look?"
                )
            )
        ),
        LearningTrack(
            id = "digital-products",
            title = "Digital Products & E-commerce",
            subtitleHindi = "नोशन टेम्पलेट्स, ई-बुक्स और डिजिटल एसेट्स बनाकर पैसिव इनकम कमाएं",
            description = "Create once, sell infinite times. Master Notion template engineering, Gumroad/Shopify store building, digital marketing, and automated passive revenue.",
            earningPotential = "₹30,000 - ₹2,00,000 / month (Passive)",
            averageStartingRate = "₹499 - ₹2,999 per digital download",
            durationHours = 20,
            lessonsCount = 13,
            tags = listOf("Passive Income", "Scalable", "High Margin"),
            toolsCovered = listOf("Notion Pro", "Gumroad", "Shopify", "Instamojo / Razorpay", "Canva"),
            stages = TrackStages(
                learn = LearnStage(
                    title = "Learn: The 'Build Once, Sell Forever' Model",
                    videoTitleHindi = "डिजिटल प्रोडक्ट्स से पैसिव इनकम का पूरा ब्लूप्रिंट",
                    videoDuration = "40:20 mins HD",
                    instructor = "Deepak Chaurasia (Digital Creator - ₹40L+ in Digital Sales)",
                    videoChapters = listOf(
                        VideoChapter("00:00", "Economics of Digital Products: 95% Profit Margins"),
                        VideoChapter("09:10", "Finding High-Demand Product Ideas (Notion, Guides, Bundles)"),
                        VideoChapter("19:40", "Building Advanced Functional Notion Templates in Hindi"),
                        VideoChapter("29:30", "Setting up Gumroad, Razorpay & Instant Delivery Emails"),
                        VideoChapter("36:00", "Zero-Cost Traffic Strategies using Pinterest, X & YouTube")
                    ),
                    transcriptHindiExcerpt = "फ्रीलांसिंग में जब तक आप काम करते हैं तब तक पैसा आता है। लेकिन डिजिटल प्रोडक्ट वो एसेट है जो जब आप सो रहे होते हैं तब भी सेल जनरेट करता है। इस लेसन में हम पहला प्रोडक्ट लाइव लॉन्च करेंगे।",
                    keyConcepts = listOf("Product-Market Fit for Templates", "Pricing Psychology", "Instant Webhook Delivery", "Organic Traffic Funnels")
                ),
                practice = PracticeStage(
                    title = "Practice: High-Converting Product Landing Page",
                    infographicTitle = "Digital Product Passive Funnel",
                    infographicDescription = "Visual diagram from social media teaser to instant UPI checkout and automated email download link.",
                    infographicDiagramSteps = listOf(
                        DiagramStep(1, "Value Teaser", "Short demo reel of template solving real problem", 0xFFFFD700),
                        DiagramStep(2, "Gumroad / Store", "High-converting sales copy with customer reviews", 0xFF00E676),
                        DiagramStep(3, "UPI / Card Checkout", "Instant Razorpay/Gumroad seamless payment", 0xFF38BDF8),
                        DiagramStep(4, "Automated Access", "Instant redirect to Notion duplicate link + bonus pack", 0xFFA855F7)
                    ),
                    downloadableNotesTitle = "DSA_Digital_Product_Launch_Kit.pdf",
                    downloadableNotesSize = "6.1 MB (52 Pages)",
                    downloadableNotesKeyPoints = listOf(
                        "Top 20 profitable digital product ideas that sell in India right now",
                        "High-converting Gumroad landing page copy template",
                        "Razorpay Payment Pages setup guide without needing a custom website",
                        "Customer support and refund prevention policy handbook"
                    ),
                    quizQuestions = listOf(
                        QuizQuestion(
                            id = 1,
                            questionHindi = "डिजिटल प्रोडक्ट का सबसे बड़ा फायदा क्या है?",
                            questionEnglish = "What is the biggest advantage of a digital product over a physical product?",
                            options = listOf(
                                "कोई इन्वेंट्री या शिपिंग खर्च नहीं, 90%+ प्रॉफिट मार्जिन और असीमित स्केलिंग",
                                "इसे सिर्फ एक ही व्यक्ति खरीद सकता है",
                                "हर बार नया प्रोडक्ट हाथ से बनाना पड़ता है",
                                "इसमें कोई पेमेंट नहीं मिलती"
                            ),
                            correctIndex = 0,
                            explanationHindi = "डिजिटल प्रोडक्ट को एक बार बनाकर लाखों बार बिना किसी अतिरिक्त मैन्युफैक्चरिंग या शिपिंग कॉस्ट के बेचा जा सकता है।"
                        )
                    )
                ),
                project = ProjectStage(
                    projectTitle = "Launch a Complete 'Freelancer OS' Notion Template",
                    clientContext = "Project: Build and launch a flagship productivity system for freelancers and agency owners.",
                    problemStatementHindi = "फ्रीलांसर्स क्लाइंट्स, इनवॉइस और प्रोजेक्ट्स मैनेज करने में उलझ जाते हैं। आपको एक कम्प्लीट 'Freelancer OS' नोशन टेम्पलेट बनाना है और इसे Gumroad पर लिस्ट करना है।",
                    deliverableChecklist = listOf(
                        "Notion Template with CRM, Invoice Tracker, and Task Board",
                        "Attractive 3D mockup graphics made in Canva",
                        "Gumroad Sales Page with persuasive copy in Hinglish/English",
                        "Product Launch Twitter/LinkedIn Thread and Reel script"
                    ),
                    expectedOutcome = "Complete functional product live on the internet ready for sales.",
                    estimatedProjectValue = "Scalable to ₹50,000 - ₹2,00,000+ monthly recurring sales"
                ),
                portfolio = PortfolioStage(
                    title = "Portfolio: Digital Product Creator Profile",
                    portfolioPieceTitle = "Freelancer OS - Flagship Productivity System",
                    mockupDescription = "Sleek dark mode laptop & tablet mockup showcasing the Notion workspace and Gumroad sales dashboard.",
                    clientPitchHindi = "'मैंने डिजिटल टूल्स और नोशन सिस्टम्स बनाए हैं जिन्हें 500+ प्रोफेशनल्स ने खरीदा है। आइए आपके ज्ञान को भी डिजिटल प्रोडक्ट में बदलें।' ",
                    recommendedTags = listOf("Digital Products", "Notion", "Gumroad", "E-Commerce", "Passive Income")
                ),
                earn = EarnStage(
                    title = "Earn: Scaling Product Sales",
                    gigTitleExample = "Sell products globally on Gumroad + Build custom Notion workspaces for clients",
                    beginnerPriceRange = "₹10,000 - ₹30,000 / month from downloads",
                    proPriceRange = "₹75,000 - ₹2,50,000 / month passive revenue",
                    topPlatforms = listOf("Gumroad", "Notion Marketplace", "Shopify Digital", "Instagram / YouTube"),
                    proposalScriptHindi = "नमस्ते! अगर आप अपने बिज़नेस का वर्कफ़्लो नोशन में कस्टमाइज़ करना चाहते हैं, तो मैंने 50+ बिज़नेसेज के लिए कस्टम सिस्टम्स बनाए हैं। क्या हम एक क्विक वॉकथ्रू कॉल शेड्यूल करें?",
                    coldEmailPitch = "Hey [Name], saw your team uses disjointed tools for project tracking. I build custom Notion operating systems that replace 5 paid tools into one. Would love to send a 2-min demo of a system I built for a similar agency."
                )
            )
        ),
        LearningTrack(
            id = "no-code-dev",
            title = "No-Code App & Web Development",
            subtitleHindi = "फ्लटरफ्लो, बबल और वेबफ्लो से बिना कोडिंग ऐप्स बनाएं",
            description = "Build full-stack responsive web apps, mobile apps, database integrations, APIs, and client portals without writing complex code.",
            earningPotential = "₹50,000 - ₹2,20,000 / month",
            averageStartingRate = "₹35,000 - ₹1,00,000 per app project",
            durationHours = 28,
            lessonsCount = 18,
            tags = listOf("High Ticket", "Software MVP", "In Demand"),
            toolsCovered = listOf("FlutterFlow", "Bubble.io", "Webflow", "Glide Apps", "Supabase / Firebase"),
            stages = TrackStages(
                learn = LearnStage(
                    title = "Learn: Visual Full-Stack Development in Hindi",
                    videoTitleHindi = "FlutterFlow और Bubble से बिना कोडिंग रियल ऐप्स कैसे बनाएं?",
                    videoDuration = "48:30 mins HD",
                    instructor = "Tarun Grover (No-Code Lead & Agency Founder)",
                    videoChapters = listOf(
                        VideoChapter("00:00", "The No-Code Revolution: Why Startups Pay ₹1L+ for MVPs"),
                        VideoChapter("08:50", "FlutterFlow Interface & Component Tree Explained in Hindi"),
                        VideoChapter("19:20", "Database Architecture: Collections, Documents, Relations in Firebase"),
                        VideoChapter("31:40", "API Integration: Connecting Razorpay & SMS OTPs"),
                        VideoChapter("41:10", "Exporting Clean Flutter Code & App Store Deployment")
                    ),
                    transcriptHindiExcerpt = "पहले एक ऐप बनाने में 6 महीने और 10 लाख रुपये लगते थे। आज नो-कोड टूल्स की मदद से आप वही ऐप 2 हफ्तों में बनाकर क्लाइंट से ₹50,000 से ₹1,50,000 चार्ज कर सकते हैं।",
                    keyConcepts = listOf("Component Architecture", "State Management in No-Code", "REST API Endpoints", "Responsive Breakpoints")
                ),
                practice = PracticeStage(
                    title = "Practice: Full-Stack App Architecture",
                    infographicTitle = "No-Code Mobile App Stack",
                    infographicDescription = "Visual walkthrough of FlutterFlow frontend, Firebase authentication, and Supabase backend with API layer.",
                    infographicDiagramSteps = listOf(
                        DiagramStep(1, "UI / Screens", "FlutterFlow drag-and-drop responsive visual canvas", 0xFFFFD700),
                        DiagramStep(2, "Authentication", "Google / Phone OTP login with secure session storage", 0xFF00E676),
                        DiagramStep(3, "Database & Logic", "Firebase Firestore real-time queries and custom functions", 0xFF38BDF8),
                        DiagramStep(4, "Payment Gateway", "Razorpay / Stripe checkout webhook confirmation", 0xFFA855F7)
                    ),
                    downloadableNotesTitle = "DSA_NoCode_App_Builder_Handbook.pdf",
                    downloadableNotesSize = "8.0 MB (68 Pages)",
                    downloadableNotesKeyPoints = listOf(
                        "Complete database schema blueprint for Marketplaces, Delivery and Booking apps",
                        "FlutterFlow custom actions and code snippets cheat sheet",
                        "App Store & Google Play Console submission checklist without rejections",
                        "Client handover and maintenance agreement contract"
                    ),
                    quizQuestions = listOf(
                        QuizQuestion(
                            id = 1,
                            questionHindi = "No-Code टूल्स (जैसे FlutterFlow) में 'API' का मुख्य काम क्या होता है?",
                            questionEnglish = "What is the primary function of an API in No-Code tools?",
                            options = listOf(
                                "ऐप की स्क्रीन का रंग बदलना",
                                "बाहरी सर्विसेज (जैसे पेमेंट्स, एसएमएस, मौसम) से डेटा लेन-देन करना",
                                "कंप्यूटर को शटडाउन करना",
                                "केवल पासवर्ड सेव करना"
                            ),
                            correctIndex = 1,
                            explanationHindi = "API की मदद से आपका No-Code ऐप Razorpay, Google Maps या किसी भी थर्ड-पार्टी सर्विस से डेटा कनेक्ट करता है।"
                        )
                    )
                ),
                project = ProjectStage(
                    projectTitle = "On-Demand Home Services Booking App MVP",
                    clientContext = "Client: 'QuickFix Services' (Startup). Needs an MVP for customers to book electricians/plumbers.",
                    problemStatementHindi = "स्टार्टअप को 14 दिनों में एक वर्किंग मोबाइल ऐप चाहिए जिसमें यूजर सर्विस सेलेक्ट कर सके, स्लॉट बुक कर सके और ऑनलाइन पे कर सके।",
                    deliverableChecklist = listOf(
                        "12 Fully Designed & Functional Mobile Screens in FlutterFlow",
                        "Firebase Firestore Database with Services & Bookings Collections",
                        "Razorpay Payment Integration with success confirmation screen",
                        "Admin Panel in Webflow/Glide for booking status management"
                    ),
                    expectedOutcome = "Fully deployed MVP ready for user testing and angel investor pitches.",
                    estimatedProjectValue = "₹45,000 - ₹85,000"
                ),
                portfolio = PortfolioStage(
                    title = "Portfolio: No-Code Product Engineer",
                    portfolioPieceTitle = "QuickFix On-Demand Services MVP",
                    mockupDescription = "3D Phone mockups showing animated booking flow, interactive map, and checkout screens.",
                    clientPitchHindi = "'मैंने स्टार्टअप्स और फाउंडर्स के लिए 10+ नो-कोड ऐप्स बनाए हैं जो 2 हफ्तों में लॉन्च होकर रेवेन्यू जनरेट करते हैं।' ",
                    recommendedTags = listOf("FlutterFlow", "Bubble", "No-Code MVP", "Firebase", "Webflow")
                ),
                earn = EarnStage(
                    title = "Earn: High-Ticket MVP Development",
                    gigTitleExample = "I will build your startup MVP mobile app using FlutterFlow & Bubble",
                    beginnerPriceRange = "₹30,000 - ₹50,000 per MVP app",
                    proPriceRange = "₹80,000 - ₹2,00,000 per full-featured app",
                    topPlatforms = listOf("Upwork", "Twitter/X Tech Founders", "LinkedIn", "Direct Startup Incubator Outreach"),
                    proposalScriptHindi = "नमस्ते [Founder], मैंने आपका MVP आइडिया देखा। इसे कोडिंग में 4 महीने लगाने की बजाय हम FlutterFlow में 2 हफ्तों में लाइव कर सकते हैं। मैंने सिमिलर ऑन-डिमांड ऐप पहले भी बनाया है। क्या हम एक क्विक डेमो कॉल करें?",
                    coldEmailPitch = "Hey [Founder], saw your announcement about [Startup]. You can validate this MVP in 14 days using FlutterFlow instead of waiting 6 months with traditional agencies. I build production-ready No-Code apps. Here's my portfolio of 8 shipped MVPs."
                )
            )
        )
    )

    val earningRoadmaps: List<EarningMilestone> = listOf(
        EarningMilestone(
            levelName = "Milestone 1: ₹10,000 - ₹25,000 / month",
            targetMonthlyIncome = "₹25,000 / Month",
            durationToAchieve = "30 to 45 Days",
            skillsNeeded = listOf("Graphic Design / Canva Thumbnails", "Prompt Engineering Basics", "Basic Upwork & LinkedIn Setup"),
            weeklyHoursRecommended = 10,
            weeklyMilestones = listOf(
                "Week 1: Choose 1 primary high-income skill (Canva or AI Automation)",
                "Week 2: Complete Learn + Practice stages & build 3 portfolio pieces",
                "Week 3: Optimize Upwork & LinkedIn profile using DSA templates",
                "Week 4: Send 10 targeted personalized proposals/DMs daily to land First Paying Client"
            )
        ),
        EarningMilestone(
            levelName = "Milestone 2: ₹50,000 - ₹75,000 / month",
            targetMonthlyIncome = "₹50,000 - ₹75,000 / Month",
            durationToAchieve = "60 to 90 Days",
            skillsNeeded = listOf("Meta Ads or Power BI or Make.com Workflows", "Loom Video Audits", "Client Retainer Contracts"),
            weeklyHoursRecommended = 18,
            weeklyMilestones = listOf(
                "Week 5-6: Transition from one-time projects to ₹15k-₹20k monthly retainers",
                "Week 7: Launch a digital product on Gumroad/Instamojo for passive sales",
                "Week 8: Sign 3 recurring retainer clients and achieve 5-star Upwork JSS"
            )
        ),
        EarningMilestone(
            levelName = "Milestone 3: ₹1,00,000+ / month (Hero Earner)",
            targetMonthlyIncome = "₹1,00,000 - ₹2,50,000 / Month",
            durationToAchieve = "120 to 180 Days",
            skillsNeeded = listOf("No-Code MVPs (FlutterFlow)", "High-Ticket B2B Consulting", "Personal Branding Authority", "Digital Product Store"),
            weeklyHoursRecommended = 25,
            weeklyMilestones = listOf(
                "Month 4: Package full-service high-ticket solutions (₹50k-₹1L per deal)",
                "Month 5: Build an inbound engine on LinkedIn generating 5+ warm inquiries weekly",
                "Month 6: Scale digital products + agency delegation for true financial freedom"
            )
        )
    )

    val gigsAndOpportunities: List<GigOpportunity> = listOf(
        GigOpportunity(
            id = "gig-1",
            title = "AI Automation Specialist for E-Commerce Brand",
            clientCompany = "Zest Lifestyle Brands (Bengaluru)",
            location = "Remote",
            stipendOrBudget = "₹35,000 - ₹45,000 / project",
            requiredTrack = "AI & Automation",
            deadline = "Apply in 2 days",
            description = "Need a skilled automation pro to connect Shopify store with WhatsApp Business API and customer support ticketing system using Make.com and OpenAI.",
            tags = listOf("Make.com", "OpenAI", "Shopify", "High Priority")
        ),
        GigOpportunity(
            id = "gig-2",
            title = "YouTube Thumbnail Designer (15 Thumbnails / Month)",
            clientCompany = "FinTech Simplified (450k Subs)",
            location = "Remote / India",
            stipendOrBudget = "₹18,000 / month retainer",
            requiredTrack = "Graphic Design & Canva",
            deadline = "Immediate Hiring",
            description = "Looking for an expert thumbnail creator who understands visual hierarchy, high CTR design, and Canva/Photoshop. Must have previous sample links.",
            tags = listOf("YouTube", "Canva Pro", "Monthly Retainer")
        ),
        GigOpportunity(
            id = "gig-3",
            title = "Performance Marketing Intern / Junior Media Buyer",
            clientCompany = "Aura Organic Skincare (Mumbai)",
            location = "Remote",
            stipendOrBudget = "₹22,000 / month",
            requiredTrack = "Digital Marketing",
            deadline = "Apply this week",
            description = "Manage daily Meta Ads budget of ₹20k, monitor ROAS, write punchy Hinglish ad copy and optimize creative variations.",
            tags = listOf("Meta Ads", "ROAS", "Paid Internship")
        ),
        GigOpportunity(
            id = "gig-4",
            title = "Power BI Dashboard Developer for Supply Chain Firm",
            clientCompany = "TransLogix Logistics (Delhi NCR)",
            location = "Remote / Hybrid",
            stipendOrBudget = "₹40,000 fixed price",
            requiredTrack = "Data Analytics",
            deadline = "Apply in 4 days",
            description = "Build an executive real-time fleet tracking and delivery delay dashboard in Power BI from PostgreSQL database. Star schema required.",
            tags = listOf("Power BI", "SQL", "Fixed Price")
        ),
        GigOpportunity(
            id = "gig-5",
            title = "FlutterFlow Mobile App MVP for Fitness Startup",
            clientCompany = "FitPulse India",
            location = "Remote",
            stipendOrBudget = "₹65,000 one-time",
            requiredTrack = "No-Code App Development",
            deadline = "Closes in 3 days",
            description = "Build a cross-platform FlutterFlow app with workout tracking, audio player, Razorpay subscription, and Firebase auth in 3 weeks.",
            tags = listOf("FlutterFlow", "Firebase", "Razorpay")
        )
    )

    val digitalProducts: List<DigitalProductItem> = listOf(
        DigitalProductItem(
            id = "prod-1",
            name = "Ultimate Freelancer OS (Notion Template)",
            category = "Notion Templates",
            priceInInr = 499,
            salesCount = 1420,
            rating = 4.9f,
            authorName = "DSA Community Earner",
            description = "All-in-one Notion workspace with client CRM, automated invoice generator, project timelines, and goal tracker.",
            previewBadge = "Best Seller"
        ),
        DigitalProductItem(
            id = "prod-2",
            name = "150+ High CTR YouTube Thumbnail Templates",
            category = "Graphic Assets",
            priceInInr = 799,
            salesCount = 890,
            rating = 4.8f,
            authorName = "Neha S. (DSA Alumni)",
            description = "Editable Canva Pro thumbnail templates tested across Finance, Gaming, Tech, and Education niches with 8%+ CTR.",
            previewBadge = "Trending"
        ),
        DigitalProductItem(
            id = "prod-3",
            name = "AI Automation Mega Prompt & Scenario Vault",
            category = "AI Toolkits",
            priceInInr = 649,
            salesCount = 1150,
            rating = 5.0f,
            authorName = "Vikram A. (Instructor)",
            description = "30+ ready-to-import Make.com blueprints, OpenAI system prompts, and client outreach cold email sequences.",
            previewBadge = "Staff Pick"
        ),
        DigitalProductItem(
            id = "prod-4",
            name = "Meta Ads Copywriting & ROAS Scaling Kit",
            category = "Marketing Toolkits",
            priceInInr = 599,
            salesCount = 670,
            rating = 4.7f,
            authorName = "Rohan M.",
            description = "25 high-converting Hinglish ad copy frameworks, video script templates, and CBO scaling spreadsheet.",
            previewBadge = "New"
        )
    )

    fun getTrackById(id: String): LearningTrack? {
        return tracks.find { it.id == id } ?: tracks.firstOrNull()
    }
}
