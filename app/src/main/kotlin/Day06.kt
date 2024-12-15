package com.github.hubvd.aoc

fun main() {
    val grid = readInput(6)
        .toGrid()
        .toMutableMap()

    val startingPosition = grid.entries.find { it.value == '^' }!!.key

    class Steps(var position: Point, var direction: Point, var grid: Map<Point, Char>) {

        val states = LinkedHashSet<Pair<Point, Point>>().also { it.add(position to direction) }
        val visited = HashSet<Point>().also { it.add(position) }

        fun nextDirection() = when (direction) {
            Point.UP -> Point.RIGHT
            Point.RIGHT -> Point.DOWN
            Point.DOWN -> Point.LEFT
            else -> Point.UP
        }

        private fun save() {
            if (!states.add(position to direction)) {
                error("loop")
            }
            visited.add(position)
        }

        fun next(): Boolean {
            val next = position + direction

            when (this.grid[next]) {
                null -> {
                    return true
                }
                '#' -> {
                    direction = nextDirection()
                    save()
                }

                '.', '^' -> {
                    position = next
                    save()
                }
            }
            return false
        }


    }

    val steps = Steps(startingPosition, Point.UP, grid)
    while (!steps.next()) {
        // empty
    }
    println(steps.visited.size)
    val path = steps.states

    val loops = HashSet<Point>()
    val two = path.zipWithNext().count { (current, next) ->
        if (next.first in loops) {
            return@count false
        }
        grid[next.first] = '#'
        val steps = Steps(startingPosition, Point.UP, grid)
        var loop = false
        try {
            while (!steps.next()) {
                // empty
            }
        } catch (e: IllegalStateException) {
            loop = true
            loops.add(next.first)
        }
        grid[next.first] = '.'
        loop
    }

    println(two)
}
