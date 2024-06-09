package chucknorris

class ChuckNoris {
    private var inputsStr = mutableListOf<Char>()
    private var inputCode = listOf<String>()
    private var binaryData = mutableMapOf<Char, List<Byte>>()
    private var outputCode = mutableListOf<String>()
    private var outputStr = mutableListOf<Char>()

    fun menu() {
        while (true) {
            println("Please input operation (encode/decode/exit):")
            val action = readln()
            when (action.lowercase()) {
                "encode" -> encode()
                "decode" -> decode()
                "exit" ->  break
                else -> { println("There is no '$action' operation\n"); continue }
            }
        }
        println("Bye!")

    }
    private fun encode() {
        outputCode = mutableListOf()
        println("Input string:")
        inputsStr = readln().toCharArray().toMutableList()
        this.binaryMap()
        this.strToCode()
        this.printEncode()
    }
    private fun decode() {
        outputStr = mutableListOf()
        println("Input encoded string:")
        inputCode = readln().split(" ")
        if (this.checkInputCode()) {
            this.codeToString()
            this.printDecode()
        }
    }
    private fun checkInputCode(): Boolean {
        var check = true
        var lenString = 0
        if (inputCode.size % 2 != 0) check = false

        inputCode.forEachIndexed { index, code ->
            if (code.any { it != '0' } ) check = false
            if (index % 2 == 0 && code.length > 2) check = false
            if (index % 2 == 1) lenString += code.length
        }
        if (lenString % 7 != 0) check = false

        if (!check) println("Encoded string is not valid.\n")
        return check
    }
    private fun binaryMap() {
        inputsStr.forEach { char ->
            val binary = Integer.toBinaryString(char.code).toInt()
            val binaryStr = String.format("%07d", binary)
            binaryData[char] = binaryStr.split("").filter { it != "" }.map { it.toByte() }
        }
    }
    private fun strToCode() {
        var lenSeq: Int
        val toIndex = inputsStr.size * 7
        val binaryCode = buildString { inputsStr.forEach{ append(binaryData[it]?.joinToString("") ?: "" )} }

        var fromIndex = 0
        while (fromIndex < toIndex) {
            lenSeq = if (binaryCode[fromIndex] == '0') {
                outputCode.add("00")
                binaryCode.substring(fromIndex, toIndex).indexOfFirst { it == '1' }
            } else {
                outputCode.add("0")
                binaryCode.substring(fromIndex, toIndex).indexOfFirst { it == '0' }
            }
            if (lenSeq == -1) {
                lenSeq = toIndex - fromIndex
            }
            outputCode.add("0".repeat(lenSeq))
            fromIndex += lenSeq
        }
    }
    private fun printEncode() {
        println("Encoded string:")
        println(outputCode.joinToString(" "))
        println()
    }
    private fun codeToString() {
        var i = 0
        var num: String // 0 == 00 or 1 == 0
        var cnt: String
        val result = mutableListOf<String>()
        var addStr: String

        repeat(inputCode.size / 2) {
            num = inputCode[i++]
            cnt = inputCode[i++]
            addStr = if (num == "00") "0".repeat(cnt.length)
            else "1".repeat(cnt.length)
            result.add(addStr)
        }
        val str = result.joinToString("")

        i = 0
        repeat(str.length / 7) {
            val charBin = str.substring(i, i + 7)
            val char = Integer.parseInt(charBin, 2)
            outputStr.add(char.toChar())
            i += 7
        }
    }
    private fun printDecode() {
        println("Decoded string:\n${ outputStr.joinToString("") }\n")
    }

}
fun main() {
    val cipherEncoder = ChuckNoris()
    cipherEncoder.menu()
}