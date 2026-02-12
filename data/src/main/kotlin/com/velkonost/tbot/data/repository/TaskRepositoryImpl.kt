package com.velkonost.tbot.data.repository

import com.velkonost.tbot.data.database.table.TaskTable
import com.velkonost.tbot.data.mapper.toTask
import com.velkonost.tbot.domain.entity.Task
import com.velkonost.tbot.domain.repository.TaskRepository
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.SortOrder
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.update
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import java.time.LocalDateTime

class TaskRepositoryImpl : TaskRepository {

    override suspend fun create(task: Task): Task = newSuspendedTransaction {
        val id = TaskTable.insert {
            it[userId] = task.userId
            it[title] = task.title
            it[description] = task.description
            it[priority] = task.priority.name
            it[deadline] = task.deadline
            it[completed] = task.completed
            it[createdAt] = task.createdAt
        } get TaskTable.id

        task.copy(id = id)
    }

    override suspend fun getById(id: Long): Task? = newSuspendedTransaction {
        TaskTable.selectAll()
            .where { TaskTable.id eq id }
            .map { it.toTask() }
            .singleOrNull()
    }

    override suspend fun getAllByUserId(userId: Long): List<Task> = newSuspendedTransaction {
        TaskTable.selectAll()
            .where { TaskTable.userId eq userId }
            .orderBy(TaskTable.createdAt, SortOrder.DESC)
            .map { it.toTask() }
    }

    override suspend fun update(task: Task): Task = newSuspendedTransaction {
        TaskTable.update({ TaskTable.id eq task.id }) {
            it[title] = task.title
            it[description] = task.description
            it[priority] = task.priority.name
            it[deadline] = task.deadline
            it[completed] = task.completed
        }
        task
    }

    override suspend fun delete(id: Long): Unit = newSuspendedTransaction {
        TaskTable.deleteWhere { TaskTable.id eq id }
    }

    override suspend fun getOverdueTasks(): List<Task> = newSuspendedTransaction {
        TaskTable.selectAll()
            .where { (TaskTable.deadline lessEq LocalDateTime.now()) and (TaskTable.completed eq false) }
            .map { it.toTask() }
    }
}
