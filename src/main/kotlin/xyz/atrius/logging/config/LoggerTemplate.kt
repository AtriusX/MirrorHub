package xyz.atrius.logging.config

/**
 * Template constants for use in [LoggerConfig]'s logger format.
 */
object LoggerTemplate {

    /**
     * Corresponds to the message's log level.
     */
    const val LEVEL = "%L%"

    /**
     * Corresponds to the message's originating class.
     */
    const val ORIGINATOR = "%O%"

    /**
     * Corresponds to the message's log time.
     */
    const val TIME = "%T%"

    /**
     * Corresponds to the logging event's message.
     */
    const val MESSAGE = "%M%"
}