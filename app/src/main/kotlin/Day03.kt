package com.github.hubvd.aoc

fun main() {
    println(
        Regex("""mul\((\d+),(\d+)\)""").findAll(readInput(3))
            .sumOf { it.groupValues[1].toLong() * it.groupValues[2].toLong() }
    )
    var `do` = true
    println(
        Regex("""mul\((\d+),(\d+)\)|do\(\)|don't\(\)""").findAll(readInput(3))
            .onEach {
                when (it.value) {
                    "do()" -> `do` = true
                    "don't()" -> `do` = false
                }
            }
            .sumOf {
                if (`do` && it.value.startsWith("mul")) it.groupValues[1].toLong() * it.groupValues[2].toLong()
                else 0
            }
    )
}
