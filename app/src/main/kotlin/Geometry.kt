package com.github.hubvd.aoc

data class Point(val x: Int, val y: Int) {
    companion object {
        val UP = Point(0, -1)
        val DOWN = Point(0, 1)
        val RIGHT = Point(1, 0)
        val LEFT = Point(-1, 0)
    }
}

private val directions = listOf(
    Point.LEFT,
    Point.RIGHT,
    Point.UP,
    Point.DOWN
)

private val diagonals = listOf(
    Point(-1, -1),
    Point(-1, 1),
    Point(1, 1),
    Point(1, -1),
)

private val allDirections = directions + diagonals

fun directions(includeDiagonals: Boolean = true): List<Point> = if (includeDiagonals) allDirections else directions

operator fun Point.plus(other: Point) = copy(x = x + other.x, y = y + other.y)

fun String.toGrid(): Map<Point, Char> = lineSequence().mapIndexed { y, line ->
    line.mapIndexed { x, c -> Point(x, y) to c }
}.flatten().toMap()
