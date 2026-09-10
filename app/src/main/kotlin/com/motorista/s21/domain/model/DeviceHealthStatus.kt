package com.motorista.s21.domain.model

/**
 * Modelo de dados para o status de saúde geral do dispositivo
 */
data class DeviceHealthStatus(
    val overallHealth: Int = 0, // 0-100
    val temperature: Float = 0f,
    val batteryPercentage: Int = 0,
    val batteryHealth: String = "Unknown",
    val ramUsed: Long = 0,
    val ramTotal: Long = 0,
    val ramPercentage: Int = 0,
    val storageUsed: Long = 0,
    val storageTotal: Long = 0,
    val storagePercentage: Int = 0,
    val networkQuality: String = "Unknown",
    val gpsStatus: String = "Unknown",
    val lastCheckTimestamp: Long = System.currentTimeMillis(),
    val alerts: List<String> = emptyList()
)
