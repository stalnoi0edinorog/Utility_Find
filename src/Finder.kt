import java.io.File

class FoundFiles {
    private val foundFiles = mutableListOf<String>()

    fun finder(directory: File, fileName: String, flagR: Boolean): List<String> {

        for (element in directory.listFiles()) {
            if (element.toString().contains(fileName.toRegex()))
                foundFiles.add(element.toString())
            if (element.isDirectory && flagR)
                finder(element, fileName, flagR)
        }
        return foundFiles
    }
}