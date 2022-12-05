package xyz.atrius.data.config

import com.sksamuel.hoplite.Masked
import xyz.atrius.logging.config.LoggerConfig
import kotlin.time.Duration

/**
 * @author Atri
 *
 * The system configuration file.
 *
 * @property accounts
 *  The set of user accounts that should be mirrored.
 *
 * @property refreshInterval
 *  How often to check the GitHub API for updates.
 *
 *  @property accountToken
 *  The general token of the account you want to mirror
 *  all your contributions to.
 *
 *  @property loggerConfig
 *  The logger formatting configuration.
 *  @see LoggerConfig
 */
data class SystemConfig(
    val accounts: Set<String>,
    val refreshInterval: Duration,
    val accountToken: Masked?,
    val loggerConfig: LoggerConfig
)
