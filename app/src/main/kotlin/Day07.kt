package com.github.hubvd.aoc

import kotlin.math.pow

fun main() {
    val numRe = Regex("\\d+")

    fun eval(nums: List<Long>, operators: List<Char>): Long {
        val queue = operators.reversed().toMutableList()
        var result = nums.first()

        for (num in nums.drop(1)) {
            result = when (queue.removeLast()) {
                '0' -> result * num
                '1' -> result + num
                '2' -> "$result$num".toLong()
                else -> error("")
            }
        }

        return result
    }

    fun part(operators: Int) = readInput(7).lineSequence()
        .map {
            val nums = numRe.findAll(it).map { it.value.toLong() }.toList()
            nums.first() to nums.drop(1)
        }.filter { (result, operands) ->
            val slots = operands.size - 1
            val possibilities = operators.toDouble().pow(slots).toInt()
            for (bin in 0 until possibilities) {
                val str = bin.toString(operators).padStart(slots, '0')
                if (eval(operands, str.toList()) == result) {
                    return@filter true
                }
            }
            false
        }
        .sumOf { it.first }

    println(part(2))
    println(part(3))

}
