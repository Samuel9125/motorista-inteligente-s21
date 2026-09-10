package com.motorista.s21.presentation.features.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Battery6Alert
import androidx.compose.material.icons.filled.Thermostat
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.motorista.s21.core.extensions.toFormattedBytes
import com.motorista.s21.presentation.theme.status_critical
import com.motorista.s21.presentation.theme.status_normal
import com.motorista.s21.presentation.theme.status_warning
import com.motorista.s21.presentation.viewmodel.DashboardViewModel

/**
 * Tela do Dashboard (Início)
 *
 * Exibe:
 * - Saúde geral do dispositivo
 * - Temperatura
 * - Bateria
 * - RAM
 * - Armazenamento
 * - Qualidade da rede
 * - Alertas
 */
@Composable
fun DashboardScreen(
    viewModel: DashboardViewModel = hiltViewModel()
) {
    val deviceHealth by viewModel.deviceHealth.collectAsState()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            HealthScoreCard(health = deviceHealth.overallHealth)
        }

        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                TemperatureCard(
                    temperature = deviceHealth.temperature,
                    modifier = Modifier.weight(1f)
                )
                BatteryCard(
                    percentage = deviceHealth.batteryPercentage,
                    modifier = Modifier.weight(1f)
                )
            }
        }

        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                RAMCard(
                    used = deviceHealth.ramUsed,
                    total = deviceHealth.ramTotal,
                    modifier = Modifier.weight(1f)
                )
                StorageCard(
                    used = deviceHealth.storageUsed,
                    total = deviceHealth.storageTotal,
                    modifier = Modifier.weight(1f)
                )
            }
        }

        item {
            NetworkCard(quality = deviceHealth.networkQuality)
        }

        if (deviceHealth.alerts.isNotEmpty()) {
            item {
                AlertsCard(alerts = deviceHealth.alerts)
            }
        }

        item {
            ActionButtonsRow()
        }
    }
}

/**
 * Card mostrando a pontuação de saúde geral (0-100)
 */
@Composable
fun HealthScoreCard(health: Int) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    "Saúde do Dispositivo",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
                Text(
                    "Seu aparelho está em bom estado",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.7f)
                )
            }

            Box(
                modifier = Modifier.size(80.dp),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    progress = { health / 100f },
                    modifier = Modifier.size(80.dp),
                    color = when {
                        health >= 70 -> status_normal
                        health >= 40 -> status_warning
                        else -> status_critical
                    }
                )
                Text(
                    "$health",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
        }
    }
}

/**
 * Card mostrando temperatura
 */
@Composable
fun TemperatureCard(
    temperature: Float,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = when {
                temperature > 45f -> Color(0xFFFFEBEE)
                temperature > 40f -> Color(0xFFFFF3E0)
                else -> MaterialTheme.colorScheme.surfaceVariant
            }
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                Icons.Default.Thermostat,
                contentDescription = "Temperatura",
                tint = when {
                    temperature > 45f -> status_critical
                    temperature > 40f -> status_warning
                    else -> status_normal
                }
            )
            Text(
                "${temperature}°C",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Text(
                "Temperatura",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

/**
 * Card mostrando bateria
 */
@Composable
fun BatteryCard(
    percentage: Int,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = when {
                percentage < 20 -> Color(0xFFFFEBEE)
                percentage < 50 -> Color(0xFFFFF3E0)
                else -> MaterialTheme.colorScheme.surfaceVariant
            }
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                Icons.Default.Battery6Alert,
                contentDescription = "Bateria",
                tint = when {
                    percentage < 20 -> status_critical
                    percentage < 50 -> status_warning
                    else -> status_normal
                }
            )
            Text(
                "$percentage%",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Text(
                "Bateria",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

/**
 * Card mostrando uso de RAM
 */
@Composable
fun RAMCard(
    used: Long,
    total: Long,
    modifier: Modifier = Modifier
) {
    val percentage = if (total > 0) ((used * 100) / total).toInt() else 0

    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "RAM",
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold
            )
            Text(
                "${used.toFormattedBytes()} / ${total.toFormattedBytes()}",
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                "$percentage%",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

/**
 * Card mostrando armazenamento
 */
@Composable
fun StorageCard(
    used: Long,
    total: Long,
    modifier: Modifier = Modifier
) {
    val percentage = if (total > 0) ((used * 100) / total).toInt() else 0

    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "Armazenamento",
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold
            )
            Text(
                "${used.toFormattedBytes()} / ${total.toFormattedBytes()}",
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                "$percentage%",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

/**
 * Card mostrando qualidade de rede
 */
@Composable
fun NetworkCard(quality: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    "Qualidade da Rede",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    quality,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

/**
 * Card mostrando alertas
 */
@Composable
fun AlertsCard(alerts: List<String>) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFFFF3E0)
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                "⚠️ Alertas Importantes",
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold
            )
            alerts.forEach { alert ->
                Text(
                    "• $alert",
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

/**
 * Linha de botões de ação
 */
@Composable
fun ActionButtonsRow() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Button(
            onClick = { },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Diagnóstico Completo")
        }
        Button(
            onClick = { },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Otimização Segura")
        }
        Button(
            onClick = { },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Modo Motorista")
        }
    }
}
