package com.github.hubvd.aoc

fun main() {
    val (rules, updates) = readInput(5).trimEnd().split("\n\n")
    val map = rules.lines()
        .map { it.split('|').map { it.toInt() } }
        .map { it[0] to it[1] }
        .toList()

    val cmp = Comparator { a: Int, b: Int ->
        when {
            map.contains(a to b) -> -1
            map.contains(b to a) -> 1
            else -> 0
        }
    }

    val one = updates.lineSequence()
        .map { it.split(',').map { it.toInt() } }
        .filter { it == it.sortedWith(cmp) }
        .sumOf { it[it.lastIndex / 2] }

    val two = updates.lineSequence()
        .map { it.split(',').map { it.toInt() } }
        .filter { it != it.sortedWith(cmp) }
        .map { it.sortedWith(cmp) }
        .sumOf { it[it.lastIndex / 2] }

    println(one)
    println(two)
}
