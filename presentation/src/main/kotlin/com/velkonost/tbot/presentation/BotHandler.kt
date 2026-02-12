package com.velkonost.tbot.presentation

import com.github.kotlintelegrambot.Bot
import com.github.kotlintelegrambot.bot
import com.github.kotlintelegrambot.dispatch
import com.github.kotlintelegrambot.dispatcher.command
import com.github.kotlintelegrambot.entities.ChatId
import org.slf4j.LoggerFactory

class BotHandler(private val token: String) {

    private val logger = LoggerFactory.getLogger(BotHandler::class.java)

    fun create(): Bot = bot {
        this.token = this@BotHandler.token

        dispatch {
            command("start") {
                bot.sendMessage(
                    chatId = ChatId.fromId(message.chat.id),
                    text = "Welcome to TBot — your task manager!\nUse /help to see available commands."
                )
            }

            command("help") {
                bot.sendMessage(
                    chatId = ChatId.fromId(message.chat.id),
                    text = buildString {
                        appendLine("Available commands:")
                        appendLine("/newtask <title> — create a new task")
                        appendLine("/tasks — list your tasks")
                        appendLine("/done <id> — mark task as completed")
                        appendLine("/delete <id> — delete a task")
                    }
                )
            }
        }
    }

    fun start() {
        logger.info("Starting Telegram bot...")
        create().startPolling()
    }
}
