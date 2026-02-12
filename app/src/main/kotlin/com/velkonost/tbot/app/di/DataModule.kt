package com.velkonost.tbot.app.di

import com.velkonost.tbot.data.repository.TaskRepositoryImpl
import com.velkonost.tbot.domain.repository.TaskRepository
import org.koin.dsl.module

val dataModule = module {
    single<TaskRepository> { TaskRepositoryImpl() }
}
