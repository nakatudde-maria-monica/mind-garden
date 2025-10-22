# Mind Garden

An Android application built with Kotlin and Jetpack Compose, featuring Material Design 3 with dynamic color support.

## Overview

Mind Garden is a modern Android app that leverages the latest Android development technologies including Jetpack Compose for declarative UI and Material 3 for contemporary design patterns.

## Features

- **Jetpack Compose UI**: Fully declarative UI built with Compose
- **Material Design 3**: Modern design system with dynamic color support
- **Dark/Light Theme**: Automatic theme switching based on system preferences
- **Dynamic Colors**: Adapts to system theme on Android 12+ devices
- **Edge-to-Edge Display**: Immersive full-screen experience

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
