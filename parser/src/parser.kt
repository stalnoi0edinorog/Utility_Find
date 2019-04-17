data class Parser(val args: Array<String>){

    val r = if (args.size > 1) args.indexOf("-r") in 0..1 else false
    val d = if (args.size > 1) args.indexOf("-d") in 0..1 else false
    val index = if (r && d) 2 else {if (!d && r) 0 else {if (!d && !r) -1 else 1}}

    val exception = when {
        args.isEmpty() -> throw CmdLineFormatException("Empty line")
        args.size > 4 -> throw CmdLineFormatException("Allowed command line size exceeded")
        !r && !d && args.size > 1 || !d && args.size > 2 -> throw CmdLineFormatException("Number of arguments exceeded")
        args.indexOf("-r") > 1 || args.indexOf("-d") > 1 -> throw CmdLineFormatException("Wrong argument order")
        d && index + 1 == args.size -> throw CmdLineFormatException("Position argument is missing")
        index in -1..0 && args[index + 1].contains(Regex("""[\\/]""")) -> throw CmdLineFormatException("Expected file name")
        else -> 1
    }

    val directory = if (index == 0 || index == -1) "." else args[index]
    val fileName = args[index + 1]

    fun r() = r

    fun directory() = directory

    fun fileName() = fileName
}