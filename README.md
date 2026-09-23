# HEARHERE

An Android audiobook app built with Kotlin. Users can create an account, browse a home feed of books, view book details, and save books to a personal library that works offline.

## Features

- **Login & Register** – account creation and sign-in with token-based authentication
- **Home feed** – book sections and recently listened books loaded from the backend
- **Book details** – title, author, cover and expandable description
- **Library** – save books locally with Room and access them anytime
- **Search** – find books in the catalog
- **Splash screen** – using the AndroidX SplashScreen API

## Tech Stack

| Area | Library |
|------|---------|
| Language | Kotlin |
| Architecture | MVVM (ViewModel + LiveData + Repository) |
| Networking | Retrofit, OkHttp (auth interceptor), Moshi |
| Async | Kotlin Coroutines |
| Local database | Room |
| Navigation | Jetpack Navigation Component + Safe Args |
| UI | XML layouts, Data Binding, Material Components |
| Images | Glide |
| Storage | SharedPreferences (auth token) |

## Project Structure

```
com.example.hearhere
├── generic/            # Shared request/response helpers
├── models/             # API models (Home, Book, Login, Register)
│   └── dbModels/       # Room entities
├── repository/
│   ├── local/          # Room database, DAO, repository, token cache
│   └── remote/         # Retrofit API, client, auth interceptor
└── screen/
    ├── home/           # Home feed and adapters
    │   └── aboutBook/  # Book details screen
    ├── library/        # Saved books
    ├── login/
    ├── register/
    └── search/
```

## Requirements

- Android Studio (latest stable)
- JDK 17–21 (Gradle 8.7 does not support newer JDKs)
- Android SDK 34
- Min SDK: 24 (Android 7.0)

## Getting Started

1. Clone the repository:
   ```bash
   git clone https://github.com/YOUR_USERNAME/HEARHERE2.git
   ```
2. Open the project in Android Studio.
3. Set the Gradle JDK to 21: **Settings → Build, Execution, Deployment → Build Tools → Gradle → Gradle JDK**.
4. Sync Gradle and run the app on an emulator or device.

## API

The app connects to a REST backend. The base URL is set in
`app/src/main/java/com/example/hearhere/repository/remote/RetrofitInstance.kt`.

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `home` | Home feed data |
| GET | `book/{id}` | Book details |
| POST | `register` | Create an account |
| POST | `login` | Sign in and receive a token |

## Screenshots

_Add screenshots here._
