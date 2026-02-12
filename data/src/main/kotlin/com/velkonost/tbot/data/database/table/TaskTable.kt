package com.velkonost.tbot.data.database.table

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.datetime

object TaskTable : Table("tasks") {
    val id = long("id").autoIncrement()
    val userId = long("user_id").index()
    val title = varchar("title", 255)
    val description = text("description").default("")
    val priority = varchar("priority", 10).default("MEDIUM")
    val deadline = datetime("deadline").nullable()
    val completed = bool("completed").default(false)
    val createdAt = datetime("created_at")

    override val primaryKey = PrimaryKey(id)
}
