package xyz.atrius.logging.config

import org.koin.core.annotation.Single

/**
 * @author Atri
 *
 * Template constants for use in [LoggerConfig]'s logger format.
 */
@Single
open class LoggerTemplate {

    /**
     * Corresponds to the message's log level.
     */
    open val LEVEL = "%L%"

    /**
     * Corresponds to the message's originating class.
     */
    open val ORIGINATOR = "%O%"

    /**
     * Corresponds to the message's log time.
     */
    open val TIME = "%T%"

    /**
     * Corresponds to the logging event's message.
     */
    open val MESSAGE = "%M%"
}