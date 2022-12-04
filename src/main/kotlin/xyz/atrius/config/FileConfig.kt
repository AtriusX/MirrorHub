package xyz.atrius.config

import com.sksamuel.hoplite.ConfigLoader
import com.sksamuel.hoplite.PropertySource
import com.sksamuel.hoplite.watch.FixedIntervalWatchable
import com.sksamuel.hoplite.watch.Watchable
import org.koin.core.annotation.*
import xyz.atrius.data.config.ConfigLoaderProvider
import xyz.atrius.data.config.FilePropertySource
import kotlin.time.Duration.Companion.seconds

/**
 * @author Atri
 *
 * Contains all beans relating to external file configuration.
 */
@Module
class FileConfig {

    /**
     * Provides a [Watchable] object that determines how often the configuration is refreshed.
     */
    @Single
    @Named("SystemConfigWatcher")
    fun systemConfigWatcher(): Watchable =
        FixedIntervalWatchable(10.seconds.inWholeMilliseconds)

    /**
     * Provides a [PropertySource] that points to an externally saved file.
     */
    @Single
    @Named("SystemConfigProperty")
    fun systemConfigProperty(): PropertySource =
        FilePropertySource("./configurations/config.yml")

    /**
     * Provides a [ConfigLoader] that will pull data from an internal config, unless
     * an external one is defined.
     */
    @Single
    @Named("SystemConfigLoader")
    fun systemConfigLoader(
        @Named("SystemConfigProperty")
        source: PropertySource,
        provider: ConfigLoaderProvider
    ): ConfigLoader = provider
        .getFileLoader("/config.yml", source)
}
