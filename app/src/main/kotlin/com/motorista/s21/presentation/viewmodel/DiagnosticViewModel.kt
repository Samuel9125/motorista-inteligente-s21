package com.motorista.s21.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.motorista.s21.domain.model.DiagnosticEntity
import com.motorista.s21.domain.usecase.DeleteDiagnosticUseCase
import com.motorista.s21.domain.usecase.GetDiagnosticsUseCase
import com.motorista.s21.domain.usecase.GetLatestDiagnosticUseCase
import com.motorista.s21.domain.usecase.InsertDiagnosticUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

/**
 * ViewModel para gerenciar o estado dos diagnósticos
 *
 * Responsável por:
 * - Coordenar as use cases
 * - Gerenciar o estado da UI
 * - Expor os dados como StateFlow para observação reativa
 */
@HiltViewModel
class DiagnosticViewModel @Inject constructor(
    private val getDiagnosticsUseCase: GetDiagnosticsUseCase,
    private val getLatestDiagnosticUseCase: GetLatestDiagnosticUseCase,
    private val insertDiagnosticUseCase: InsertDiagnosticUseCase,
    private val deleteDiagnosticUseCase: DeleteDiagnosticUseCase
) : ViewModel() {

    /**
     * Fluxo de todos os diagnósticos
     */
    val diagnostics: StateFlow<List<DiagnosticEntity>> = getDiagnosticsUseCase()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    /**
     * Fluxo do diagnóstico mais recente
     */
    val latestDiagnostic: StateFlow<DiagnosticEntity?> = getLatestDiagnosticUseCase()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = null
        )

    /**
     * Insere um novo diagnóstico
     */
    fun insertDiagnostic(diagnostic: DiagnosticEntity) {
        viewModelScope.launch {
            try {
                insertDiagnosticUseCase(diagnostic)
                Timber.d("Diagnostic inserted successfully")
            } catch (e: Exception) {
                Timber.e(e, "Error inserting diagnostic")
            }
        }
    }

    /**
     * Deleta um diagnóstico
     */
    fun deleteDiagnostic(diagnostic: DiagnosticEntity) {
        viewModelScope.launch {
            try {
                deleteDiagnosticUseCase(diagnostic)
                Timber.d("Diagnostic deleted successfully")
            } catch (e: Exception) {
                Timber.e(e, "Error deleting diagnostic")
            }
        }
    }
}
