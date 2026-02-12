package com.velkonost.tbot.data.repository

import com.velkonost.tbot.data.database.TasksTable
import com.velkonost.tbot.domain.model.Priority
import com.velkonost.tbot.domain.model.Task
import com.velkonost.tbot.domain.repository.TaskRepository
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.update
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import java.time.LocalDateTime

class TaskRepositoryImpl : TaskRepository {

    override suspend fun create(task: Task): Task = dbQuery {
        val id = TasksTable.insertAndGetId {
            it[userId] = task.userId
            it[title] = task.title
            it[description] = task.description
            it[priority] = task.priority.name
            it[deadline] = task.deadline
            it[completed] = task.completed
            it[createdAt] = task.createdAt
        }
        task.copy(id = id.value)
    }

    override suspend fun getById(id: Long): Task? = dbQuery {
        TasksTable.selectAll().where { TasksTable.id eq id }
            .map(::toTask)
            .singleOrNull()
    }

    override suspend fun getAllByUser(userId: Long): List<Task> = dbQuery {
        TasksTable.selectAll().where { TasksTable.userId eq userId }
            .map(::toTask)
    }

    override suspend fun update(task: Task): Task = dbQuery {
        TasksTable.update({ TasksTable.id eq task.id }) {
            it[title] = task.title
            it[description] = task.description
            it[priority] = task.priority.name
            it[deadline] = task.deadline
            it[completed] = task.completed
        }
        task
    }

    override suspend fun delete(id: Long) = dbQuery {
        TasksTable.deleteWhere { TasksTable.id eq id }
        Unit
    }

    override suspend fun markCompleted(id: Long): Task? = dbQuery {
        TasksTable.update({ TasksTable.id eq id }) {
            it[completed] = true
        }
        TasksTable.selectAll().where { TasksTable.id eq id }
            .map(::toTask)
            .singleOrNull()
    }

    private fun toTask(row: ResultRow) = Task(
        id = row[TasksTable.id].value,
        userId = row[TasksTable.userId],
        title = row[TasksTable.title],
        description = row[TasksTable.description],
        priority = Priority.valueOf(row[TasksTable.priority]),
        deadline = row[TasksTable.deadline],
        completed = row[TasksTable.completed],
        createdAt = row[TasksTable.createdAt],
    )

    private suspend fun <T> dbQuery(block: suspend () -> T): T =
        newSuspendedTransaction { block() }
}
