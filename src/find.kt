
fun main(args: Array<String>) {
    val parser = Parser(args)
    var foundFiles = listOf<String>()

    if (!parser.isCmd()){
        println("FIND: НЕПРАВИЛЬНЫЙ ФОРМАТ ПАРАМЕТРА")
        return
    }
    if (!parser.directory().exists()){
        println("FIND: ДИРЕКТОРИЯ НЕ СУЩЕСТВУЕТ")
        return
    }
    if (parser.directory().isDirectory)
        foundFiles = FoundFiles( parser.fileName(), parser.r()).finder(parser.directory())

    if (foundFiles.isEmpty()){
        println("FIND: ФАЙЛ НЕ НАЙДЕН")
        return
    }

    println(foundFiles.joinToString(separator = "\n"))
}