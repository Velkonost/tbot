package com.velkonost.tbot.domain.model

import java.time.LocalDateTime

data class Task(
    val id: Long = 0,
    val userId: Long,
    val title: String,
    val description: String = "",
    val priority: Priority = Priority.MEDIUM,
    val deadline: LocalDateTime? = null,
    val completed: Boolean = false,
    val createdAt: LocalDateTime = LocalDateTime.now(),
)

enum class Priority {
    LOW, MEDIUM, HIGH
}
