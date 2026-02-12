package com.velkonost.tbot.domain.usecase

import com.velkonost.tbot.domain.model.Task
import com.velkonost.tbot.domain.repository.TaskRepository

class CompleteTaskUseCase(
    private val taskRepository: TaskRepository,
) : UseCase<Long, Task?> {
    override suspend fun invoke(params: Long): Task? {
        return taskRepository.markCompleted(params)
    }
}
