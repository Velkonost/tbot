package com.velkonost.tbot.domain.usecase

import com.velkonost.tbot.domain.model.Task
import com.velkonost.tbot.domain.repository.TaskRepository

class GetUserTasksUseCase(
    private val taskRepository: TaskRepository,
) : UseCase<Long, List<Task>> {
    override suspend fun invoke(params: Long): List<Task> {
        return taskRepository.getAllByUser(params)
    }
}
