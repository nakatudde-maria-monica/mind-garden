package com.example.mind_garden.data

import com.example.mind_garden.data.local.MindGardenDatabase
import com.example.mind_garden.data.local.entity.ChatMessageEntity
import com.example.mind_garden.data.local.entity.CourseEntity
import com.example.mind_garden.data.local.entity.MotivationalContentEntity
import com.example.mind_garden.data.local.entity.ResourceEntity
import com.example.mind_garden.data.local.entity.SearchHistoryEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DatabaseSeeder @Inject constructor(
    private val database: MindGardenDatabase
) {
    suspend fun seedDatabase() {
        seedMockChatHistory()
        seedMockSearchHistory()
        seedMotivationalContent()
        seedSampleCourses()
        seedResources()
    }

    private suspend fun seedMockSearchHistory() {
        val searchDao = database.searchHistoryDao()

        val now = System.currentTimeMillis()
        val twoHoursAgo = now - (2 * 60 * 60 * 1000)
        val oneDayAgo = now - (24 * 60 * 60 * 1000)
        val twoDaysAgo = now - (2L * 24 * 60 * 60 * 1000)
        val threeDaysAgo = now - (3L * 24 * 60 * 60 * 1000)
        val oneWeekAgo = now - (7L * 24 * 60 * 60 * 1000)

        val mockSearches = listOf(
            SearchHistoryEntity(
                userId = "default_user",
                searchQuery = "data structures",
                searchType = "course",
                timestamp = twoHoursAgo,
                resultCount = 5
            ),
            SearchHistoryEntity(
                userId = "default_user",
                searchQuery = "calculus",
                searchType = "course",
                timestamp = oneDayAgo,
                resultCount = 3
            ),
            SearchHistoryEntity(
                userId = "default_user",
                searchQuery = "algorithms",
                searchType = "course",
                timestamp = oneDayAgo,
                resultCount = 7
            ),
            SearchHistoryEntity(
                userId = "default_user",
                searchQuery = "time management",
                searchType = "resource",
                timestamp = twoDaysAgo,
                resultCount = 12
            ),
            SearchHistoryEntity(
                userId = "default_user",
                searchQuery = "computer science",
                searchType = "course",
                timestamp = twoDaysAgo,
                resultCount = 15
            ),
            SearchHistoryEntity(
                userId = "default_user",
                searchQuery = "study tips",
                searchType = "resource",
                timestamp = threeDaysAgo,
                resultCount = 8
            ),
            SearchHistoryEntity(
                userId = "default_user",
                searchQuery = "programming",
                searchType = "course",
                timestamp = threeDaysAgo,
                resultCount = 10
            ),
            SearchHistoryEntity(
                userId = "default_user",
                searchQuery = "exam preparation",
                searchType = "resource",
                timestamp = oneWeekAgo,
                resultCount = 6
            ),
            SearchHistoryEntity(
                userId = "default_user",
                searchQuery = "mathematics",
                searchType = "course",
                timestamp = oneWeekAgo,
                resultCount = 9
            ),
            SearchHistoryEntity(
                userId = "default_user",
                searchQuery = "stress management",
                searchType = "resource",
                timestamp = oneWeekAgo - (2 * 24 * 60 * 60 * 1000),
                resultCount = 4
            )
        )

        mockSearches.forEach { searchDao.insertSearch(it) }
    }

    private suspend fun seedMockChatHistory() {
        val chatDao = database.chatMessageDao()

        val now = System.currentTimeMillis()
        val oneHourAgo = now - (60 * 60 * 1000)
        val twoDaysAgo = now - (2L * 24 * 60 * 60 * 1000)
        val threeDaysAgo = now - (3L * 24 * 60 * 60 * 1000)
        val oneWeekAgo = now - (7L * 24 * 60 * 60 * 1000)

        val mockChats = listOf(
            // Recent AI conversations
            ChatMessageEntity(
                userId = "default_user",
                message = "How do I prepare for my Data Structures exam?",
                response = "Great question! Here are some effective strategies for preparing for your Data Structures exam:\n\n1. **Master the Basics**: Make sure you understand fundamental concepts like arrays, linked lists, stacks, queues, trees, and graphs.\n\n2. **Practice Implementation**: Don't just memorize - actually code these data structures from scratch.\n\n3. **Understand Time Complexity**: Know the Big O notation for different operations.\n\n4. **Solve Problems**: Practice on LeetCode or HackerRank.\n\n5. **Review Past Exams**: Look at previous papers to understand the format.\n\nWould you like specific practice problems?",
                isUserMessage = false,
                timestamp = oneHourAgo,
                courseId = "cs201"
            ),
            ChatMessageEntity(
                userId = "default_user",
                message = "What are the best study techniques for Calculus?",
                response = "Calculus requires both conceptual understanding and practice:\n\n1. **Understand Concepts First**: Don't just memorize formulas - understand WHY they work.\n\n2. **Practice Daily**: Spend at least 30 minutes solving problems.\n\n3. **Use Visual Aids**: Watch Khan Academy or 3Blue1Brown videos.\n\n4. **Form Study Groups**: Teaching peers helps solidify understanding.\n\n5. **Office Hours**: Visit professors with specific questions.\n\n6. **Past Papers**: Practice with previous exam questions.\n\nFocus on understanding rather than memorization!",
                isUserMessage = false,
                timestamp = twoDaysAgo,
                courseId = "math101"
            ),
            ChatMessageEntity(
                userId = "default_user",
                message = "I'm feeling overwhelmed with my course load. Any tips?",
                response = "I understand - feeling overwhelmed is very common. Here are strategies:\n\n**Time Management:**\n- Use Pomodoro Technique (25-min work + 5-min breaks)\n- Prioritize with Eisenhower Matrix\n- Create a weekly schedule\n\n**Self-Care:**\n- Get 7-8 hours of sleep\n- Take regular breaks\n- Practice deep breathing\n\n**Academic Support:**\n- Break tasks into smaller chunks\n- Use tutoring and counseling\n- Communicate with professors\n\nRemember: It's okay to ask for help. Progress over perfection!\n\nWould you like specific help with any course?",
                isUserMessage = false,
                timestamp = threeDaysAgo
            ),
            ChatMessageEntity(
                userId = "default_user",
                message = "What programming language should I focus on?",
                response = "The best language depends on your goals:\n\n**For Beginners:**\n- **Python**: Great for fundamentals, data science, AI/ML\n- **JavaScript**: Essential for web development\n\n**For Careers:**\n- Web: JavaScript, TypeScript\n- Mobile: Kotlin (Android), Swift (iOS)\n- Data Science: Python, R\n- Game Dev: C++, C#\n- Systems: C, Rust\n\n**My Recommendation:** Start with **Python** - it has clean syntax, huge community, and wide applications.\n\nOnce you master one language, others become easier!\n\nWhat are you interested in building?",
                isUserMessage = false,
                timestamp = oneWeekAgo
            ),
            ChatMessageEntity(
                userId = "default_user",
                message = "How can I improve my time management?",
                response = "Time management is crucial! Here's a comprehensive approach:\n\n**Planning:**\n- Use a planner or digital calendar\n- Plan your week every Sunday\n- Time-block for deep work\n\n**Prioritization:**\n- ABCDE method: A=Must do, B=Should do, C=Nice to do\n- Tackle hardest task first\n\n**Execution:**\n- Set specific session goals\n- Remove distractions\n- 2-minute rule: if it takes <2 min, do it now\n\n**Review:**\n- Weekly review of progress\n- Adjust based on actual time\n\nStart small - master one technique at a time!",
                isUserMessage = false,
                timestamp = oneWeekAgo - (2 * 24 * 60 * 60 * 1000)
            ),
            ChatMessageEntity(
                userId = "default_user",
                message = "How do I choose the right courses for next semester?",
                response = "Great question! Here's a strategic approach:\n\n**Consider These Factors:**\n1. **Prerequisites**: Check what courses unlock future classes\n2. **Workload Balance**: Mix difficult courses with easier ones\n3. **Interest**: Take courses you're genuinely excited about\n4. **Career Goals**: Align with your intended career path\n5. **Professor Reviews**: Check RateMyProfessors\n\n**Practical Steps:**\n- Meet with your academic advisor\n- Read course syllabi from previous semesters\n- Talk to students who've taken the courses\n- Consider your other commitments (work, activities)\n\n**Pro Tip**: Don't overload! Quality > quantity. Better to excel in 4 courses than struggle in 6.\n\nNeed help with specific course combinations?",
                isUserMessage = false,
                timestamp = oneWeekAgo - (5 * 24 * 60 * 60 * 1000)
            )
        )

        mockChats.forEach { chatDao.insertMessage(it) }
    }

    private suspend fun seedMotivationalContent() {
        val contentDao = database.motivationalContentDao()

        val motivationalContents = listOf(
            // Stress Management
            MotivationalContentEntity(
                title = "The Power of Deep Breathing",
                content = "When you feel overwhelmed, try the 4-7-8 breathing technique: Breathe in for 4 seconds, hold for 7 seconds, exhale for 8 seconds. This activates your parasympathetic nervous system, reducing stress and anxiety. Practice this before exams or during study breaks.",
                category = "stress_management",
                author = "Dr. Sarah Johnson, Clinical Psychologist"
            ),
            MotivationalContentEntity(
                title = "Break It Down",
                content = "Large tasks can feel overwhelming. Use the 'chunking' method: Break your study material into 25-minute focused sessions (Pomodoro Technique) with 5-minute breaks. After 4 sessions, take a longer 15-30 minute break. This prevents burnout and improves retention.",
                category = "stress_management",
                author = "Academic Success Center"
            ),
            MotivationalContentEntity(
                title = "Move Your Body, Calm Your Mind",
                content = "Exercise is one of the most effective stress relievers. Even a 10-minute walk can reduce cortisol levels and improve mood. Schedule regular movement breaks during study sessions - your brain will thank you!",
                category = "stress_management",
                author = "Campus Wellness Program"
            ),

            // Exam Tips
            MotivationalContentEntity(
                title = "The Night Before Strategy",
                content = "Cramming doesn't work! The night before an exam: Review your summary notes (don't learn new material), get 7-8 hours of sleep, prepare everything you need, and visualize success. Your brain consolidates memories during sleep - don't skip it!",
                category = "exam_tips",
                author = "Prof. Michael Chen, Educational Psychology"
            ),
            MotivationalContentEntity(
                title = "Active Recall Beats Re-reading",
                content = "Don't just re-read your notes! Use active recall: Close your book and try to explain concepts out loud or write them from memory. This is proven to be 3x more effective than passive re-reading. Practice with past papers!",
                category = "exam_tips",
                author = "Learning Science Institute"
            ),
            MotivationalContentEntity(
                title = "Exam Day Success Formula",
                content = "Arrive early, bring water and snacks, read all instructions twice, start with questions you know, manage your time (allocate minutes per question), and if you blank out, take 3 deep breaths and move to another question. You've got this!",
                category = "exam_tips",
                author = "Student Success Office"
            ),

            // Motivation
            MotivationalContentEntity(
                title = "You Are Not Alone",
                content = "Did you know? 40% of college students fail at least one course. Many successful people - including Steve Jobs, Oprah Winfrey, and Bill Gates - faced academic setbacks. Failure isn't final; it's feedback. Use it to grow stronger.",
                category = "motivation",
                author = "Motivational Speaker Series"
            ),
            MotivationalContentEntity(
                title = "Your Why Matters",
                content = "When motivation dips, reconnect with your 'why': Why did you choose this path? What impact do you want to make? Write it down, put it where you study. On tough days, reading your purpose can reignite your drive.",
                category = "motivation",
                author = "Career Counseling Center"
            ),
            MotivationalContentEntity(
                title = "Celebrate Small Wins",
                content = "Finished a chapter? Celebrate! Completed an assignment? Acknowledge it! Your brain releases dopamine when you recognize progress. Keep a 'wins journal' - even small victories build momentum and confidence.",
                category = "motivation",
                author = "Positive Psychology Department"
            ),
            MotivationalContentEntity(
                title = "Growth Mindset Power",
                content = "Replace 'I can't do this' with 'I can't do this YET.' Your abilities aren't fixed - your brain forms new neural connections every time you learn. Struggling means you're growing. Embrace the challenge!",
                category = "motivation",
                author = "Dr. Carol Dweck's Research Team"
            ),

            // Success Stories
            MotivationalContentEntity(
                title = "From F to PhD: Maria's Story",
                content = "Maria failed Calculus twice in her first year. She felt like giving up on her engineering dream. Instead, she joined study groups, met with tutors, and changed her study approach. Today, she's a PhD candidate in Mechanical Engineering. 'Failure taught me resilience,' she says.",
                category = "success_stories",
                author = "Alumni Success Stories"
            ),
            MotivationalContentEntity(
                title = "Second Chances Work: James's Journey",
                content = "James failed out of college in his sophomore year. After working for two years, he returned with renewed purpose. He graduated with honors and now mentors struggling students. 'Taking time off helped me understand my goals. Don't be afraid to pivot,' he advises.",
                category = "success_stories",
                author = "Returning Student Program"
            ),
            MotivationalContentEntity(
                title = "Overcoming Test Anxiety: Priya's Success",
                content = "Priya would freeze during exams despite knowing the material. She worked with counselors, learned breathing techniques, and practiced with timed mock exams. Her GPA improved from 2.1 to 3.6. 'Anxiety is manageable with the right tools,' she shares.",
                category = "success_stories",
                author = "Counseling Center Testimonials"
            ),
            MotivationalContentEntity(
                title = "From Dropout to Dean's List: Alex's Comeback",
                content = "Alex dropped out after failing multiple courses due to undiagnosed ADHD. After diagnosis and treatment, he returned and made the Dean's List three semesters in a row. 'Understanding myself changed everything. Seek help - it's strength, not weakness.'",
                category = "success_stories",
                author = "Disability Services Success Stories"
            ),

            // Additional Stress Management
            MotivationalContentEntity(
                title = "The Power of 'No'",
                content = "You can't do everything. Learn to say no to non-essential commitments during high-stress periods. Protect your study time and mental health. It's okay to prioritize your well-being.",
                category = "stress_management",
                author = "Time Management Workshop"
            ),
            MotivationalContentEntity(
                title = "Sleep is Non-Negotiable",
                content = "All-nighters hurt more than help. Sleep-deprived brains perform 40% worse on memory tasks. Aim for 7-9 hours. Your grades - and your health - depend on it.",
                category = "stress_management",
                author = "Sleep Research Center"
            ),

            // Additional Exam Tips
            MotivationalContentEntity(
                title = "Study Groups Done Right",
                content = "Effective study groups: Set clear goals, teach each other (teaching = learning), quiz one another, limit to 4-5 people, and meet regularly. Avoid groups that just socialize or complain!",
                category = "exam_tips",
                author = "Academic Skills Center"
            ),
            MotivationalContentEntity(
                title = "Office Hours Are Your Secret Weapon",
                content = "Professors WANT to help! Attend office hours with specific questions. It shows initiative, helps you understand material better, and builds relationships. Students who attend office hours average one letter grade higher.",
                category = "exam_tips",
                author = "Faculty Advisory Committee"
            ),

            // Additional Motivation
            MotivationalContentEntity(
                title = "Your Mental Health Matters",
                content = "Academic success means nothing if you're struggling mentally. If you're experiencing persistent sadness, anxiety, or thoughts of self-harm, please reach out. Campus counseling is confidential and here for you. You matter more than any grade.",
                category = "motivation",
                author = "Counseling & Psychological Services"
            ),
            MotivationalContentEntity(
                title = "Comparison is the Thief of Joy",
                content = "Stop comparing your chapter 1 to someone else's chapter 20. Everyone has a different journey, different challenges, and different strengths. Focus on YOUR progress, YOUR growth, YOUR goals. You are enough.",
                category = "motivation",
                author = "Peer Mentoring Program"
            )
        )

        contentDao.insertAllContent(motivationalContents)
    }

    private suspend fun seedSampleCourses() {
        val courseDao = database.courseDao()

        val sampleCourses = listOf(
            CourseEntity(
                id = "cs101",
                code = "CS101",
                name = "Introduction to Programming",
                credits = 3,
                department = "Computer Science",
                level = "100",
                description = "Learn the fundamentals of programming using Python. Covers variables, control structures, functions, and basic data structures.",
                difficulty = "Easy",
                instructor = "Prof. Smith",
                semester = "Fall 2024"
            ),
            CourseEntity(
                id = "cs201",
                code = "CS201",
                name = "Data Structures and Algorithms",
                credits = 4,
                department = "Computer Science",
                level = "200",
                description = "Study of fundamental data structures (arrays, linked lists, trees, graphs) and algorithms (sorting, searching, graph traversal).",
                difficulty = "Moderate",
                instructor = "Dr. Johnson",
                semester = "Fall 2024"
            ),
            CourseEntity(
                id = "math101",
                code = "MATH101",
                name = "Calculus I",
                credits = 4,
                department = "Mathematics",
                level = "100",
                description = "Introduction to differential and integral calculus. Covers limits, derivatives, and applications.",
                difficulty = "Moderate",
                instructor = "Prof. Williams"
            )
        )

        courseDao.insertCourses(sampleCourses)
    }

    private suspend fun seedResources() {
        val resourceDao = database.resourceDao()

        val sampleResources = listOf(
            ResourceEntity(
                id = "res001",
                title = "How to Study Effectively",
                description = "Comprehensive guide on evidence-based study techniques",
                type = "STUDY_GUIDE",
                category = "Study Tips & Strategies",
                rating = 4.8f
            ),
            ResourceEntity(
                id = "res002",
                title = "Time Management for Students",
                description = "Master your schedule and reduce stress",
                type = "STUDY_GUIDE",
                category = "Study Tips & Strategies",
                rating = 4.6f
            ),
            ResourceEntity(
                id = "res003",
                title = "Dealing with Test Anxiety",
                description = "Practical strategies to manage exam stress",
                type = "STUDY_GUIDE",
                category = "Academic Support",
                rating = 4.7f
            )
        )

        resourceDao.insertResources(sampleResources)
    }

    suspend fun clearAllData() {
        database.clearAllTables()
    }
}
