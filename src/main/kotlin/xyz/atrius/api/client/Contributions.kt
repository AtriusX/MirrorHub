package xyz.atrius.api.client

/**
 * @author Atri
 *
 * Generic contribution data for all associated services.
 *
 * @param date The commit date of the contributions.
 * @param commitCount The number of contributions given for this date.
 */
data class Contributions(
    val date: String,
    val commitCount: Int
)
