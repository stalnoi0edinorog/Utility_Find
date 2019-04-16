data class Parser(val args: Array<String>){

    val r = if (args.size > 1) args[0] == "-r" || args[1] == "-r" else false
    val d = if (args.size > 1) args[0] == "-d" || args[1] == "-d" else false
    val index = if (r && d) 2 else {if (!d && r) 0 else {if (!d && !r) -1 else 1}}

    val exception1 = if (args.isEmpty()) throw CmdLineFormatException("Empty line") else 1
    val exception2 = if (!r && !d && args.size > 1 || !d && args.size > 2)
        throw CmdLineFormatException("Number of positional arguments exceeded") else 1
    val exception3 = if (args.indexOf("-r") > 1 || args.indexOf("-d") > 1)
        throw CmdLineFormatException("Wrong flag order") else 1
    val exception4 = if (args.size > 4)
        throw CmdLineFormatException("Allowed command line size exceeded") else 1
    val exception5 = if (d && index + 1 == args.size)
        throw CmdLineFormatException("Position argument is missing") else 1
    val exception6 = if ((index == 0 || index == -1) && args[index + 1].contains(Regex("""[\\/]""")))
        throw CmdLineFormatException("Expected file name") else 1

    val directory = if (index == 0 || index == -1) "." else args[index]
    val fileName = args[index + 1]

    fun r() = r

    fun directory() = directory

    fun fileName() = fileName
}