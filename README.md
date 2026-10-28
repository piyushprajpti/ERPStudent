# 🎓 ERPStudent - Modern College ERP Mobile Application

> A modern, enterprise-grade, cross-platform mobile ERP client engineered for college and university students. Built using **Kotlin Multiplatform (KMP)** and **Compose Multiplatform (CMP)** with **Clean Architecture** and **MVI (Model-View-Intent)** pattern, targeting both **Android** and **iOS** from a single shared codebase.

---

## 🚀 Live App Download & Demo Credentials

Recruiters and reviewers can test the application directly using the pre-built APK:

![Download APK](https://drive.google.com/file/d/1VWqKqRWRNTzkGUKMCyS6XPksYoXGsnBX/view?usp=sharing)

### 🔑 Demo Login Credentials

| Field | Value |
| :--- | :--- |
| **Institution / Tenant** | Select **`Raffles University`** from the institution dropdown |
| **Student Email** | `user@example.com` |
| **Password** | `pass@123` |

> 💡 *Note: The companion faculty app is also available at [ERPTeacher](https://github.com/piyushprajpti/ERPTeacher).*

---

## 📱 Key Highlights & Capabilities

### 🏢 1. Multi-Tenant Authentication & Session Management
- **Institution Selection:** Multi-tenant architecture allowing students to select their respective college/university tenant before authentication.
- **Secure Authentication:** JWT-based login lifecycle with automated token refresh (`refresh-token`), session persistence via DataStore Preferences, and self-service password recovery / OTP verification.

### 📅 2. Real-Time Academic Schedule & Attendance
- **Interactive Timetable:** Day-by-day and weekly view with interactive scrollable calendar selector, highlighting upcoming lectures, venues, and professors.
- **Attendance Analytics:** Subject-wise breakdown, aggregate percentage calculation, visual circular progress indicators, and automated low-attendance warnings.

### 📊 3. Academic Marks & Digital Grade Cards
- **CGPA & Academic Standing:** Dynamic overview of GPA/CGPA across all semesters.
- **Performance Visualization:** Interactive Performance Radar Charts and detailed subject scorecards.
- **Digital Grade Cards:** Semester-by-semester official marksheet inspection with SGPA/CGPA breakdowns.

### 📝 4. Assignments & Continuous Assessment
- **Assignment Tracker:** Filter coursework by *Pending*, *Submitted*, and *Overdue* states.
- **Submissions Hub:** Digital file attachment, submission history review, grading feedback, and teacher remarks.

### 🎟️ 5. Examinations & Digital Hall Ticket
- **Exam Timetable:** Complete schedule of internal assessments and end-semester examinations with timings, reporting guidelines, and seat allocation.
- **Digital Hall Ticket:** Verified student hall ticket with downloadable / printable digital pass.

### 💳 6. Fee Management & Invoicing
- **Financial Breakdown:** Comprehensive fee ledger including tuition fees, hostel, library, and laboratory charges.
- **Installment Tracking:** Due date reminders, receipt history, and simulated integrated fee payment interface.

### 📚 7. Digital Library Catalog
- **Book Search & Discovery:** Fast catalog search with real-time query filtering by title, author, or subject.
- **Issued Books & Fines:** Track currently borrowed books, return due dates, overdue notices, and automated fine calculation.

### 🏡 8. Hostel & Residential Life
- **Room Allotment & Stay Info:** Hostel block, room number, roommate information, and warden contact directory.
- **Digital Gate Pass:** Paperless campus in/out movement request system with QR-code generation and approval status.
- **Mess Menu & Meal Ratings:** Weekly rotating menu schedule, meal rating and feedback system, and meal skipping notices.
- **Hostel Complaints:** Maintenance request filing (electrical, plumbing, Wi-Fi) with real-time status tracking.

### 🚌 9. Campus Transport & Live GPS Bus Tracking
- **Route & Stop Management:** Assigned bus route, stop schedule, and driver contact info.
- **Live GPS Map Tracking:** Platform-native interactive maps using **Google Maps Compose** (Android) and platform-specific view bridging (`expect`/`actual`) for real-time bus location tracking, ETA pills, and route polylines.

### 🆔 10. Student ID & Document Center
- **Digital Student ID:** Official digital identification card with student photo, barcode/QR code, and verification details.
- **Document Requests:** Formal request portal for Bonafide Certificates, Transcripts, and NOCs with progress tracking.

### 🎨 11. Custom Dual-Theme System (Light / Dark Mode)
- Custom design system supporting Light, Dark, and System Default modes.
- Zero hardcoded colors; strict use of centralized theme tokens (`CustomTheme.colors`).
- Seamless edge-to-edge layout integration with `statusBarsPadding` and `navigationBarsPadding`.

---

## 🛠️ Technical Architecture & Engineering Decisions

ERPStudent is crafted adhering to modern Android & Kotlin Multiplatform development best practices:

```
┌────────────────────────────────────────────────────────┐
│                      :composeApp                       │
│     (Android & iOS Entry Points, Dependency Root)      │
└──────────────────────────┬─────────────────────────────┘
                           │
┌──────────────────────────▼─────────────────────────────┐
│                       :feature                         │
│  UI Screens, MVI ViewModels, Compose Multiplatform     │
└───────────┬────────────────────────────────┬───────────┘
            │                                │
┌───────────▼───────────┐        ┌───────────▼───────────┐
│        :domain        │        │         :core         │
│ UseCases, Domain Model│        │ Design System, Routes │
└───────────▲───────────┘        │ HTTP Utils, Theme     │
            │                    └───────────▲───────────┘
┌───────────┴───────────┐                    │
│         :data         ├────────────────────┘
│ Room KMP, Ktor Client │
│ Repositories, DAOs    │
└───────────────────────┘
```

### 1. Unidirectional Data Flow (MVI)
Each feature screen is isolated with 4 predictable, testable components:
- **`[Feature]Screen.kt`**: Pure declarative UI composables observing immutable state.
- **`[Feature]ViewModel.kt`**: State holder processing user intents and business logic.
- **`[Feature]State.kt`**: Immutable data class capturing the entire UI state.
- **`[Feature]Event.kt`**: Sealed interface defining all possible user actions/intents.

### 2. Multi-Module Separation
- **`:composeApp`**: Application orchestrator, Android `Application` class, iOS `UIViewController` factory, and root dependency graph.
- **`:feature`**: Modular feature packages containing Compose Multiplatform UI components, ViewModels, and navigation entry providers.
- **`:domain`**: Pure Kotlin business layer containing Models, Repository Interfaces, and Use Cases with zero Android/UI dependencies.
- **`:data`**: Data layer containing Ktor API clients, Room Database, DAOs, Entity mappers, and Repository implementations.
- **`:core`**: Common utilities, HTTP error handling (`Result<T>`), Design System (`CustomColorScheme`), DataStore factory, and type-safe navigation definitions.

### 3. JetBrains Navigation 3
Utilizes JetBrains Navigation 3 (`androidx.navigation3`) with type-safe route serializability (`@Serializable`), backstack management, and isolated entry providers per feature module.

### 4. Robust Offline Caching & Networking
- **AndroidX Room (KMP):** SQLite bundled driver providing local database persistence across Kotlin Multiplatform targets.
- **Ktor 3.x Client:** Fully configured with JSON serialization, HTTP timeout controls, logging, and automated JWT token refresh interceptors.
- **DataStore Preferences:** Multiplatform key-value persistence for authentication tokens and user settings.

---

## 🧰 Technology Stack & Libraries

| Category | Technology |
| :--- | :--- |
| **Language** | [Kotlin 2.4.20](https://kotlinlang.org/) |
| **UI Framework** | [Compose Multiplatform 1.12.1](https://www.jetbrains.com/lp/compose-multiplatform/) (Material 3) |
| **Targets** | Android (minSdk 26, targetSdk 37) & iOS (Arm64, Simulator) |
| **Architecture** | Clean Architecture + MVI (Model-View-Intent) |
| **Dependency Injection** | [Koin 4.2.2](https://insert-koin.io/) (`koin-compose-viewmodel`, `koin-android`) |
| **Networking** | [Ktor 3.6.0](https://ktor.io/) (OkHttp on Android, Darwin on iOS, ContentNegotiation) |
| **Serialization** | `kotlinx.serialization` 1.11.0 |
| **Local Database** | [AndroidX Room 2.8.5](https://developer.android.com/kotlin/multiplatform/room) with SQLite Bundled Driver |
| **Local Key-Value** | AndroidX DataStore Preferences 1.2.1 |
| **Asynchronous Engine** | Kotlin Coroutines 1.11.0 & Kotlin Flow |
| **Image Loading** | [Coil 3.6.3](https://coil-kt.github.io/coil/) (Multiplatform + Ktor 3 engine) |
| **Navigation** | [JetBrains Navigation 3](https://github.com/JetBrains/compose-multiplatform) |
| **Maps & Location** | Google Maps Compose 8.6.0 with multiplatform `expect`/`actual` |
| **Logging** | [Kermit 2.2.0](https://kermit.touchlab.co/) by Touchlab |
| **Date & Time** | `kotlinx-datetime` 0.8.0 |

---

## 📂 Project Directory Structure

```
ERPStudent/
├── composeApp/                     # App entry point, Application class & iOS MainViewController
│   ├── src/androidMain/            # Android manifest, Activity, Room factory, App initialization
│   ├── src/commonMain/             # App composable, Navigation router, Theme wrapper
│   └── src/iosMain/                # iOS ViewController and platform initializers
├── core/                           # Core foundation module
│   └── src/commonMain/kotlin/
│       ├── constants/              # API endpoints, constants, and storage keys
│       ├── datastore/              # DataStore preference factory
│       ├── http/                   # Result wrapper, HTTP response helper, error codes
│       ├── navigation/             # Type-safe Serializable NavRoutes
│       └── theme/                  # Design system, CustomColorScheme, light/dark themes
├── data/                           # Data layer module
│   └── src/commonMain/kotlin/
│       ├── local/                  # Room Database, DAOs, Entities, Type Converters
│       ├── remote/                 # Ktor API services, Request & Response DTOs
│       └── repository/             # Repository implementations (Auth, User, Common)
├── domain/                         # Domain layer module (Pure Kotlin)
│   └── src/commonMain/kotlin/
│       ├── model/                  # Domain business entities (User, Tenant)
│       ├── repository/             # Abstract Repository interfaces
│       └── usecase/                # Single-purpose business use cases
├── feature/                        # Feature presentation module
│   └── src/commonMain/kotlin/
│       ├── assignment/             # Assignment dashboard & digital submission
│       ├── attendance/             # Attendance calendar, subject metrics, and alerts
│       ├── auth/                   # Tenant selection & student login
│       ├── common/                 # Password recovery, OTP verification, support
│       ├── documents/              # Digital student ID & certificate requests
│       ├── events/                 # College extracurriculars & event details
│       ├── exam/                   # Examination schedule & digital hall ticket
│       ├── fees/                   # Invoicing, fee summary & simulated payment
│       ├── home/                   # Student dashboard, alerts, quick actions
│       ├── hostel/                 # Stay details, gate pass, mess menu, complaints
│       ├── leave/                  # Leave application & history
│       ├── library/                # Book search, catalog, issued books, fines
│       ├── marks/                  # Academic performance, radar chart, grade card
│       ├── schedule/               # Interactive timetable & class timings
│       ├── settings/               # Profile management & dual-theme switcher
│       └── transport/              # Route details & live GPS bus tracking
└── iosApp/                         # iOS Xcode project entry point (SwiftUI wrapper)
```

---

## 💻 Building and Running the Project

### Prerequisites
- **JDK:** OpenJDK 17 or 21
- **Android Studio:** Android Studio Ladybug (or newer) with Compose Multiplatform plugin
- **Xcode:** Xcode 15+ (for iOS compilation, macOS only)
- **CocoaPods / Swift Package Manager:** Configured for iOS dependencies

### Android Build
Build the debug APK directly via Gradle:

```bash
# On macOS / Linux
./gradlew :composeApp:assembleDebug

# On Windows (PowerShell)
.\gradlew.bat :composeApp:assembleDebug
```

The output APK will be generated at:
`composeApp/build/outputs/apk/debug/erp_student.apk`

### iOS Build
1. Open the `/iosApp` directory in Xcode.
2. Select an iOS Simulator (e.g., iPhone 15 / 16) or a connected physical iOS device.
3. Build and run using `Product > Run` (`Cmd + R`).

---

## 👨‍💻 Developer & Contact

**Piyush Prajapati**  
- **GitHub:** [@piyushprajpti](https://github.com/piyushprajpti)  
- **Email:** [eng.piyushp@gmail.com](mailto:eng.piyushp@gmail.com)  
- **Repository:** [ERPStudent on GitHub](https://github.com/piyushprajpti/ERPStudent)  
- **Related Project:** [ERPTeacher on GitHub](https://github.com/piyushprajpti/ERPTeacher)  

---

<p align="center">
  <i>Developed with ❤️ using Kotlin Multiplatform & Compose Multiplatform</i>
</p>