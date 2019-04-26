data class Parser(val args: Array<String>){

    var r = false
    var d = false
    var index = -1
    var directory = ""
    var fileName = ""

    init {
        if (args.size > 1){
             r = args.indexOf("-r") in 0..1
             d = args.indexOf("-d") in 0..1
        }
        when {
            (r && d) -> index = 2
            (!d && r) -> index = 0
            (d && !r) -> index = 1
        }
        when {
            args.isEmpty() -> throw CmdLineFormatException("Empty line")
            args.size > 4 -> throw CmdLineFormatException("Allowed command line size exceeded")
            !r && !d && args.size > 1 || !d && args.size > 2 -> throw CmdLineFormatException("Number of arguments exceeded")
            args.indexOf("-r") > 1 || args.indexOf("-d") > 1 -> throw CmdLineFormatException("Wrong argument order")
            d && index + 1 == args.size -> throw CmdLineFormatException("Position argument is missing")
        }
        directory = if (index == 0 || index == -1) "." else args[index]
        fileName = args[index + 1]
    }
}