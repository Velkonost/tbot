package com.velkonost.tbot.domain.usecase

import com.velkonost.tbot.domain.entity.Task
import com.velkonost.tbot.domain.repository.TaskRepository

class GetUserTasksUseCase(private val repository: TaskRepository) {
    suspend operator fun invoke(userId: Long): List<Task> = repository.getAllByUserId(userId)
}
