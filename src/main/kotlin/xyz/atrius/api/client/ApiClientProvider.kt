package xyz.atrius.api.client

/**
 * @author Atri
 *
 * Provides an API Client that can be used to access a given [ApiSource]. In the case of GitHub our
 * use for this would be to provide an Apollo client instance to interact with GitHub's GraphQL API.
 *
 * A provider is meant to exist for all supported service domains. Multiple clients may be generated
 * by a single provider if they all share the same domain.
 *
 * @property T The API client type.
 */
interface ApiClientProvider<T> {

    /**
     * The API endpoint for our generated clients.
     */
    val apiEndpointRoot: String

    /**
     * Provides a generated client template with the given authorization.
     *
     * @param authorization The bearer token used for logging into a given account.
     *
     * @return An API client associated to the provided endpoint and authorization.
     */
    fun provide(authorization: String?): T
}