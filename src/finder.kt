import java.io.File

class FoundFiles(fileName: String, val flagR: Boolean ) {

    private val foundFiles = mutableListOf<String>()
    private val regexFileName = fileName.toRegex()

    fun finder(directory: File): List<String> {

        for (element in directory.listFiles()) {
            if (regexFileName in element.toString())
                foundFiles.add(element.toString())

            if (element.isDirectory && flagR)
                finder(element)
            }
        return foundFiles
    }
}

