package xyz.atrius.logging

import xyz.atrius.logging.config.LogLevel
import xyz.atrius.logging.config.LoggerConfig
import xyz.atrius.logging.config.LoggerConfigProvider
import xyz.atrius.logging.config.LoggerTemplate
import java.time.Instant
import kotlin.reflect.KClass

/**
 * @author Atri
 *
 * Logger implementation for application.
 *
 * @property originator The originating object of the logger.
 * @property loggerConfig The configuration associated with this logger.
 */
@Suppress("unused")
class Logger<T : Any>(
    private val originator: KClass<T>,
    private val configProvider: LoggerConfigProvider,
    private val template: LoggerTemplate
) {

    private val loggerConfig: LoggerConfig
        get() = configProvider.retrieve()

    /**
     * Logs a fatal error message.
     *
     * @param msg The message to log.
     */
    fun fatal(msg: Any?) =
        log(msg, LogLevel.FATAL)

    /**
     * Logs a normal error message.
     *
     * @param msg The message to log.
     */
    fun error(msg: Any?) =
        log(msg, LogLevel.ERROR)

    /**
     * Logs a warning message.
     *
     * @param msg The message to log.
     */
    fun warn(msg: Any?) =
        log(msg, LogLevel.WARN)

    /**
     * Logs a standard info message.
     *
     * @param msg The message to log.
     */
    fun info(msg: Any?) =
        log(msg, LogLevel.INFO)

    /**
     * Logs a debug message.
     *
     * @param msg The message to log.
     */
    fun debug(msg: Any?) =
        log(msg, LogLevel.DEBUG)

    /**
     * Logs a trace-level message.
     *
     * @param msg The message to log.
     */
    fun trace(msg: Any?) =
        log(msg, LogLevel.TRACE)

    /**
     * Alternative logging function that allows you to specify
     * the logging level explicitly.
     *
     * @param msg The message to log.
     * @param logLevel The logging level of the message.
     */
    fun log(
        msg: Any?,
        logLevel: LogLevel
    ) = when (LogLevel.permitLogging(loggerConfig.highestLoggingLevel, logLevel)) {
        true -> printMessage(msg, logLevel)
        else -> Unit
    }

    internal fun printMessage(msg: Any?, logLevel: LogLevel) {
        val level = getLevelColor(logLevel)
        val originator = processOriginator()
        val time = processTimestamp()
        val message = processMessage(msg)
        val output = formatOutput(level, originator, time, message)
        println(output)
    }

    private fun getLevelColor(
        level: LogLevel
    ): String {
        // Apply padding to level name
        val padded = level.name
            .padStart(loggerConfig.levelPadding, loggerConfig.paddingCharacter[0])
        // Retrieve the color code for the given level if present
        return when(val color = loggerConfig.logLevelColors[level]){
            null -> padded
            else -> color.format(padded)
        }
    }

    private fun processOriginator(): String? {
        // Apply padding to originator name
        val padded = originator.simpleName
            ?.padEnd(loggerConfig.originatorPadding, loggerConfig.paddingCharacter[0])
        // Apply coloring to originator name if present
        return when (val color = loggerConfig.originatorColor) {
            null -> padded
            else -> padded?.let(color::format)
        }
    }

    private fun processTimestamp(): String {
        val time = Instant.now().toString()
        return when(val color = loggerConfig.timestampColor) {
            null -> time
            else -> color.format(time)
        }
    }

    private fun processMessage(
        msg: Any?
    ): String = when(val color = loggerConfig.messageColor) {
        null -> msg.toString()
        else -> color.format(msg)
    }

    private fun formatOutput(
        level: String,
        originator: String?,
        time: String,
        message: String
    ): String = loggerConfig.loggerFormat
        .replace(template.LEVEL, level)
        .replace(template.ORIGINATOR, originator ?: "")
        .replace(template.TIME, time)
        .replace(template.MESSAGE, message)
}