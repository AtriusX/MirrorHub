package xyz.atrius.logging

import org.koin.core.annotation.Single
import xyz.atrius.logging.config.LoggerConfigProvider
import xyz.atrius.logging.config.LoggerTemplate

/**
 * @author Atri
 *
 * Provides a simple way of generating a logger with a given origin class.
 *
 * @property configProvider The base configuration used to build this logger.
 * @property loggerTemplate The template strings provided for logger formatting.
 */
@Single
class LoggerProvider(
    val configProvider: LoggerConfigProvider,
    val loggerTemplate: LoggerTemplate
) {

    /**
     * Generates a [Logger] with the application's existing logger template
     * and configuration settings.
     *
     * @return A [Logger] instance set to the log actions in class [T].
     */
    inline fun <reified T : Any> create(): Logger<T> =
        Logger(T::class, configProvider, loggerTemplate)
}