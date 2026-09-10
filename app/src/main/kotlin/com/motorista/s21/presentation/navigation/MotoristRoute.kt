package com.motorista.s21.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AirlineSeatFlat
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.DashboardCustomize
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.NetworkCheck
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Enum com as rotas de navegação do aplicativo
 *
 * Cada rota representa uma tela principal do aplicativo
 */
enum class MotoristRoute(
    val title: String,
    val icon: ImageVector,
    val route: String
) {
    HOME(
        title = "Início",
        icon = Icons.Default.DashboardCustomize,
        route = "home"
    ),
    DIAGNOSTICS(
        title = "Diagnóstico",
        icon = Icons.Default.Build,
        route = "diagnostics"
    ),
    OPTIMIZATION(
        title = "Otimização",
        icon = Icons.Default.AirlineSeatFlat,
        route = "optimization"
    ),
    NETWORK(
        title = "Rede",
        icon = Icons.Default.NetworkCheck,
        route = "network"
    ),
    HISTORY(
        title = "Histórico",
        icon = Icons.Default.History,
        route = "history"
    ),
    SETTINGS(
        title = "Configurações",
        icon = Icons.Default.Settings,
        route = "settings"
    );

    companion object {
        fun getBottomNavItems(): List<MotoristRoute> = listOf(
            HOME,
            DIAGNOSTICS,
            OPTIMIZATION,
            NETWORK,
            HISTORY,
            SETTINGS
        )
    }
}
