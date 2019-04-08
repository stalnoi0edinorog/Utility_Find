
fun main(args: Array<String>) {
    val cmdLine = args.joinToString(separator = " ")
    val fileName = Parser(args).fileName()
    val flagR = Parser(args).r()
    var foundFiles = listOf<String>()

    if (!cmdLine.matches(Regex(
            """find\s((-r\s)?-d\s[A-Z]:((\\)|(\\([^\\/:*?<>|]+))+))\s([^\\/:?<>|]+)"""))
        && !cmdLine.matches(Regex("""find\s(-r\s)?([^\\/:?<>|(-d)])+"""))) {
        println("FIND: НЕПРАВИЛЬНЫЙ ФОРМАТ ПАРАМЕТРА")
        return
    }
    try {
        val directory = Parser(args).directory()
        if (directory.isDirectory) {
            foundFiles = FoundFiles().finder(directory, fileName, flagR)
        }
    } catch (e: Exception){
        println("FIND: ДИРЕКТОРИЯ НЕ СУЩЕСТВУЕТ")
        return
    }
    if (foundFiles.isEmpty()){
        println("FIND: ФАЙЛ НЕ НАЙДЕН")
        return
    }

    println(foundFiles.joinToString(separator = "\n"))



}