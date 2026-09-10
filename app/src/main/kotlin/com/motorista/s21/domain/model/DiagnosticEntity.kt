package com.motorista.s21.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

/**
 * Modelo de entidade para o diagnóstico do sistema
 *
 * Representa um diagnóstico completo realizado no dispositivo
 */
@Entity(tableName = "diagnostics")
data class DiagnosticEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val timestamp: Long = System.currentTimeMillis(),
    val deviceHealth: Int = 0, // 0-100
    val temperature: Float = 0f,
    val batteryPercentage: Int = 0,
    val batteryHealth: String = "Unknown",
    val ramUsed: Long = 0,
    val ramTotal: Long = 0,
    val storageUsed: Long = 0,
    val storageTotal: Long = 0,
    val networkQuality: String = "Unknown",
    val gpsStatus: String = "Unknown",
    val diagnosticDetails: String = "" // JSON string com detalhes completos
)
