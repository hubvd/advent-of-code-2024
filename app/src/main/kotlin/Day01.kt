package com.github.hubvd.aoc

import kotlin.math.abs

fun main() {
    val lines = Regex("""(\d+)\s+(\d+)""").findAll(readInput(1)).map { it.groupValues.drop(1) }.toList()
    val firstCol = lines.map { it[0].toInt() }
    val secondCol = lines.map { it[1].toInt() }
    println(firstCol.sorted().zip(secondCol.sorted()).sumOf { (a, b) ->
        abs(b - a)
    })
    println(firstCol.sumOf {
        it * secondCol.count { b -> b == it }
    })
}
