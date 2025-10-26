package com.example.mind_garden

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items as gridItems
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.mind_garden.ui.theme.MindgardenTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MindgardenTheme {
                MindGardenApp()
            }
        }
    }
}

@Composable
fun MindGardenApp() {
    // Track authentication state
    // For now, using simple state management
    // TODO: Replace with proper auth state from ViewModel/DataStore
    var isFirstTime by remember { mutableStateOf(true) }
    var isAuthenticated by remember { mutableStateOf(false) }
    var showLogin by remember { mutableStateOf(false) }

    when {
        isFirstTime -> {
            com.example.mind_garden.presentation.auth.OnboardingScreen(
                onComplete = {
                    isFirstTime = false
                    showLogin = false // Show sign up after onboarding
                }
            )
        }
        !isAuthenticated && !showLogin -> {
            com.example.mind_garden.presentation.auth.SignUpScreen(
                onSignUpSuccess = {
                    isAuthenticated = true
                },
                onNavigateToLogin = {
                    showLogin = true
                }
            )
        }
        !isAuthenticated && showLogin -> {
            com.example.mind_garden.presentation.auth.LoginScreen(
                onLoginSuccess = {
                    isAuthenticated = true
                },
                onNavigateToSignUp = {
                    showLogin = false
                }
            )
        }
        else -> {
            MindGardenDashboard()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MindGardenDashboard() {
    var showAIChat by remember { mutableStateOf(false) }
    var deepResearch by remember { mutableStateOf(false) }
    var extendedThinking by remember { mutableStateOf(false) }
    var queryText by remember { mutableStateOf("") }
    var currentScreen by remember { mutableStateOf("home") }
    var selectedCourse by remember { mutableStateOf<Course?>(null) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            if (!showAIChat && currentScreen == "home") {
                FloatingActionButton(
                    onClick = { showAIChat = true },
                    containerColor = MaterialTheme.colorScheme.primary,
                    shape = CircleShape
                ) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "AI Assistant",
                        modifier = Modifier.size(28.dp)
                    )
                }
            }
        },
        bottomBar = {
            if (!showAIChat) {
                NavigationBar {
                    NavigationBarItem(
                        icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                        label = { Text("Home") },
                        selected = currentScreen == "home",
                        onClick = {
                            currentScreen = "home"
                            selectedCourse = null
                        }
                    )
                    NavigationBarItem(
                        icon = { Icon(Icons.Default.Search, contentDescription = "Search") },
                        label = { Text("Courses") },
                        selected = currentScreen == "courses",
                        onClick = {
                            currentScreen = "courses"
                            selectedCourse = null
                        }
                    )
                    NavigationBarItem(
                        icon = { Icon(Icons.Default.FavoriteBorder, contentDescription = "Support") },
                        label = { Text("Support") },
                        selected = currentScreen == "support",
                        onClick = {
                            currentScreen = "support"
                            selectedCourse = null
                        }
                    )
                    NavigationBarItem(
                        icon = { Icon(Icons.Default.DateRange, contentDescription = "Progress") },
                        label = { Text("Progress") },
                        selected = currentScreen == "progress",
                        onClick = {
                            currentScreen = "progress"
                            selectedCourse = null
                        }
                    )
                }
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Content based on current screen
            when (currentScreen) {
                "home" -> {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                    ) {
                        // Header Section
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(180.dp)
                                .background(
                                    Brush.horizontalGradient(
                                        colors = listOf(
                                            Color(0xFF6B4CE6),
                                            Color(0xFF4E9BF5)
                                        )
                                    )
                                )
                                .padding(24.dp),
                            contentAlignment = Alignment.CenterStart
                        ) {
                            Column {
                                Text(
                                    text = "Mind Garden",
                                    style = MaterialTheme.typography.headlineLarge,
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = "Your academic knowledge hub",
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = Color.White.copy(alpha = 0.9f)
                                )
                            }
                        }

                        // Quick Questions Section
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        ) {
                            Text(
                                text = "Quick Questions",
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(bottom = 16.dp)
                            )

                            LazyVerticalGrid(
                                columns = GridCells.Fixed(2),
                                horizontalArrangement = Arrangement.spacedBy(12.dp),
                                verticalArrangement = Arrangement.spacedBy(12.dp),
                                modifier = Modifier.height(600.dp)
                            ) {
                                gridItems(quickQuestions) { question ->
                                    QuestionCard(
                                        question = question,
                                        onClick = {
                                            queryText = question.text
                                            showAIChat = true
                                        }
                                    )
                                }
                            }
                        }
                    }
                }
                "courses" -> {
                    if (selectedCourse != null) {
                        CourseDetailScreen(
                            course = selectedCourse!!,
                            onBack = { selectedCourse = null }
                        )
                    } else {
                        CourseSearchScreen(
                            onCourseClick = { course ->
                                selectedCourse = course
                            }
                        )
                    }
                }
                "resources" -> {
                    ResourcesScreen()
                }
                "support" -> {
                    com.example.mind_garden.presentation.support.EmotionalSupportScreen()
                }
                "progress" -> {
                    com.example.mind_garden.presentation.progress.ProgressTrackingScreen()
                }
            }

            // AI Chat Overlay
            AnimatedVisibility(
                visible = showAIChat,
                enter = slideInVertically(
                    initialOffsetY = { it },
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessLow
                    )
                ) + fadeIn(),
                exit = slideOutVertically(
                    targetOffsetY = { it },
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioNoBouncy,
                        stiffness = Spring.StiffnessMedium
                    )
                ) + fadeOut()
            ) {
                AIChatPanel(
                    queryText = queryText,
                    onQueryChange = { queryText = it },
                    deepResearch = deepResearch,
                    onDeepResearchChange = { deepResearch = it },
                    extendedThinking = extendedThinking,
                    onExtendedThinkingChange = { extendedThinking = it },
                    onClose = { showAIChat = false },
                    onSubmit = {
                        // Handle AI query submission
                        // TODO: Integrate with AI service
                    }
                )
            }
        }
    }
}

@Composable
fun QuestionCard(question: QuickQuestion, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(140.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                imageVector = question.icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(32.dp)
            )
            Text(
                text = question.text,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AIChatPanel(
    queryText: String,
    onQueryChange: (String) -> Unit,
    deepResearch: Boolean,
    onDeepResearchChange: (Boolean) -> Unit,
    extendedThinking: Boolean,
    onExtendedThinkingChange: (Boolean) -> Unit,
    onClose: () -> Unit,
    onSubmit: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Header with close button
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "AI Research Assistant",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )
                IconButton(onClick = onClose) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close"
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Toggle Options
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                )
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = "Research Options",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(bottom = 12.dp)
                    )

                    // Deep Research Toggle
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.weight(1f)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(24.dp)
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            Column {
                                Text(
                                    text = "Deep Research",
                                    style = MaterialTheme.typography.bodyLarge,
                                    fontWeight = FontWeight.Medium
                                )
                                Text(
                                    text = "Comprehensive analysis with sources",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                        Switch(
                            checked = deepResearch,
                            onCheckedChange = onDeepResearchChange
                        )
                    }

                    HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

                    // Extended Thinking Toggle
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.weight(1f)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Info,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(24.dp)
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            Column {
                                Text(
                                    text = "Extended Thinking",
                                    style = MaterialTheme.typography.bodyLarge,
                                    fontWeight = FontWeight.Medium
                                )
                                Text(
                                    text = "More detailed reasoning process",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                        Switch(
                            checked = extendedThinking,
                            onCheckedChange = onExtendedThinkingChange
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Text Input Area
            OutlinedTextField(
                value = queryText,
                onValueChange = onQueryChange,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                placeholder = { Text("Enter your question or topic for research...") },
                label = { Text("Your Query") },
                shape = RoundedCornerShape(12.dp),
                maxLines = 10,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                    unfocusedBorderColor = MaterialTheme.colorScheme.outline
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Submit Button
            Button(
                onClick = onSubmit,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(12.dp),
                enabled = queryText.isNotBlank()
            ) {
                Icon(
                    imageVector = Icons.Default.Send,
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Start Research",
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

data class QuickQuestion(
    val text: String,
    val icon: ImageVector
)

val quickQuestions = listOf(
    QuickQuestion("How do I choose the right courses?", Icons.Default.List),
    QuickQuestion("What study techniques work best?", Icons.Default.Info),
    QuickQuestion("I failed a course, what now?", Icons.Default.Warning),
    QuickQuestion("How to prepare for exams?", Icons.Default.Check),
    QuickQuestion("Where can I find resources?", Icons.Default.Settings),
    QuickQuestion("How to manage stress?", Icons.Default.FavoriteBorder)
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CourseDetailScreen(course: Course, onBack: () -> Unit) {
    Column(


        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        // Header with back button
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Brush.horizontalGradient(
                        colors = listOf(
                            Color(0xFF6B4CE6),
                            Color(0xFF4E9BF5)
                        )
                    )
                )
                .padding(16.dp)
        ) {
            IconButton(
                onClick = onBack,
                modifier = Modifier.align(Alignment.TopStart)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.White
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 48.dp, bottom = 16.dp)
            ) {
                Text(
                    text = course.code,
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.White.copy(alpha = 0.9f),
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = course.name,
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Course Info Cards
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                )
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Course Information",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 12.dp)
                    )

                    CourseInfoRow(label = "Credits", value = "${course.credits}")
                    CourseInfoRow(label = "Department", value = course.department)
                    CourseInfoRow(label = "Level", value = "Level ${course.level}")
                    CourseInfoRow(label = "Difficulty", value = course.difficulty)

                    if (course.prerequisites.isNotEmpty()) {
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Prerequisites",
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Text(
                            text = course.prerequisites.joinToString(", "),
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(top = 4.dp)
                        )
                    }
                }
            }

            // Description
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Description",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Text(
                        text = course.description,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            // Study Tips for this course
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Info,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Study Tips",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = when (course.difficulty) {
                            "Easy" -> "This is a great foundational course. Focus on understanding core concepts and practice regularly."
                            "Moderate" -> "Stay consistent with your study schedule. Form study groups and don't hesitate to ask questions during office hours."
                            else -> "This is a challenging course. Start early, break topics into smaller chunks, and seek help from TAs and professors regularly."
                        },
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
            }

            // Action Buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Button(
                    onClick = { /* TODO: Add to favorites */ },
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Save Course")
                }
                OutlinedButton(
                    onClick = { /* TODO: View resources */ },
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Settings,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Resources")
                }
            }
        }
    }
}

@Composable
fun CourseInfoRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Composable
fun ResourcesScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Study Resources",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(resourceCategories) { category ->
                ResourceCategoryCard(category)
            }
        }
    }
}

data class ResourceCategory(
    val title: String,
    val description: String,
    val icon: ImageVector,
    val itemCount: Int
)

val resourceCategories = listOf(
    ResourceCategory(
        title = "Past Exam Papers",
        description = "Access previous years' exam papers and solutions",
        icon = Icons.Default.Check,
        itemCount = 124
    ),
    ResourceCategory(
        title = "Study Guides",
        description = "Comprehensive guides for all major courses",
        icon = Icons.Default.Info,
        itemCount = 89
    ),
    ResourceCategory(
        title = "Video Tutorials",
        description = "Watch recorded lectures and explanatory videos",
        icon = Icons.Default.PlayArrow,
        itemCount = 256
    ),
    ResourceCategory(
        title = "Practice Problems",
        description = "Sharpen your skills with practice exercises",
        icon = Icons.Default.Edit,
        itemCount = 342
    ),
    ResourceCategory(
        title = "Study Tips & Strategies",
        description = "Learn effective study techniques and time management",
        icon = Icons.Default.Info,
        itemCount = 45
    ),
    ResourceCategory(
        title = "Academic Support",
        description = "Find tutoring services and study groups",
        icon = Icons.Default.Person,
        itemCount = 18
    )
)

@Composable
fun ResourceCategoryCard(category: ResourceCategory) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { /* TODO: Navigate to resources */ },
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                shape = RoundedCornerShape(12.dp),
                color = MaterialTheme.colorScheme.primaryContainer,
                modifier = Modifier.size(56.dp)
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Icon(
                        imageVector = category.icon,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onPrimaryContainer,
                        modifier = Modifier.size(28.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = category.title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = category.description,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(top = 4.dp)
                )
                Text(
                    text = "${category.itemCount} items",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }

            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = "Go",
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}