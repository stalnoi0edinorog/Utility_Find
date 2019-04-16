import java.io.File

fun main(args: Array<String>) {
    try {
        val parser = Parser(args)
        var foundFiles = listOf<String>()
        val directory = File(parser.directory())

        if (!directory.exists()) {
            println("FIND: ДИРЕКТОРИЯ НЕ НАЙДЕНА")
            return
        }
        if (directory.isDirectory)
            foundFiles = FoundFiles(parser.fileName(), parser.r()).finder(directory)

        if (foundFiles.isEmpty()) {
            println("FIND: ФАЙЛ НЕ НАЙДЕН")
            return
        }
        println(foundFiles.joinToString(separator = "\n"))
    } catch (e: CmdLineFormatException) {
        println("FIND: НЕПРАВИЛЬНЫЙ ФОРМАТ ПАРАМЕТРА")
    }
}