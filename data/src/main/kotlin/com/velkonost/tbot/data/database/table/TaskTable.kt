package com.velkonost.tbot.data.database.table

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.datetime

object TaskTable : Table("tasks") {

    private const val TITLE_MAX_LENGTH = 255
    private const val PRIORITY_MAX_LENGTH = 10

    val id = long("id").autoIncrement()
    val userId = long("user_id").index()
    val title = varchar("title", TITLE_MAX_LENGTH)
    val description = text("description").default("")
    val priority = varchar("priority", PRIORITY_MAX_LENGTH).default("MEDIUM")
    val deadline = datetime("deadline").nullable()
    val completed = bool("completed").default(false)
    val createdAt = datetime("created_at")

    override val primaryKey = PrimaryKey(id)
}
