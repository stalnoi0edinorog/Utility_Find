import java.io.File
import java.lang.Exception
import java.lang.StringBuilder

data class Parser(val args: Array<String>){

    fun r() = args.contains("-r")

    fun directory(): File {
        val exceptionOfExistence = Exception()
        for (element in args) {
            if (element.contains(Regex("""^[CD]:\\""")) && !File(element).exists())
                throw exceptionOfExistence
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
