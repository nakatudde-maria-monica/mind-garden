# MindGarden 🌱

An AI-powered educational support and course guidance mobile application designed to help students succeed academically and emotionally.

[![Android](https://img.shields.io/badge/Platform-Android-green.svg)](https://www.android.com/)
[![Kotlin](https://img.shields.io/badge/Language-Kotlin-purple.svg)](https://kotlinlang.org/)
[![Jetpack Compose](https://img.shields.io/badge/UI-Jetpack%20Compose-blue.svg)](https://developer.android.com/jetpack/compose)
[![Material Design 3](https://img.shields.io/badge/Design-Material%203-orange.svg)](https://m3.material.io/)

## Overview

MindGarden is a comprehensive mobile application that serves as a student's academic companion, providing:

- 🤖 **AI-Powered Chat Assistant** - 24/7 intelligent support using Google Gemini
- 📚 **Course Information Hub** - Browse, search, and discover courses
- 💚 **Emotional Support System** - Mental health resources and motivational content
- 📊 **Progress Tracking** - Monitor study sessions and achieve goals
- 📖 **Educational Resources** - Study guides, past papers, and tutorials
- ✨ **Study Tips & Techniques** - Evidence-based learning strategies

## Features

### ✅ Implemented

#### 1. AI Chat Assistant
- Natural language understanding for educational queries
- Deep Research mode for comprehensive answers
- Extended Thinking mode for detailed reasoning
- Empathetic responses for emotional support
- Chat history persistence

#### 2. Course Search & Information
- Advanced search with filters (department, level)
- Detailed course information with difficulty ratings
- Prerequisites tracking
- Course-specific study tips

#### 3. Emotional Support System
- **Dealing with Failure** - Strategies for academic setbacks
- **Stress Management** - Breathing techniques, exercise, sleep hygiene
- **Building Confidence** - Growth mindset, celebrating wins
- **Motivation Boosters** - Purpose connection, accountability
- **Crisis Support** - Emergency contacts and resources

#### 4. Progress Tracking
- Study hours statistics and weekly patterns
- Goal setting and completion tracking
- Study session history
- Achievement system

#### 5. Modern Android Architecture
- **MVVM Pattern** - Clean separation of concerns
- **Hilt Dependency Injection** - Modular, testable code
- **Room Database** - Offline-first data persistence
- **Jetpack Compose** - Modern declarative UI
- **Material Design 3** - Beautiful, accessible interface

## Requirements

- **Android Studio**: Latest stable version recommended
- **Min SDK**: 24 (Android 7.0)
- **Target SDK**: 36
- **Java**: 11
- **Kotlin**: Latest version

## Getting Started

### Clone the Repository

```bash
git clone <repository-url>
cd mindgarden
```

### Build the Project

```bash
# Build the project
.\gradlew build

# Clean build artifacts
.\gradlew clean

# Assemble debug APK
.\gradlew assembleDebug
```

### Run the App

```bash
# Install and run debug APK on connected device/emulator
.\gradlew installDebug && adb shell am start -n com.example.mind_garden/.MainActivity
```

## Testing

```bash
# Run all unit tests
.\gradlew test

# Run instrumented tests on connected device/emulator
.\gradlew connectedAndroidTest
```

## Code Quality

```bash
# Lint the code
.\gradlew lint

# Generate lint report
.\gradlew lintDebug
```

## Project Structure

```
app/src/
├── main/
│   ├── java/com/example/mind_garden/
│   │   ├── MainActivity.kt          # Entry point activity
│   │   └── ui/theme/                # Theme configuration
│   │       ├── Theme.kt             # Main theme composable
│   │       ├── Color.kt             # Color definitions
│   │       └── Type.kt              # Typography definitions
│   ├── res/                         # Resources (layouts, strings, etc.)
│   └── AndroidManifest.xml          # App manifest
├── test/                            # Unit tests (JUnit)
└── androidTest/                     # Instrumented tests
```

## Technology Stack

- **Kotlin**: Primary programming language
- **Jetpack Compose**: Modern UI toolkit
- **Material 3**: Design system
- **Compose BOM**: Version 2024.09.00
- **Activity Compose**: Compose integration with Activities
- **Core KTX**: Kotlin extensions for Android APIs
- **Lifecycle Components**: ViewModel and lifecycle-aware components

## Architecture

The app follows a single-activity architecture with Jetpack Compose:

- **UI Layer**: Compose-based declarative UI with Material 3 components
- **Theme System**: Comprehensive theming with support for light/dark modes and dynamic colors
- **Edge-to-Edge**: Full-screen immersive experience

## License

[Add your license information here]

## Contributing

[Add contribution guidelines here]

## Contact

[Add contact information here]
