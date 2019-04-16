import java.io.File

class FoundFiles(fileName: String, val flagR: Boolean ) {

    private val foundFiles = mutableListOf<String>()
    private val regexFileName = fileName.toRegex()

    fun finder(directory: File): List<String> {
        for (element in directory.listFiles()) {
            if (element.name.contains(regexFileName))
                foundFiles.add(element.path)
            if (element.isDirectory && flagR)
                finder(element)
            }
        return foundFiles
    }
}