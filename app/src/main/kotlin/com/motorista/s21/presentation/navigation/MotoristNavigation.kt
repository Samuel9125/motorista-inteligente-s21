package com.motorista.s21.presentation.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.motorista.s21.presentation.features.dashboard.DashboardScreen
import com.motorista.s21.presentation.features.diagnostics.DiagnosticsScreen
import com.motorista.s21.presentation.features.history.HistoryScreen
import com.motorista.s21.presentation.features.network.NetworkScreen
import com.motorista.s21.presentation.features.optimization.OptimizationScreen
import com.motorista.s21.presentation.features.settings.SettingsScreen

/**
 * Navegação principal do aplicativo
 *
 * Responsável por:
 * - Gerenciar as rotas e transições entre telas
 * - Exibir a barra de navegação inferior
 * - Manter o estado de navegação
 */
@Composable
fun MotoristNavigation() {
    val navController = rememberNavController()
    var selectedRoute by rememberSaveable { mutableStateOf(MotoristRoute.HOME.route) }

    Scaffold(
        bottomBar = {
            NavigationBar {
                MotoristRoute.getBottomNavItems().forEach { route ->
                    NavigationBarItem(
                        selected = selectedRoute == route.route,
                        onClick = {
                            selectedRoute = route.route
                            navController.navigate(route.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                lazyRestoreState = true
                            }
                        },
                        icon = { androidx.compose.material3.Icon(route.icon, contentDescription = route.title) },
                        label = { Text(route.title) }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = MotoristRoute.HOME.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(MotoristRoute.HOME.route) {
                DashboardScreen()
            }
            composable(MotoristRoute.DIAGNOSTICS.route) {
                DiagnosticsScreen()
            }
            composable(MotoristRoute.OPTIMIZATION.route) {
                OptimizationScreen()
            }
            composable(MotoristRoute.NETWORK.route) {
                NetworkScreen()
            }
            composable(MotoristRoute.HISTORY.route) {
                HistoryScreen()
            }
            composable(MotoristRoute.SETTINGS.route) {
                SettingsScreen()
            }
        }
    }
}
