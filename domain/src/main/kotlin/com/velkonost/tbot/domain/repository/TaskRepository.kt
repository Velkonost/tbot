package com.velkonost.tbot.domain.repository

import com.velkonost.tbot.domain.entity.Task

interface TaskRepository {
    suspend fun create(task: Task): Task
    suspend fun getById(id: Long): Task?
    suspend fun getAllByUserId(userId: Long): List<Task>
    suspend fun update(task: Task): Task
    suspend fun delete(id: Long)
    suspend fun getOverdueTasks(): List<Task>
}
