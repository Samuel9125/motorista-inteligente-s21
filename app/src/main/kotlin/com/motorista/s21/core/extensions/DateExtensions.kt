package com.motorista.s21.core.extensions

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * Extensões para manipulação de datas
 */
fun Date.formatToString(pattern: String = "dd/MM/yyyy HH:mm"): String {
    val sdf = SimpleDateFormat(pattern, Locale("pt", "BR"))
    return sdf.format(this)
}

fun Long.toFormattedDate(pattern: String = "dd/MM/yyyy HH:mm"): String {
    return Date(this).formatToString(pattern)
}
