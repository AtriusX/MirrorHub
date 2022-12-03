package xyz.atrius.data.config

import com.sksamuel.hoplite.ConfigLoader
import com.sksamuel.hoplite.watch.ReloadableConfig
import com.sksamuel.hoplite.watch.Watchable

/**
 * @author Atri
 *
 * Configuration file referencing the main system config. The configuration file will
 * update automatically over a set interval provided by the [watcher].
 *
 * @property watcher Provides the logic for when the file should be re-checked.
 * @property loader Provides the location data of the watched file.
 */
class SystemConfigProvider(
    private val watcher: Watchable,
    private val loader: ConfigLoader
) : Config<SystemConfig> {

    private val configLoader: ReloadableConfig<SystemConfig> =
        ReloadableConfig(loader, SystemConfig::class)
            .addWatcher(watcher)
            .also { it.subscribe(::println) }

    override fun retrieve(): SystemConfig =
        configLoader.getLatest()
}