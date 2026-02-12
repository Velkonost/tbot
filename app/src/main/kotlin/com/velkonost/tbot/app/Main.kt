package com.velkonost.tbot.app

import com.velkonost.tbot.app.di.appModule
import com.velkonost.tbot.app.di.dataModule
import com.velkonost.tbot.app.di.domainModule
import com.velkonost.tbot.app.di.presentationModule
import org.koin.core.context.startKoin
import org.slf4j.LoggerFactory

private val logger = LoggerFactory.getLogger("Main")

fun main() {
    logger.info("Starting TBot...")

    startKoin {
        modules(appModule, domainModule, dataModule, presentationModule)
    }

    // TODO: Initialize database, start bot polling
    logger.info("TBot started successfully")
}
