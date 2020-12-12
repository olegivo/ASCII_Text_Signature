package signature

import java.io.File
import java.util.*
import kotlin.math.max

fun main() {
    print("Enter name and surname: ")
    val nameOrigin = readLine()!!.trim()
    print("Enter person's status: ")
    val statusOrigin = readLine()!!.trim()

    val nameText = Text(nameOrigin, RomanFont())
    val statusText = Text(statusOrigin, MediumFont())

    val contentLength = max(nameText.length, statusText.length) + 4
    val hBorderLength = contentLength + 4
    val hBorder = "8".repeat(hBorderLength)

    println(hBorder)
    val vBorder = "88"
    nameText.printWithVBorders(contentLength, vBorder)
    statusText.printWithVBorders(contentLength, vBorder)
    println(hBorder)
}

private fun Text.printWithVBorders(contentLength: Int, vBorder: String) {
    getPrintableStrings(contentLength).forEach {
        println("$vBorder$it$vBorder")
    }
}

class Text(origin: String, private val font: Font) {
    private val letters = origin.map { font.letters[it]!! }

    val length =
        letters.sumBy { letter ->
            letter.map { row -> row.length }.maxOrNull()!!
        } + (letters.size - 1) * font.betweenSpace.length /*spaces between letters*/

    fun getPrintableStrings(contentLength: Int): List<String> {
        val summarySpace = contentLength - length
        val leftSpaceLength = summarySpace / 2 + (summarySpace % 2)
        val rightSpaceLength = summarySpace / 2
        val leftSpace = " ".repeat(leftSpaceLength)
        val rightSpace = " ".repeat(rightSpaceLength)

        return (0 until font.height)
            .map { row ->
                val s = letters.joinToString(font.betweenSpace) { letter -> letter[row] }
                "$leftSpace$s$rightSpace"
            }
    }
}

interface Font {
    val height: Int
    val letters: Map<Char, List<String>>
    val betweenSpace: String
}

class MediumFont : Font {
    override val height: Int = 3

    override val betweenSpace: String = " "

    override val letters = mapOf(
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
        ('z' to listOf("___ ", "  / ", " /__"))
    ).let { it + it.mapKeys { entry -> entry.key.toUpperCase() } } +
        mapOf((' ' to listOf("    ", "    ", "    ")))
}


class RomanFont(filename: String = "/Users/olegivo/Downloads/Learn/roman.txt") : Font {
    override val height: Int
    override val letters: Map<Char, List<String>>
    override val betweenSpace: String = ""

    init {
        with(Scanner(File(filename))) {
            val (height, count) = nextLine().split(' ').let { it.first().toInt() to it.last().toInt() }
            val letters = (1..count)
                .map {
                    val (char, width) = nextLine().split(' ').let { it.first().first() to it.last().toInt() }
                    val list = (1..height).map {
                        nextLine().substring(0, width)
                    }
                    char to list
                }
                .associate { it }

            this@RomanFont.height = height
            this@RomanFont.letters =
                letters + mapOf(' ' to letters['a']!!.map { String(it.map { ' ' }.toCharArray()) })
        }
    }
}

/*
origin:
888888888888888888888888888888888888888888888888888888888888888888888888888888888
88  ooooo                                   .oooooo.                           88
88  `888'                                  d8P'  `Y8b                          88
88   888   .oooo.   ooo. .oo.             888      888 ooo. .oo.    .ooooo.    88
88   888  `P  )88b  `888P"Y88b            888      888 `888P"Y88b  d88' `88b   88
88   888   .oP"888   888   888            888      888  888   888  888ooo888   88
88   888  d8(  888   888   888            `88b    d88'  888   888  888    .o   88
88  o888o `Y888""8o o888o o888o            `Y8bood8P'  o888o o888o `Y8bod8P'   88
88                                                                             88
88                                                                             88
88                                                                             88
88                                _  _ _ ___                                   88
88                                |  | | |__]                                  88
88                                 \/  | |                                     88
888888888888888888888888888888888888888888888888888888888888888888888888888888888
actual:
888888888888888888888888888888888888888888888888888888888888888888888888888888888
88  ooooo                                   .oooooo.                           88
88  `888'                                  d8P'  `Y8b                          88
88   888   .oooo.   ooo. .oo.             888      888 ooo. .oo.    .ooooo.    88
88   888  `P  )88b  `888P"Y88b            888      888 `888P"Y88b  d88' `88b   88
88   888   .oP"888   888   888            888      888  888   888  888ooo888   88
88   888  d8(  888   888   888            `88b    d88'  888   888  888    .o   88
88  o888o `Y888""8o o888o o888o            `Y8bood8P'  o888o o888o `Y8bod8P'   88
88                                                                             88
88                                                                             88
88                                                                             88
88                                 _  _ _ ___                                  88
88                                 |  | | |__]                                 88
88                                  \/  | |                                    88
888888888888888888888888888888888888888888888888888888888888888888888888888888888



*/