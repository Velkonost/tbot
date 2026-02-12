# TBot — Telegram Todo Bot

Telegram-бот для управления задачами: создание, редактирование, удаление, приоритеты и дедлайны с напоминаниями.

## Tech Stack

- **Kotlin** + Coroutines / Flow
- **kotlin-telegram-bot** — Telegram Bot API
- **PostgreSQL** + Exposed ORM + HikariCP
- **Koin** — Dependency Injection
- **JUnit 5** + MockK + Testcontainers
- **Detekt** — static analysis
- **Docker Compose** — containerization
- **GitHub Actions** — CI/CD
- **SLF4J + Logback** — logging

## Architecture

Clean Architecture, multi-module Gradle:

```
:app            — Entry point, DI setup (Koin modules), configuration
:domain         — Entities, use cases, repository interfaces (pure Kotlin, no framework deps)
:data           — Repository implementations, Exposed tables, DB mappers
:presentation   — Telegram bot handlers
```

## Project Structure

```
tbot/
├── app/                        # Application module
│   └── src/main/kotlin/.../
│       ├── Main.kt             # Entry point
│       └── di/                 # Koin DI modules
├── domain/                     # Domain module (pure Kotlin)
│   └── src/main/kotlin/.../
│       ├── entity/             # Task, Priority
│       ├── repository/         # Repository interfaces
│       └── usecase/            # CreateTask, CompleteTask, ...
├── data/                       # Data module
│   └── src/main/kotlin/.../
│       ├── database/           # DatabaseFactory, Exposed tables
│       ├── mapper/             # ResultRow -> Entity mappers
│       └── repository/         # Repository implementations
├── presentation/               # Presentation module
│   └── src/main/kotlin/.../
│       └── bot/                # Telegram bot handlers
├── gradle/libs.versions.toml   # Version catalog
├── detekt.yml                  # Static analysis config
├── Dockerfile
├── docker-compose.yml
└── .github/workflows/ci.yml   # CI pipeline
```

## Getting Started

### Prerequisites

- JDK 17+
- Docker & Docker Compose
- Telegram Bot Token (via @BotFather)

### Run with Docker Compose

```bash
cp .env.example .env
# Edit .env — set your BOT_TOKEN

docker compose up -d
```

### Run locally

```bash
cp .env.example .env
# Edit .env — set your BOT_TOKEN and DB credentials

# Start PostgreSQL
docker compose up -d postgres

# Run the app
./gradlew :app:run
```

### Run tests

```bash
./gradlew test
```

### Run static analysis

```bash
./gradlew detekt
```

## License

MIT
