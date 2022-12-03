package xyz.atrius.data.config

import com.sksamuel.hoplite.ConfigLoader
import com.sksamuel.hoplite.ConfigLoaderBuilder
import com.sksamuel.hoplite.addResourceSource

/**
 * @author Atri
 *
 * Provides a [ConfigLoader] with the required specifications.
 */
object ConfigLoaderProvider {

    /**
     * Provides a function which can be used to generate a
     * [FilePropertySource]-based [ConfigLoader].
     *
     * @param resource The location of the resource in the JVM classpath.
     * @param userLocation The location that external file changes are read from.
     *
     * @return A new [ConfigLoader] that tracks external file changes.
     */
    fun getFileLoader(
        resource: String,
        userLocation: String = resource
    ): ConfigLoader = ConfigLoaderBuilder
        .default()
        .addPropertySource(FilePropertySource(userLocation))
        .addResourceSource(resource)
        .build()
}
