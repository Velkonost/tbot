package com.velkonost.tbot.data.mapper

import com.velkonost.tbot.data.database.table.TaskTable
import com.velkonost.tbot.domain.entity.Priority
import com.velkonost.tbot.domain.entity.Task
import org.jetbrains.exposed.sql.ResultRow

fun ResultRow.toTask(): Task = Task(
    id = this[TaskTable.id],
    userId = this[TaskTable.userId],
    title = this[TaskTable.title],
    description = this[TaskTable.description],
    priority = Priority.valueOf(this[TaskTable.priority]),
    deadline = this[TaskTable.deadline],
    completed = this[TaskTable.completed],
    createdAt = this[TaskTable.createdAt]
)
