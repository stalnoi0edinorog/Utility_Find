import java.io.File

data class Parser(val args: Array<String>){

    fun isCmd():Boolean {
        val find = args[0] == "find"

        if ("-d" !in args && find && args.all { !it.contains(Regex("""[/:<>]""")) } )
            return true

        val i = args.indexOf("-d") + 1
        val dirWindows = args[i].matches(Regex("""([A-Z]:|.)(\\|(\\([^\\/:*?<>|])+)+)""")) //Работает при условии, что в названиях директорий нет пробелов
        val dirLinux = args[i].matches(
            Regex("""((/([^\\/:*?<>|])+)(/([^\\/:*?<>|])+)*|(~/|../)([^\\/:*?<>|])+(/([^\\/:*?<>|])+)*)"""))
        val flags = args[1].matches(Regex("""(-d|-r)"""))

        if (!find || !(dirWindows || dirLinux ) || !flags)
            return false

        return true
    }

    fun r() = args[1] == ("-r")

    fun directory(): File {
        if (!Parser(args).isCmd()) //Нужна ли эта проверка в методах или это не задача парсера?
            throw CmdLineFormatException("")
        if ("-d" !in args)
            return File(".")
        val d = args.indexOf("-d")
        return File(args[d + 1].trimEnd()) //Корректно, если названия директорий не содержат пробелов
    }

    fun fileName():String {
        if (!Parser(args).isCmd())
            throw CmdLineFormatException("")
        if (directory().toString() == ".")
            return args.filter { !it.contains(Regex("""find|-r|-d|\\""")) }.joinToString(separator = " ")
        val cmd = args.indexOf(Parser(args).directory().toString())
        val result = args.takeLast(args.size - cmd - 1)
        return result.joinToString(separator = " ")
    }
}
