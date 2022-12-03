package xyz.atrius.data.config

import com.sksamuel.hoplite.Masked
import kotlin.time.Duration

/**
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
 */
data class SystemConfig(
    val accounts: Set<String>,
    val refreshInterval: Duration,
    val accountToken: Masked?
)
