import java.io.File

fun main(args: Array<String>) {
    val parser = Parser(args)
    var foundFiles = listOf<String>()
    val directory = File(parser.directory())

    if (!parser.isCmd()){
        println("FIND: НЕПРАВИЛЬНЫЙ ФОРМАТ ПАРАМЕТРА")
        return
    }
    if (!directory.exists()){
        println("FIND: ДИРЕКТОРИЯ НЕ НАЙДЕНА")
        return
    }
    if (directory.isDirectory)
        foundFiles = FoundFiles( parser.fileName(), parser.r()).finder(directory)

    if (foundFiles.isEmpty()){
        println("FIND: ФАЙЛ НЕ НАЙДЕН")
        return
    }
    println(foundFiles.joinToString(separator = "\n"))
}