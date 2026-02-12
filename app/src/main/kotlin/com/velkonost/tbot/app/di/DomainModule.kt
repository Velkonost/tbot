package com.velkonost.tbot.app.di

import com.velkonost.tbot.domain.usecase.CompleteTaskUseCase
import com.velkonost.tbot.domain.usecase.CreateTaskUseCase
import com.velkonost.tbot.domain.usecase.DeleteTaskUseCase
import com.velkonost.tbot.domain.usecase.GetUserTasksUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { CreateTaskUseCase(get()) }
    factory { GetUserTasksUseCase(get()) }
    factory { CompleteTaskUseCase(get()) }
    factory { DeleteTaskUseCase(get()) }
}
