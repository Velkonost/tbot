package com.velkonost.tbot.app.di

import com.velkonost.tbot.presentation.bot.TBotHandler
import org.koin.dsl.module

val presentationModule = module {
    single { TBotHandler() }
}
