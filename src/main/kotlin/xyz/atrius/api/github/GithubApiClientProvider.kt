package xyz.atrius.api.github

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.http.HttpHeader
import org.koin.core.annotation.Single
import xyz.atrius.api.client.ApiClientProvider

/**
 * @author Atri
 *
 * [ApiClientProvider] for the main GitHub API.
 */
@Single
class GithubApiClientProvider : ApiClientProvider<ApolloClient> {

    override val apiEndpointRoot: String =
        "https://api.github.com/graphql"

    override fun provide(authorization: String?): ApolloClient {
        val headers = authorization?.let {
            listOf(HttpHeader("Authorization", "Bearer $authorization"))
        }
        return ApolloClient.Builder()
            .serverUrl(apiEndpointRoot)
            .httpHeaders(headers)
            .build()
    }
}