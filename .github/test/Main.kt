import kotlin.math.pow

fun main() {
    val binary: Long = 110110111
    val decimal = convertBinaryToDecimal(binary)
    println("$binary in binary = $decimal in decimal")
}

fun convertBinaryToDecimal(binaryNumber: Long): Int {
    var num = binaryNumber
    var decimalNumber = 0
    var i = 0
    var remainder: Long

    while (num.toInt() != 0) {
        remainder = num % 10
        num /= 10
        decimalNumber += (remainder * 2.0.pow(i.toDouble())).toInt()
        ++i
    }
    return decimalNumber
}
