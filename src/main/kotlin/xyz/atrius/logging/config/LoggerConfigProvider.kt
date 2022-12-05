package xyz.atrius.logging.config

import org.koin.core.annotation.Single
import xyz.atrius.data.config.SystemConfigProvider
import xyz.atrius.data.config.SystemConfig

/**
 * @author Atri
 *
 * Providers the application's current [LoggerConfig] data to a receiver.
 *
 * @property systemConfigProvider
 * The [SystemConfigProvider] the contains the current [LoggerConfig] data.
 */
@Single
class LoggerConfigProvider(
    private val systemConfigProvider: SystemConfigProvider
) {

    /**
     * Retrieves the current configuration data from the existing [SystemConfig].
     *
     * @return The current [LoggerConfig] data.
     */
    fun retrieve(): LoggerConfig =
        systemConfigProvider.retrieve().loggerConfig
}