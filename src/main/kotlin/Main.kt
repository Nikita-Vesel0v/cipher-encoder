package chucknorris

class CipherEncoder {
    private var inputsStr = mutableListOf<Char>()
    private var inputCode = listOf<String>()
    private var binaryData = mutableMapOf<Char, List<Byte>>()
    private var outputCode = mutableListOf<String>()
    private var outputStr = mutableListOf<Char>()

    fun binary() {
        inputsStr.forEach { char ->
            val binary = Integer.toBinaryString(char.code).toInt()
            val binaryStr = String.format("%07d", binary)
            binaryData[char] = binaryStr.split("").filter { it != "" }.map { it.toByte() }
        }
    }
    fun printBinary() {
        println("\nThe result of binary data:")
        inputsStr.forEach {
            println("$it = ${(binaryData[it] ?: emptyList()).joinToString("")}")
        }
    }
    fun chuckNorris() {
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
    fun printChuckNorris() {
        println("\nThe result of Chuck Norris coder:")
        println(outputCode.joinToString(" "))
    }
    fun chuckEncoder() {
        println("Input encoded string:")
        var i = 0
        var num: String // 0 == 00 or 1 == 0
        var cnt: String
        val result = mutableListOf<String>()
        var addStr: String
        inputCode = readln().split(" ")
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
        this.printEncode()
    }
    private fun printEncode() {
        println("The result:\n${outputStr.joinToString("") }")
    }
    fun chuckCoder() {
        println("Input string:")
        inputsStr = readln().toCharArray().toMutableList()
        this.binary()
        this.printBinary()
        this.chuckNorris()
        this.printChuckNorris()
    }
}
fun main() {
    val cipherEncoder = CipherEncoder()
//    cipherEncoder.chuckCoder()
    cipherEncoder.chuckEncoder()
}