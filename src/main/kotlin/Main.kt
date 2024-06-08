package chucknorris

class CipherEncoder {
    private var inputData = charArrayOf()
    init {
        println("Input string:")
        inputData = readln().toCharArray()
    }
    fun printInputData() {
        println(inputData.joinToString(" "))
    }
}
fun main() {
    val cipherEncoder = CipherEncoder()
    cipherEncoder.printInputData()
}