package xyz.atrius.data.config

/**
 * @author Atri
 *
 * Definition of an application config file.
 *
 * @property T The configuration's property declaration.
 */
interface Config<T> {

    /**
     * Gets the internal configuration object.
     *
     * @return The configuration object.
     */
    fun retrieve(): T
}