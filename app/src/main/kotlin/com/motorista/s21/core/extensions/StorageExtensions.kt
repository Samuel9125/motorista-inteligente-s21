package com.motorista.s21.core.extensions

/**
 * Extensões para manipulação de valores de armazenamento
 */
fun Long.toFormattedBytes(): String {
    return when {
        this <= 0L -> "0 B"
        this < 1024L -> "$this B"
        this < 1024L * 1024L -> String.format("%.2f KB", this.toDouble() / 1024)
        this < 1024L * 1024L * 1024L -> String.format("%.2f MB", this.toDouble() / (1024 * 1024))
        this < 1024L * 1024L * 1024L * 1024L -> String.format("%.2f GB", this.toDouble() / (1024 * 1024 * 1024))
        else -> String.format("%.2f TB", this.toDouble() / (1024 * 1024 * 1024 * 1024))
    }
}

fun Int.toFormattedBytes(): String = this.toLong().toFormattedBytes()
