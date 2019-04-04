import java.io.File

    fun finder(directory: File, fileName: String, flagR: Boolean) {

        for (element in directory.listFiles()) {
            if (element.toString().contains(fileName.toRegex()))
                println(element.toString())

        if (element.isDirectory && flagR)
            finder(element, fileName, flagR)
        }
}