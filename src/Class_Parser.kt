import java.io.File
import java.lang.Exception
import java.lang.StringBuilder

data class Parser(val args: Array<String>){

    fun isCmd(): Boolean {
        val cmdLine = args.joinToString(separator = " ")
        return cmdLine
            .matches(Regex(
                """find\s(-r|-d\s[A-Z]:(\\[^\\/:*?<>|]+)+|-r\s-d\s[A-Z]:(\\[^\\/:*?<>|]+)+)\s([^\\/:?<>|])+"""))
                || cmdLine
            .matches(Regex("""find\s(-r\s)?([^\\/:?<>|(-d)])+"""))
    }

    fun r() = args.contains("-r")

    fun directory(): File {
        for (element in args) {
            if (element.contains(Regex("""^[CD]:\\""")) && !File(element).exists())
                throw Exception()
            if (element.contains(Regex("""^[CD]:\\""")) && File(element).exists())
                return File(element)
        }
        return File(".")
    }

    fun fileName():String {
        val result = StringBuilder()
        for (index in args.size -1 downTo 0){
            if (args[index].contains(Regex("""^[CD]:\\|-r|-d|find""")))
                break
            result.append(args[index])
            if (result.length > 1)
                result.append(" ")
        }
        return result.trimEnd().filter { it != '*'}.split(" ").reversed().joinToString ( separator = " " )

    }
}
