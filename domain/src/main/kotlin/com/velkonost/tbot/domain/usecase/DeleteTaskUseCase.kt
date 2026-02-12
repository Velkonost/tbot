package com.velkonost.tbot.domain.usecase

import com.velkonost.tbot.domain.repository.TaskRepository

class DeleteTaskUseCase(private val repository: TaskRepository) {
    suspend operator fun invoke(taskId: Long) = repository.delete(taskId)
}
