package signature

fun main() {
    val s = readLine()!!.trim()
    val stars = "*".repeat(s.length + 4)
    println(stars)
    println("* $s *")
    println(stars)
}
