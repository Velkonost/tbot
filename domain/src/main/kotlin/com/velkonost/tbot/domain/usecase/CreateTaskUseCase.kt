package com.velkonost.tbot.domain.usecase

import com.velkonost.tbot.domain.model.Task
import com.velkonost.tbot.domain.repository.TaskRepository

class CreateTaskUseCase(
    private val taskRepository: TaskRepository,
) : UseCase<Task, Task> {
    override suspend fun invoke(params: Task): Task {
        return taskRepository.create(params)
    }
}
