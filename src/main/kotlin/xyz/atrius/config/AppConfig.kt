package xyz.atrius.config

import org.koin.core.annotation.*
import xyz.atrius.logging.config.LoggerConfig

/**
 * @author Atri
 *
 * Global configuration which permits access to all beans in the application.
 */
@Module(includes = [FileConfig::class])
@ComponentScan("xyz.atrius")
class AppConfig {

    /**
     * Provides a default logger configuration.
     */
    @Single
    fun loggerConfig(): LoggerConfig =
        LoggerConfig()
}