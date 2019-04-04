

fun main(args: Array<String>) {
    val fileName = Parser(args).fileName()
    val flagR = Parser(args).r()
    if (!Parser(args).isCmd())
        println("FIND: НЕПРАВИЛЬНЫЙ ФОРМАТ ПАРАМЕТРА")

    try {
        val directory = Parser(args).directory()
        if (directory.isDirectory)
            finder(directory, fileName, flagR)
    } catch (e: Exception){
        println("FIND: ДИРЕКТОРИЯ НЕ СУЩЕСТВУЕТ")
    }

   // ??? -> println("FIND: ФАЙЛ НЕ НАЙДЕН")


}