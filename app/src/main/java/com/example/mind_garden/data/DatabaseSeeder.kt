package com.example.mind_garden.data

import com.example.mind_garden.data.local.MindGardenDatabase
import com.example.mind_garden.data.local.entity.CourseEntity
import com.example.mind_garden.data.local.entity.MotivationalContentEntity
import com.example.mind_garden.data.local.entity.ResourceEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DatabaseSeeder @Inject constructor(
    private val database: MindGardenDatabase
) {
    suspend fun seedDatabase() {
        seedMotivationalContent()
        seedSampleCourses()
        seedResources()
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
