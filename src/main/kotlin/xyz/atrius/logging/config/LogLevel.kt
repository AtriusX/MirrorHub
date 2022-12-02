package xyz.atrius.logging.config

/**
 * The supported logging levels for the application's internal logging framework.
 */
@Suppress("unused")
enum class LogLevel(val level: Int) {

    /**
     * All messages will be disabled at this logging level.
     */
    OFF(0),

    /**
     * Used for events that will terminate an application.
     */
    FATAL(1),

    /**
     * Used for events that result in errors that don't always result in program termination.
     */
    ERROR(2),

    /**
     * Used for events that are considered suspicious but not otherwise serious.
     */
    WARN(3),

    /**
     * Default logging level, used for general information reporting.
     */
    INFO(4),

    /**
     * Elevated logging messages for more verbose reporting.
     */
    DEBUG(5),

    /**
     * Highly verbose reporting.
     */
    TRACE(6)
    ;

    companion object {

        /**
         * Checks if a given log message can be sent at the current [systemLevel].
         * If the value of the log message ranks higher than the system level, then
         * this will return false.
         *
         * @param systemLevel The systems current logging level.
         * @param level The level of a potential logging event.
         *
         * @return True if the logging level is lower or equal to the system level.
         */
        fun permitLogging(
            systemLevel: LogLevel,
            level: LogLevel
        ): Boolean = (level.level != 0) && (level <= systemLevel)
    }
}