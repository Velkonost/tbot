package com.velkonost.tbot.presentation.di

import com.velkonost.tbot.presentation.BotHandler
import org.koin.dsl.module

val presentationModule = module {
    single { BotHandler(getProperty("BOT_TOKEN")) }
}
