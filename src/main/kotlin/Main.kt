package chucknorris

class CipherEncoder {
    private var inputData = mutableListOf<Char>()
    private var binaryData = mutableMapOf<Char, String>()
    init {
        println("Input string:")
        inputData = readln().toCharArray().toMutableList()
    }

    fun binary() {
        inputData.forEach {
            val binary = Integer.toBinaryString(it.code)
            binaryData[it] = String.format("%07d", binary.toInt())
        }
    }
    fun printBinary() {
        println("\nThe result:")
        inputData.forEach {
            println("$it = ${binaryData[it]}")
        }
    }
}
fun main() {
    val cipherEncoder = CipherEncoder()
    cipherEncoder.binary()
    cipherEncoder.printBinary()
}