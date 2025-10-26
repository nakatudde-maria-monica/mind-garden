package com.example.mind_garden.presentation.support

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun EmotionalSupportScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // Header
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
                .background(
                    Brush.horizontalGradient(
                        colors = listOf(
                            Color(0xFF9C27B0),
                            Color(0xFFE91E63)
                        )
                    )
                )
                .padding(24.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Column {
                Icon(
                    imageVector = Icons.Default.FavoriteBorder,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(40.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Emotional Support",
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "You're not alone in this journey",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White.copy(alpha = 0.9f)
                )
            }
        }

        // Support Categories
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Quick Help Section
            item {
                QuickHelpCard()
            }

            // Support Categories
            items(supportCategories) { category ->
                SupportCategoryCard(category)
            }

            // Crisis Support Section
            item {
                CrisisSupportCard()
            }
        }
    }
}

@Composable
fun QuickHelpCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        )
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimaryContainer,
                    modifier = Modifier.size(28.dp)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = "Feeling Overwhelmed?",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "It's okay to struggle. Academic challenges are part of growth. Remember:",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
            Spacer(modifier = Modifier.height(8.dp))
            BulletPoint("Failure doesn't define you - it's a stepping stone")
            BulletPoint("Many successful students have failed courses before")
            BulletPoint("You have resources and support available")
            BulletPoint("Taking care of your mental health is priority")
        }
    }
}

@Composable
fun BulletPoint(text: String) {
    Row(
        modifier = Modifier.padding(vertical = 4.dp)
    ) {
        Text(
            text = "• ",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
    }
}

@Composable
fun SupportCategoryCard(category: SupportCategory) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(bottom = 12.dp)
            ) {
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = category.color.copy(alpha = 0.2f),
                    modifier = Modifier.size(48.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(
                            imageVector = category.icon,
                            contentDescription = null,
                            tint = category.color,
                            modifier = Modifier.size(28.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = category.title,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
            }

            Text(
                text = category.description,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            // Tips
            category.tips.forEach { tip ->
                Row(
                    modifier = Modifier.padding(vertical = 4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = null,
                        tint = category.color,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = tip,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}

@Composable
fun CrisisSupportCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFE53935).copy(alpha = 0.1f)
        )
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.Warning,
                    contentDescription = null,
                    tint = Color(0xFFE53935),
                    modifier = Modifier.size(28.dp)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = "Need Immediate Help?",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFE53935)
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "If you're experiencing a mental health crisis or having thoughts of self-harm:",
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(12.dp))
            CrisisContactRow("Campus Counseling Center", "(555) 123-4567")
            CrisisContactRow("National Crisis Hotline", "988")
            CrisisContactRow("Crisis Text Line", "Text HOME to 741741")
        }
    }
}

@Composable
fun CrisisContactRow(label: String, contact: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.SemiBold
        )
        Text(
            text = contact,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

data class SupportCategory(
    val title: String,
    val description: String,
    val icon: ImageVector,
    val color: Color,
    val tips: List<String>
)

val supportCategories = listOf(
    SupportCategory(
        title = "Dealing with Failure",
        description = "Failed a course? You're not alone, and this isn't the end.",
        icon = Icons.Default.FavoriteBorder,
        color = Color(0xFF2196F3),
        tips = listOf(
            "Reflect on what went wrong without self-blame",
            "Talk to your professor about improvement strategies",
            "Create a concrete plan for retaking the course",
            "Join study groups for peer support",
            "Remember: many successful people failed courses before"
        )
    ),
    SupportCategory(
        title = "Stress Management",
        description = "Practical techniques to manage academic pressure.",
        icon = Icons.Default.Star,
        color = Color(0xFF4CAF50),
        tips = listOf(
            "Practice the 5-5-5 breathing technique",
            "Take 10-minute study breaks every hour",
            "Exercise for 20 minutes daily",
            "Maintain a regular sleep schedule",
            "Use the Pomodoro technique for studying"
        )
    ),
    SupportCategory(
        title = "Building Confidence",
        description = "Regain your academic self-esteem.",
        icon = Icons.Default.KeyboardArrowUp,
        color = Color(0xFFFF9800),
        tips = listOf(
            "Celebrate small wins and progress",
            "Keep a success journal",
            "Challenge negative self-talk",
            "Set realistic, achievable goals",
            "Focus on growth, not perfection"
        )
    ),
    SupportCategory(
        title = "Motivation Boosters",
        description = "Stay motivated throughout the semester.",
        icon = Icons.Default.Favorite,
        color = Color(0xFFE91E63),
        tips = listOf(
            "Connect with your 'why' - remember your goals",
            "Create a vision board for your academic future",
            "Find an accountability partner",
            "Reward yourself for meeting milestones",
            "Read success stories of students who overcame failure"
        )
    )
)
