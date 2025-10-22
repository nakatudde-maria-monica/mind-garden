package com.example.mind_garden

import androidx.compose.foundation.clickable
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

data class Course(
    val code: String,
    val name: String,
    val credits: Int,
    val department: String,
    val level: String,
    val prerequisites: List<String>,
    val description: String,
    val difficulty: String // "Easy", "Moderate", "Hard"
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CourseSearchScreen(onCourseClick: (Course) -> Unit) {
    var searchQuery by remember { mutableStateOf("") }
    var selectedDepartment by remember { mutableStateOf("All") }
    var selectedLevel by remember { mutableStateOf("All") }

    val departments = listOf("All", "Computer Science", "Mathematics", "Engineering", "Business", "Arts")
    val levels = listOf("All", "100", "200", "300", "400")

    val filteredCourses = remember(searchQuery, selectedDepartment, selectedLevel) {
        sampleCourses.filter { course ->
            val matchesSearch = course.name.contains(searchQuery, ignoreCase = true) ||
                    course.code.contains(searchQuery, ignoreCase = true) ||
                    course.description.contains(searchQuery, ignoreCase = true)
            val matchesDepartment = selectedDepartment == "All" || course.department == selectedDepartment
            val matchesLevel = selectedLevel == "All" || course.level == selectedLevel

            matchesSearch && matchesDepartment && matchesLevel
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Course Search",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Search Bar
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            placeholder = { Text("Search by course name, code, or description...") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search"
                )
            },
            trailingIcon = {
                if (searchQuery.isNotEmpty()) {
                    IconButton(onClick = { searchQuery = "" }) {
                        Icon(
                            imageVector = Icons.Default.Clear,
                            contentDescription = "Clear"
                        )
                    }
                }
            },
            shape = RoundedCornerShape(12.dp),
            singleLine = true
        )

        // Filters
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Department Filter
            var departmentExpanded by remember { mutableStateOf(false) }
            ExposedDropdownMenuBox(
                expanded = departmentExpanded,
                onExpandedChange = { departmentExpanded = it },
                modifier = Modifier.weight(1f)
            ) {
                OutlinedTextField(
                    value = selectedDepartment,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Department") },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = departmentExpanded)
                    },
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp)
                )
                ExposedDropdownMenu(
                    expanded = departmentExpanded,
                    onDismissRequest = { departmentExpanded = false }
                ) {
                    departments.forEach { dept ->
                        DropdownMenuItem(
                            text = { Text(dept) },
                            onClick = {
                                selectedDepartment = dept
                                departmentExpanded = false
                            }
                        )
                    }
                }
            }

            // Level Filter
            var levelExpanded by remember { mutableStateOf(false) }
            ExposedDropdownMenuBox(
                expanded = levelExpanded,
                onExpandedChange = { levelExpanded = it },
                modifier = Modifier.weight(1f)
            ) {
                OutlinedTextField(
                    value = if (selectedLevel == "All") "All" else "Level $selectedLevel",
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Level") },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = levelExpanded)
                    },
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp)
                )
                ExposedDropdownMenu(
                    expanded = levelExpanded,
                    onDismissRequest = { levelExpanded = false }
                ) {
                    levels.forEach { level ->
                        DropdownMenuItem(
                            text = { Text(if (level == "All") "All" else "Level $level") },
                            onClick = {
                                selectedLevel = level
                                levelExpanded = false
                            }
                        )
                    }
                }
            }
        }

        // Results count
        Text(
            text = "${filteredCourses.size} courses found",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // Course List
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(filteredCourses) { course ->
                CourseCard(course = course, onClick = { onCourseClick(course) })
            }
        }
    }
}

@Composable
fun CourseCard(course: Course, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = course.code,
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = course.name,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                }

                // Difficulty Badge
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = when (course.difficulty) {
                        "Easy" -> Color(0xFF4CAF50).copy(alpha = 0.2f)
                        "Moderate" -> Color(0xFFFF9800).copy(alpha = 0.2f)
                        else -> Color(0xFFF44336).copy(alpha = 0.2f)
                    }
                ) {
                    Text(
                        text = course.difficulty,
                        style = MaterialTheme.typography.labelSmall,
                        color = when (course.difficulty) {
                            "Easy" -> Color(0xFF2E7D32)
                            "Moderate" -> Color(0xFFE65100)
                            else -> Color(0xFFC62828)
                        },
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = course.description,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 2
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                InfoChip(
                    icon = Icons.Default.Info,
                    text = course.department
                )
                InfoChip(
                    icon = Icons.Default.Star,
                    text = "${course.credits} Credits"
                )
                InfoChip(
                    icon = Icons.Default.KeyboardArrowUp,
                    text = "Level ${course.level}"
                )
            }

            if (course.prerequisites.isNotEmpty()) {
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Warning,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Prerequisites: ${course.prerequisites.joinToString(", ")}",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}

@Composable
fun InfoChip(icon: androidx.compose.ui.graphics.vector.ImageVector, text: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(16.dp)
        )
        Text(
            text = text,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

// Sample data - This will be replaced with actual database/API calls
val sampleCourses = listOf(
    Course(
        code = "CS101",
        name = "Introduction to Programming",
        credits = 3,
        department = "Computer Science",
        level = "100",
        prerequisites = emptyList(),
        description = "Learn the fundamentals of programming using Python. Covers variables, control structures, functions, and basic data structures.",
        difficulty = "Easy"
    ),
    Course(
        code = "CS201",
        name = "Data Structures and Algorithms",
        credits = 4,
        department = "Computer Science",
        level = "200",
        prerequisites = listOf("CS101"),
        description = "Study of fundamental data structures (arrays, linked lists, trees, graphs) and algorithms (sorting, searching, graph traversal).",
        difficulty = "Moderate"
    ),
    Course(
        code = "CS301",
        name = "Database Systems",
        credits = 3,
        department = "Computer Science",
        level = "300",
        prerequisites = listOf("CS201"),
        description = "Design and implementation of database systems. Topics include relational model, SQL, normalization, transactions, and indexing.",
        difficulty = "Moderate"
    ),
    Course(
        code = "CS401",
        name = "Machine Learning",
        credits = 4,
        department = "Computer Science",
        level = "400",
        prerequisites = listOf("CS201", "MATH202"),
        description = "Advanced topics in machine learning including supervised and unsupervised learning, neural networks, and deep learning.",
        difficulty = "Hard"
    ),
    Course(
        code = "MATH101",
        name = "Calculus I",
        credits = 4,
        department = "Mathematics",
        level = "100",
        prerequisites = emptyList(),
        description = "Introduction to differential and integral calculus. Covers limits, derivatives, and applications.",
        difficulty = "Moderate"
    ),
    Course(
        code = "MATH202",
        name = "Linear Algebra",
        credits = 3,
        department = "Mathematics",
        level = "200",
        prerequisites = listOf("MATH101"),
        description = "Study of vector spaces, matrices, linear transformations, eigenvalues, and eigenvectors.",
        difficulty = "Moderate"
    ),
    Course(
        code = "ENG101",
        name = "Engineering Design",
        credits = 3,
        department = "Engineering",
        level = "100",
        prerequisites = emptyList(),
        description = "Introduction to engineering design process. Hands-on projects using CAD software and prototyping.",
        difficulty = "Easy"
    ),
    Course(
        code = "BUS201",
        name = "Principles of Marketing",
        credits = 3,
        department = "Business",
        level = "200",
        prerequisites = emptyList(),
        description = "Fundamentals of marketing including market research, consumer behavior, and marketing strategies.",
        difficulty = "Easy"
    ),
    Course(
        code = "ART101",
        name = "Introduction to Digital Art",
        credits = 3,
        department = "Arts",
        level = "100",
        prerequisites = emptyList(),
        description = "Explore digital art creation using industry-standard software. Learn composition, color theory, and digital techniques.",
        difficulty = "Easy"
    ),
    Course(
        code = "CS305",
        name = "Software Engineering",
        credits = 3,
        department = "Computer Science",
        level = "300",
        prerequisites = listOf("CS201"),
        description = "Software development lifecycle, design patterns, testing strategies, and project management methodologies.",
        difficulty = "Moderate"
    )
)
