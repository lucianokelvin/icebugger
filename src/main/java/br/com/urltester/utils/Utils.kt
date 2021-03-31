package br.com.urltester.utils

fun String.isNumber(): Boolean {
    try {
        this.toDouble()
    } catch (e: NumberFormatException) {
        return false
    }

    return true
}


