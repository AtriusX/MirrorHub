package xyz.atrius.config

import org.koin.core.annotation.*

/**
 * @author Atri
 *
 * Global configuration which permits access to all beans in the application.
 */
@Module(includes = [FileConfig::class])
@ComponentScan("xyz.atrius")
class AppConfig