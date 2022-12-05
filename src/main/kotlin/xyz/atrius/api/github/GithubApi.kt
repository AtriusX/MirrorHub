package xyz.atrius.api.github

import com.apollographql.apollo3.ApolloClient
import org.koin.core.annotation.Single
import xyz.atrius.api.apollo.executeQuery
import xyz.atrius.api.client.ApiSource
import xyz.atrius.api.client.Contributions
import xyz.atrius.data.config.SystemConfigProvider
import xyz.atrius.graphql.GetUserContributionsQuery
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoField

/**
 * @author Atri
 *
 * Api Source for pulling contribution data from GitHub.
 *
 * @property apiClientProvider Provides access to GitHub's API client.
 * @property apiQueryProvider Provides access to GitHub's API queries.
 * @property systemConfig Provides access to the main account token.
 */
@Single
class GithubApi(
    private val apiClientProvider: GithubApiClientProvider,
    private val apiQueryProvider: GithubApiQueryProvider,
    private val systemConfig: SystemConfigProvider
) : ApiSource {

    private val client: ApolloClient = apiClientProvider
        .provide(systemConfig.retrieve().accountToken?.value)

    private val dateTimeFormat: DateTimeFormatter =
        DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")

    private val currentYear: Int =
        LocalDate.now().year

    override suspend fun isRateLimited(): Boolean = client
        .executeQuery(apiQueryProvider.rateLimitQuery())
        .let { (it?.rateLimit?.remaining ?: 0) <= 0 }

    override suspend fun getContributions(user: String): List<Contributions> {
        val startYear = getCreationYear(user)
        return (startYear..currentYear)
            .flatMap { getContributionsForYear(user, it) }
            .flatMap { it.contributionDays }
            .map { Contributions(it.date.toString(), it.contributionCount) }
    }

    private suspend fun getContributionsForYear(
        user: String,
        year: Int
    ): List<GetUserContributionsQuery.Week> = client
        .executeQuery(apiQueryProvider.getUserContributionsQuery(user, year))
        ?.user
        ?.contributionsCollection
        ?.contributionCalendar
        ?.weeks
        ?: emptyList()

    private suspend fun getCreationYear(user: String): Int = client
        .executeQuery(apiQueryProvider.getUserCreationDateQuery(user))
        ?.user
        ?.createdAt
        .let {
            dateTimeFormat.parse(it.toString()).get(ChronoField.YEAR)
        }
}