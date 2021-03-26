package br.com.urltester.utils

fun String.isNumber(): Boolean {
    try {
        this.toDouble()
    } catch (e: NumberFormatException) {
        return false
    }

    return true
}

fun String.inc(): String {
    this.toIntOrNull()?.let {
        return (it + 1).toString();
    }

    return this.plus("a")
}

fun String.dec(): String {
    this.toIntOrNull()?.let {
        return (it - 1).toString();
    }

    return this.dropLast(1)
}
