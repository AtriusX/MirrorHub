package xyz.atrius.logging.config

/**
 * @author Atri
 *
 * Provides a set of ANSI color codes for use in application terminals.
 */
@Suppress("unused")
enum class TextColor(private val color: String) {
    RED("\u001b[31m"),
    GREEN("\u001b[32m"),
    YELLOW("\u001b[33m"),
    BLUE("\u001b[34m"),
    MAGENTA("\u001b[35m"),
    CYAN("\u001b[36m"),
    BRIGHT_RED("\u001b[31;1m"),
    BRIGHT_GREEN("\u001b[32;1m"),
    BRIGHT_YELLOW("\u001b[33;1m"),
    BRIGHT_BLUE("\u001b[34;1m"),
    BRIGHT_MAGENTA("\u001b[35;1m"),
    BRIGHT_CYAN("\u001b[36;1m"),
    RESET("\u001b[0m")
    ;

    /**
     * Shallow formatting support. Will format a given message with the
     * requested color, and reset the string directly after. This likely
     * will not work for nested formats.
     *
     * @param input The object to color.
     *
     * @return The formatted string.
     */
    fun format(input: Any?): String {
        return "${color}$input${RESET.color}"
    }
}