package xyz.atrius

import org.koin.core.context.startKoin
import org.koin.ksp.generated.module
import xyz.atrius.config.AppConfig
import xyz.atrius.data.config.SystemConfigProvider
import xyz.atrius.logging.LoggerProvider

val koin = startKoin { modules(AppConfig().module) }
    .koin

object Main {

    fun main() {
        val logger = koin
            .get<LoggerProvider>()
            .create<Main>()
        koin.get<SystemConfigProvider>()
            .addSubscriber(logger::info)
            .addErrorHandler { logger.error(it.message) }
        while (true) {}
    }
}

fun main() = Main.main()