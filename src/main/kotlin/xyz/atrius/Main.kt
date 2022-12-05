package xyz.atrius

import org.koin.core.context.startKoin
import org.koin.ksp.generated.module
import xyz.atrius.api.github.GithubApi
import xyz.atrius.config.AppConfig
import xyz.atrius.logging.LoggerProvider

val koin = startKoin { modules(AppConfig().module) }
    .koin

object Main {

    suspend fun main(user: String) {
        val logger = koin
            .get<LoggerProvider>()
            .create<Main>()
        val client = koin
            .get<GithubApi>()
        client.getContributions(user)
            .filter { it.commitCount > 0 }
            .map { "${it.date} > ${it.commitCount}" }
            .forEach(logger::info)
    }
}

suspend fun main(args: Array<String>) =
    Main.main(args.getOrNull(0) ?: "AtriusX")
