package xyz.atrius.data.config

import com.sksamuel.hoplite.*
import com.sksamuel.hoplite.fp.valid
import java.io.File
import kotlin.io.path.inputStream

/**
 * @author Atri
 *
 * Hoplite [PropertySource] for reading config changes from a remove file location.
 *
 * @property location The location of the remote file source.
 */
class FilePropertySource(
    private val location: String
) : PropertySource {

    override fun node(context: PropertySourceContext): ConfigResult<Node> {
        val file = File(location)
        // If the file does not exist, just use the internal config
        if (!file.exists()) {
            return Undefined.valid()
        }
        // Pull data from the external file
        val path = file.toPath()
        val input = path.inputStream()
        // Read into live config
        return context.parsers
            .locate(file.extension)
            .map { it.load(input, path.toString()) }
    }

    override fun source(): String = location
}