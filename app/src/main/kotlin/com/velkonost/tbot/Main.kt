package com.velkonost.tbot

import com.velkonost.tbot.data.database.DatabaseFactory
import com.velkonost.tbot.data.di.dataModule
import com.velkonost.tbot.domain.di.domainModule
import com.velkonost.tbot.presentation.BotHandler
import com.velkonost.tbot.presentation.di.presentationModule
import org.koin.core.context.startKoin
import org.koin.core.parameter.parametersOf
import org.slf4j.LoggerFactory

fun main() {
    val logger = LoggerFactory.getLogger("com.velkonost.tbot.Main")

    val botToken = System.getenv("BOT_TOKEN")
        ?: error("BOT_TOKEN environment variable is required")
    val dbUrl = System.getenv("DATABASE_URL")
        ?: "jdbc:postgresql://localhost:5432/tbot"
    val dbUser = System.getenv("DATABASE_USER") ?: "tbot"
    val dbPassword = System.getenv("DATABASE_PASSWORD") ?: "tbot"

    logger.info("Initializing database...")
    DatabaseFactory.init(dbUrl, dbUser, dbPassword)

    val koinApp = startKoin {
        properties(mapOf("BOT_TOKEN" to botToken))
        modules(domainModule, dataModule, presentationModule)
    }

    logger.info("Starting application...")
    val botHandler = koinApp.koin.get<BotHandler>()
    botHandler.start()
}
