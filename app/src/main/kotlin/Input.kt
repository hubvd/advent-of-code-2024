package com.github.hubvd.aoc

fun readInput(day: Int): String =
    object {}.javaClass.getResource("/Day${day.toString().padStart(2, '0')}.txt")
        .readText()