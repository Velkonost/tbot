package com.velkonost.tbot.data.database

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jetbrains.exposed.sql.Database

object DatabaseFactory {

    private const val MAX_POOL_SIZE = 10

    fun init(url: String, user: String, password: String) {
        val config = HikariConfig().apply {
            jdbcUrl = url
            driverClassName = "org.postgresql.Driver"
            username = user
            this.password = password
            maximumPoolSize = MAX_POOL_SIZE
            isAutoCommit = false
            transactionIsolation = "TRANSACTION_REPEATABLE_READ"
            validate()
        }
        Database.connect(HikariDataSource(config))
    }
}
