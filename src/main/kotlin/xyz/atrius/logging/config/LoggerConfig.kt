package xyz.atrius.logging.config

/**
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
    val highestLoggingLevel: LogLevel = LogLevel.INFO,
    val logLevelColors: Map<LogLevel, TextColor> = mapOf(
        LogLevel.FATAL to TextColor.RED,
        LogLevel.ERROR to TextColor.BRIGHT_RED,
        LogLevel.WARN to TextColor.YELLOW,
        LogLevel.INFO to TextColor.GREEN,
        LogLevel.DEBUG to TextColor.CYAN,
        LogLevel.TRACE to TextColor.MAGENTA
    ),
    val originatorColor: TextColor? = TextColor.BRIGHT_YELLOW,
    val timestampColor: TextColor? = TextColor.BRIGHT_BLUE,
    val messageColor: TextColor? = TextColor.GREEN,
    val levelPadding: Int = 7,
    val originatorPadding: Int = 25,
    val paddingCharacter: Char = '.',
    val loggerFormat: String = "[%L% - %O%] -> %T%: %M%"
)

/**
 * Basic default configuration object.
 */
val defaultConfig = LoggerConfig()
