package com.github.hubvd.aoc

private fun List<Int>.isSafe() = zipWithNext().all { it.second - it.first in 1..3 } ||
        zipWithNext().all { it.first - it.second in 1..3 }

fun main() {
    val nums = readInput(2).trim().lines().map { it.split(" ").map { it.toInt() } }
    println(nums.count { it.isSafe() })
    println(nums.count {
        it.isSafe() || it.indices.any { i -> it.toMutableList().apply { removeAt(i) }.isSafe() }
    })
}
