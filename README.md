# TBot — Telegram Task Manager

A Telegram bot for managing personal todo lists. Create, edit, and complete tasks with priorities and deadlines — all from your Telegram chat.

## Tech Stack

- **Language:** Kotlin
- **Bot Framework:** kotlin-telegram-bot
- **Database:** PostgreSQL + Exposed ORM + HikariCP
- **DI:** Koin
- **Architecture:** Clean Architecture (domain / data / presentation)
- **Build:** Gradle KTS, multi-module
- **Testing:** JUnit 5, MockK, Testcontainers
- **CI/CD:** GitHub Actions
- **Deploy:** Docker Compose
- **Static Analysis:** Detekt
- **Logging:** SLF4J + Logback

## Project Structure

```
tbot/
├── app/                  # Entry point, DI setup
├── domain/               # Entities, use cases, repository interfaces (pure Kotlin)
├── data/                 # Repository implementations, database, Exposed tables
├── presentation/         # Telegram bot handlers
├── gradle/
│   └── libs.versions.toml
├── docker-compose.yml
├── Dockerfile
├── detekt.yml
└── build.gradle.kts
```

## Getting Started

### Prerequisites

- JDK 17+
- Docker & Docker Compose
- Telegram Bot Token (from [@BotFather](https://t.me/BotFather))

### Run with Docker Compose

```bash
# 1. Clone the repository
git clone https://github.com/Velkonost/tbot.git
cd tbot

# 2. Create .env file
cp .env.example .env
# Edit .env and set your BOT_TOKEN

# 3. Start services
docker compose up -d
```

### Run Locally

```bash
# 1. Start PostgreSQL (e.g. via Docker)
docker run -d --name tbot-pg \
  -e POSTGRES_DB=tbot \
  -e POSTGRES_USER=tbot \
  -e POSTGRES_PASSWORD=tbot \
  -p 5432:5432 postgres:16-alpine

# 2. Set environment variables
export BOT_TOKEN=your-bot-token
export DATABASE_URL=jdbc:postgresql://localhost:5432/tbot
export DATABASE_USER=tbot
export DATABASE_PASSWORD=tbot

# 3. Build and run
./gradlew :app:run
```

### Run Tests

```bash
./gradlew test
```

### Run Detekt

```bash
./gradlew detekt
```

## Bot Commands

| Command             | Description                  |
|---------------------|------------------------------|
| `/start`            | Start the bot                |
| `/help`             | Show available commands      |
| `/newtask <title>`  | Create a new task            |
| `/tasks`            | List your tasks              |
| `/done <id>`        | Mark a task as completed     |
| `/delete <id>`      | Delete a task                |

## Environment Variables

| Variable            | Description                         | Default                                   |
|---------------------|-------------------------------------|-------------------------------------------|
| `BOT_TOKEN`         | Telegram Bot API token (required)   | —                                         |
| `DATABASE_URL`      | PostgreSQL JDBC URL                 | `jdbc:postgresql://localhost:5432/tbot`    |
| `DATABASE_USER`     | Database user                       | `tbot`                                    |
| `DATABASE_PASSWORD`  | Database password                   | `tbot`                                    |

## License

MIT
