package chucknorris

const val ZERO: Byte = 0
const val ONE: Byte = 1

class CipherEncoder {
    private var inputData = mutableListOf<Char>()
    private var binaryData = mutableMapOf<Char, List<Byte>>()
    private var chuckNorris = mutableListOf<String>()
    init {
        println("Input string:")
        inputData = readln().toCharArray().toMutableList()
    }

    fun binary() {
        inputData.forEach { char ->
            val binary = Integer.toBinaryString(char.code).toInt()
            val binaryStr = String.format("%07d", binary)
            binaryData[char] = binaryStr.split("").filter { it != "" }.map { it.toByte() }
        }
    }
    fun printBinary() {
        println("\nThe result of binary data:")
        inputData.forEach {
            println("$it = ${(binaryData[it] ?: emptyList()).joinToString("")}")
        }
    }
    fun chuckNorris() {
        var lenSeq: Int
        val toIndex = inputData.size * 7
        val binaryCode = buildString { inputData.forEach{ append(binaryData[it]?.joinToString("") ?: "" )} }

        var fromIndex = 0
        while (fromIndex < toIndex) {
            lenSeq = if (binaryCode[fromIndex] == '0') {
                chuckNorris.add("00")
                binaryCode.substring(fromIndex, toIndex).indexOfFirst { it == '1' }
            } else {
                chuckNorris.add("0")
                binaryCode.substring(fromIndex, toIndex).indexOfFirst { it == '0' }
            }
            if (lenSeq == -1) {
                lenSeq = toIndex - fromIndex
            }
            chuckNorris.add("0".repeat(lenSeq))
            fromIndex += lenSeq
        }

    }
    fun printChuckNorris() {
        println("\nThe result of Chuck Norris coder:")
        println(chuckNorris.joinToString(" "))
    }
}
fun main() {
    val cipherEncoder = CipherEncoder()
    cipherEncoder.binary()
    cipherEncoder.printBinary()
    cipherEncoder.chuckNorris()
    cipherEncoder.printChuckNorris()
}