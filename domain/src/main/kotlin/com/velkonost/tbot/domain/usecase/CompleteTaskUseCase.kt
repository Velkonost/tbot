package com.velkonost.tbot.domain.usecase

import com.velkonost.tbot.domain.entity.Task
import com.velkonost.tbot.domain.repository.TaskRepository

class CompleteTaskUseCase(private val repository: TaskRepository) {
    suspend operator fun invoke(taskId: Long): Task? {
        val task = repository.getById(taskId) ?: return null
        return repository.update(task.copy(completed = true))
    }
}
