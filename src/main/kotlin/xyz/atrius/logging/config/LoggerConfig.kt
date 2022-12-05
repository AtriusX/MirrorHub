package xyz.atrius.logging.config

/**
 * @author Atri
 *
 * General configuration object for the logger system.
 *
 * @property highestLoggingLevel The highest permitted logging event level for all messages from this logger.
 * @property logLevelColors The color associations for each logging level. Omitted levels default to white.
 * @property originatorColor The color associated with the logger event's originator name.
 * @property timestampColor The color associated with the logger event's timestamp.
 * @property messageColor The color associated with the logged message.
 * @property levelPadding How many placeholder characters to append to the level name.
 * @property originatorPadding How many characters to append to the originator name.
 * @property paddingCharacter The character used for name padding.
 * @property loggerFormat The format to use for the logger message, uses templates provided in [LoggerTemplate].
 */
data class LoggerConfig(
    val highestLoggingLevel: LogLevel,
    val logLevelColors: Map<LogLevel, TextColor>,
    val originatorColor: TextColor?,
    val timestampColor: TextColor?,
    val messageColor: TextColor?,
    val levelPadding: Int,
    val originatorPadding: Int,
    val paddingCharacter: String,
    val loggerFormat: String
)
