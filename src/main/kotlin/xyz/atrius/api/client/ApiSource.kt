package xyz.atrius.api.client

/**
 * @author Atri
 *
 * Defines an API source that can retrieve contribution data from remove services. This could include
 * services like GitHub, Gitlab, and others; however currently only GitHub is supported.
 */
interface ApiSource {

    /**
     * Checks if the current API Client is rate limited. Other client functions should rely on this to
     * check if they can make another call.
     *
     * @return True if the client is currently not able to make another call.
     */
    suspend fun isRateLimited(): Boolean

    /**
     * Retrieves all the contributions of a given user account.
     *
     * @param user The user account to retrieve contribution data from.
     *
     * @return A list of dated contribution counts.
     */
    suspend fun getContributions(user: String): List<Contributions>
}