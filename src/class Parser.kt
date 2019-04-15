import java.lang.StringBuilder

data class Parser(val args: Array<String>){

    fun isCmd():Boolean {
        val pArgs = Parser(args)
        val find = args[0] == "find"

        if (pArgs.fileName().contains(Regex("""[/:"\\<>]""")))
            return false

        if ("-d" !in args && find)
            return true

        val dirWindows = pArgs.directory().matches(
            Regex(""""?([A-Z]:|\.)(\\|(\\([^\\/:*?<>|]+))+)"?"""))
        val dirLinux = pArgs.directory().matches(
            Regex(""""?(/([^\\/:*?<>|])+)+"?|"?(~|\.\.?)(/[^\\/:*?<>|]+)+"?"""))
        val flags = args[1].matches(Regex("""(-d|-r)"""))
        val space = pArgs.directory()[0] != pArgs.directory().last()
                && pArgs.directory().split(" ").size > 1

        if (!find || !(dirWindows || dirLinux) || !flags || space)
            return false
        return true
    }

    fun r() = args[1] == ("-r")

    fun directory(): String {
        if ("-d" !in args)
            return "."

        val indexOfDir = args.indexOf("-d") + 1

        if (args.all { !it.contains("\"") })
            return args[indexOfDir]

        val result = StringBuilder()
        for (element in indexOfDir until args.size){
            result.append(args[element])
            if (args[element].last() != '\"'){
                result.append(" ")
                continue
            }
            break
        }
        return result.toString()
    }

    fun fileName():String {

        if (Parser(args).directory() == ".")
            return args.filter {it != args[0] && it != "-r"}.joinToString(separator = " ")

        if (!Parser(args).directory().contains("\""))
            return  args.filter { args.indexOf(it) > args.indexOf("-d") + 1 }.joinToString(separator = " ")

        val index = args.indexOf(Parser(args).directory().split(" ").last())
        return args.filter { args.indexOf(it) > index }.joinToString(separator = " ")
    }
}
