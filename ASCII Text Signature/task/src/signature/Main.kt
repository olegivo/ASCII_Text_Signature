package signature

import kotlin.math.max

fun main() {
    print("Enter name and surname: ")
    val nameOrigin = readLine()!!.trim()
    print("Enter person's status: ")
    val status = readLine()!!.trim()

    val name = nameOrigin.map { letters[it.toLowerCase()]!! }
    val nameLength = name.sumBy { letter -> letter.map { row -> row.length }.maxOrNull()!! } + (name.size - 1)// * 4
    val contentLength = max(status.length, nameLength) + 4

    val hBorderLength = contentLength + 2
    val hBorder = "*".repeat(hBorderLength)

    val nameSummarySpace = contentLength - nameLength
    val nameLeftSpaceLength = nameSummarySpace / 2
    val nameRightSpaceLength = nameSummarySpace / 2 + (nameSummarySpace % 2)
    val nameLeftSpace = " ".repeat(nameLeftSpaceLength)
    val nameRightSpace = " ".repeat(nameRightSpaceLength)

    println(hBorder)
    (0..2)
        .map { row ->
            name.joinToString(" ") { letter -> letter[row] }
        }
        .forEach {
            println("*$nameLeftSpace$it$nameRightSpace*")
        }

    val statusSummarySpace = contentLength - status.length
    val statusLeftSpaceLength = statusSummarySpace / 2
    val statusRightSpaceLength = statusSummarySpace / 2 + (statusSummarySpace % 2)
    val statusLeftSpace = " ".repeat(statusLeftSpaceLength)
    val statusRightSpace = " ".repeat(statusRightSpaceLength)
    println("*$statusLeftSpace$status$statusRightSpace*")
    println(hBorder)
}

val letters = mapOf(
    ('a' to listOf("____", "|__|", "|  |")),
    ('b' to listOf("___ ", "|__]", "|__]")),
    ('c' to listOf("____", "|   ", "|___")),
    ('d' to listOf("___ ", "|  \\", "|__/")),
    ('e' to listOf("____", "|___", "|___")),
    ('f' to listOf("____", "|___", "|   ")),
    ('g' to listOf("____", "| __", "|__]")),
    ('h' to listOf("_  _", "|__|", "|  |")),
    ('i' to listOf("_", "|", "|")),
    ('j' to listOf(" _", " |", "_|")),
    ('k' to listOf("_  _", "|_/ ", "| \\_")),
    ('l' to listOf("_   ", "|   ", "|___")),
    ('m' to listOf("_  _", "|\\/|", "|  |")),
    ('n' to listOf("_  _", "|\\ |", "| \\|")),
    ('o' to listOf("____", "|  |", "|__|")),
    ('p' to listOf("___ ", "|__]", "|   ")),
    ('q' to listOf("____", "|  |", "|_\\|")),
    ('r' to listOf("____", "|__/", "|  \\")),
    ('s' to listOf("____", "[__ ", "___]")),
    ('t' to listOf("___", " | ", " | ")),
    ('u' to listOf("_  _", "|  |", "|__|")),
    ('v' to listOf("_  _", "|  |", " \\/ ")),
    ('w' to listOf("_ _ _", "| | |", "|_|_|")),
    ('x' to listOf("_  _", " \\/ ", "_/\\_")),
    ('y' to listOf("_   _", " \\_/ ", "  |  ")),
    ('z' to listOf("___ ", "  / ", " /__")),
    (' ' to listOf("    ", "    ", "    "))
)