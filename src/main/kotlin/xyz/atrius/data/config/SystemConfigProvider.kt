package xyz.atrius.data.config

import com.sksamuel.hoplite.ConfigLoader
import com.sksamuel.hoplite.watch.ReloadableConfig
import com.sksamuel.hoplite.watch.Watchable
import org.koin.core.annotation.Named
import org.koin.core.annotation.Single

/**
 * @author Atri
 *
 * Configuration file referencing the main system config. The configuration file will
 * update automatically over a set interval provided by the [watcher].
 *
 * @property watcher Provides the logic for when the file should be re-checked.
 * @property loader Provides the location data of the watched file.
 */
@Single
class SystemConfigProvider(
    @Named("SystemConfigWatcher")
    private val watcher: Watchable,
    @Named("SystemConfigLoader")
    private val loader: ConfigLoader
) : Config<SystemConfig> {

    private val configLoader: ReloadableConfig<SystemConfig> =
        ReloadableConfig(loader, SystemConfig::class)
            .addWatcher(watcher)

    override fun retrieve(): SystemConfig =
        configLoader.getLatest()

    /**
     * Appends a subscriber function to the [configLoader] object. Can be used to
     * monitor changes made to the [SystemConfig] as soon as updates are received.
     *
     * @param block The subscriber function to append.
     *
     * @return The current instance of [SystemConfigProvider]
     */
    fun addSubscriber(block: (SystemConfig) -> Unit): SystemConfigProvider = apply {
        configLoader.subscribe(block)
    }
}