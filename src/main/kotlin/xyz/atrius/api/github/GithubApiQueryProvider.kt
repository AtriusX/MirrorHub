package xyz.atrius.api.github

import org.koin.core.annotation.Single
import xyz.atrius.graphql.GetRateLimitQuery
import xyz.atrius.graphql.GetUserContributionsQuery
import xyz.atrius.graphql.GetUserCreationDateQuery

/**
 * @author Atri
 *
 * Provides access to Query objects for the GitHub GraphQL API.
 */
@Single
class GithubApiQueryProvider {

    /**
     * Provides [GetRateLimitQuery].
     */
    fun rateLimitQuery(): GetRateLimitQuery =
        GetRateLimitQuery()

    /**
     * Provides [GetUserContributionsQuery].
     *
     * @param user Whose contributions to retrieve.
     * @param year The contribution year to retrieve (since GitHub only permits querying a single year at once).
     */
    fun getUserContributionsQuery(user: String, year: Int): GetUserContributionsQuery =
        GetUserContributionsQuery(user, "$year-01-01T00:00:00Z")

    /**
     * Provides [GetUserCreationDateQuery].
     *
     * @param user Whose join date to retrieve.
     */
    fun getUserCreationDateQuery(user: String): GetUserCreationDateQuery =
        GetUserCreationDateQuery(user)
}