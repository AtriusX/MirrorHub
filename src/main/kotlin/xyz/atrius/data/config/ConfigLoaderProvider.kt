package xyz.atrius.data.config

import com.sksamuel.hoplite.ConfigLoader
import com.sksamuel.hoplite.ConfigLoaderBuilder
import com.sksamuel.hoplite.PropertySource
import com.sksamuel.hoplite.addResourceSource
import org.koin.core.annotation.Single

/**
 * @author Atri
 *
 * Provides a [ConfigLoader] with the required specifications.
 */
@Single
class ConfigLoaderProvider {

    /**
     * Provides a function which can be used to generate a
     * [FilePropertySource]-based [ConfigLoader].
     *
     * @param resource The location of the resource in the JVM classpath.
     * @param propertySource The source of where properties are pulled from.
     *
     * @return A new [ConfigLoader] that tracks external file changes.
     */
    fun getFileLoader(
        resource: String,
        propertySource: PropertySource
    ): ConfigLoader = ConfigLoaderBuilder
        .default()
        .addPropertySource(propertySource)
        .addResourceSource(resource)
        .build()
}
