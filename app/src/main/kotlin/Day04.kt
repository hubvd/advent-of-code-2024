package com.github.hubvd.aoc

fun main() {
    val grid = readInput(4).trim().toGrid()

    fun Sequence<Point>.join() = joinToString("") { grid.getOrDefault(it, '%').toString() }

    println(grid.keys.sumOf { point ->
        directions().map { dir ->
            generateSequence(point) { it + dir }.take(4).join()
        }.count { it == "XMAS" }
    })

    println(grid.keys.count { point ->
        val diag1 = sequenceOf(
            point,
            Point(point.x + 1, point.y + 1),
            Point(point.x + 2, point.y + 2),
        ).join()

        val diag2 = sequenceOf(
            Point(point.x, point.y + 2),
            Point(point.x + 1, point.y + 1),
            Point(point.x + 2, point.y),
        ).join()

        (diag1 == "MAS" || diag1 == "SAM") && (diag2 == "MAS" || diag2 == "SAM")
    })

}
