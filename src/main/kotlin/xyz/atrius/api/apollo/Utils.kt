package xyz.atrius.api.apollo

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.Query

/**
 * @author Atri
 *
 * Shorthand function for quickly executing a query and retrieving query data.
 *
 * @param query The query to execute
 *
 * @return The resulting query data.
 */
suspend fun <D : Query.Data> ApolloClient.executeQuery(
    query: Query<D>
): D? = query(query)
    .execute()
    .data