package xyz.atrius.logging

import xyz.atrius.logging.config.LogLevel
import xyz.atrius.logging.config.LoggerConfig
import xyz.atrius.logging.config.LoggerTemplate
import xyz.atrius.logging.config.defaultConfig
import java.time.Instant
import kotlin.reflect.KClass

/**
 * Logger implementation for application.
 *
 * @property originator The originating object of the logger.
 * @property loggerConfig The configuration associated with this logger.
 */
@Suppress("unused")
class Logger<T : Any>(
    private val originator: KClass<T>,
    private val loggerConfig: LoggerConfig = defaultConfig
) {

    /**
     * Logs a fatal error message.
     *
     * @param msg The message to log.
     */
    fun fatal(msg: String) =
        log(msg, LogLevel.FATAL)

    /**
     * Logs a normal error message.
     *
     * @param msg The message to log.
     */
    fun error(msg: String) =
        log(msg, LogLevel.ERROR)

    /**
     * Logs a warning message.
     *
     * @param msg The message to log.
     */
    fun warn(msg: String) =
        log(msg, LogLevel.WARN)

    /**
     * Logs a standard info message.
     *
     * @param msg The message to log.
     */
    fun info(msg: String) =
        log(msg, LogLevel.INFO)

    /**
     * Logs a debug message.
     *
     * @param msg The message to log.
     */
    fun debug(msg: String) =
        log(msg, LogLevel.DEBUG)

    /**
     * Logs a trace-level message.
     *
     * @param msg The message to log.
     */
    fun trace(msg: String) =
        log(msg, LogLevel.TRACE)

    /**
     * Alternative logging function that allows you to specify
     * the logging level explicitly.
     *
     * @param msg The message to log.
     * @param logLevel The logging level of the message.
     */
    fun log(
        msg: String,
        logLevel: LogLevel
    ) = when (LogLevel.permitLogging(loggerConfig.highestLoggingLevel, logLevel)) {
        true -> printMessage(msg, logLevel)
        else -> Unit
    }

    internal fun printMessage(msg: String, logLevel: LogLevel) {
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
            .padStart(loggerConfig.levelPadding, loggerConfig.paddingCharacter)
        // Retrieve the color code for the given level if present
        return when(val color = loggerConfig.logLevelColors[level]){
            null -> padded
            else -> color.format(padded)
        }
    }

    private fun processOriginator(): String? {
        // Apply padding to originator name
        val padded = originator.simpleName
            ?.padEnd(loggerConfig.originatorPadding, loggerConfig.paddingCharacter)
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
        msg: String
    ): String = when(val color = loggerConfig.messageColor) {
        null -> msg
        else -> color.format(msg)
    }

    private fun formatOutput(
        level: String,
        originator: String?,
        time: String,
        message: String
    ): String = loggerConfig.loggerFormat
        .replace(LoggerTemplate.LEVEL, level)
        .replace(LoggerTemplate.ORIGINATOR, originator ?: "")
        .replace(LoggerTemplate.TIME, time)
        .replace(LoggerTemplate.MESSAGE, message)

    companion object {

        /**
         * Generates a [Logger] using type parameter syntax.
         *
         * @param logLevel The highest permitted logging level.
         *
         * @return A [Logger] instance set at the given [logLevel].
         */
        inline fun <reified T : Any> create(
            logLevel: LogLevel = LogLevel.INFO
        ): Logger<T> = Logger(T::class, LoggerConfig(logLevel))

        /**
         * Generates a [Logger] using type parameter syntax.
         *
         * @param config The logger config to provide to the logger.
         *
         * @return The newly created [Logger] instance.
         */
        inline fun <reified T : Any> create(
            config: LoggerConfig
        ): Logger<T> = Logger(T::class, config)
    }
}