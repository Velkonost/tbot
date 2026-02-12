package com.velkonost.tbot.domain.usecase

import com.velkonost.tbot.domain.entity.Task
import com.velkonost.tbot.domain.repository.TaskRepository

class CreateTaskUseCase(private val repository: TaskRepository) {
    suspend operator fun invoke(task: Task): Task = repository.create(task)
}
