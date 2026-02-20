# Android Simpsons App 🍩

[Versión en Español](README.es.md)

This is a modern Android project developed with **Jetpack Compose** to explore and search for characters from "The Simpsons" series by consuming the [thesimpsonsapi.com](https://thesimpsonsapi.com/) API.

## 🚀 Technologies Used

- **Kotlin**: Primary programming language.
- **Jetpack Compose**: Modern framework for building native user interfaces.
- **Material 3**: Latest evolution of Material Design for UI components.
- **MVVM + Clean Architecture**: Robust and scalable architecture divided into layers (Presentation, Domain, Data, Core).
- **Manual Dependency Injection**: Implementation of DI without external frameworks (Hilt/Koin) to keep the project lightweight.
- **Retrofit**: Consuming the official Simpsons REST API.
- **Coil**: Efficient image loading from remote URLs.
- **Navigation Compose**: Route management and screen transitions.
- **[Stitch](https://stitch.withgoogle.com/)**: Tool used to define and translate the visual design layer into code.

## 📱 Screens and Features

### 1. Main Screen (Paginated List)
- Displays a dynamic list of characters loaded from the API.
- Includes a real pagination system to navigate through the hundreds of available characters.
- **SimpsonHeader**: A custom header with the series' iconic visual style.

### 2. Intelligent Hybrid Search
- **Search by ID**: If you enter a number, the app directly queries the API to fetch that specific character.
- **Search by Name**: If you enter text, the app filters in real-time from the locally loaded list.
- Rounded "pill" visual style integrated into the header.

### 3. Detail Screen
- Tapping on any character navigates to a detailed view.
- Shows high-resolution image, occupation, age, and gender.
- **Phrases List**: Includes a section with all the character's famous quotes in individual cards.

## 🛠️ Project Architecture

The project strictly follows the `ARCHITECTURE.md` file:
- **Data Layer**: Repositories and data sources (Retrofit). Mapping DTOs to domain models.
- **Domain Layer**: Pure business models and Use Cases with single responsibility.
- **Presentation Layer**: ViewModels managing UI state immutably via `StateFlow` and Compose screens.
- **Core Layer**: Network utilities, controlled error handling (`AppError`), and connectivity validation.

---
Developed as a high visual and technical fidelity MVP application.
