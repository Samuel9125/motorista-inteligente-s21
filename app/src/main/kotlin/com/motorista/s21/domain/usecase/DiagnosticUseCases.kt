package com.motorista.s21.domain.usecase

import com.motorista.s21.data.repository.DiagnosticRepository
import com.motorista.s21.domain.model.DiagnosticEntity
import javax.inject.Inject

/**
 * Use Case para obter diagnósticos
 */
class GetDiagnosticsUseCase @Inject constructor(
    private val diagnosticRepository: DiagnosticRepository
) {
    operator fun invoke() = diagnosticRepository.getAllDiagnostics()
}

/**
 * Use Case para obter o diagnóstico mais recente
 */
class GetLatestDiagnosticUseCase @Inject constructor(
    private val diagnosticRepository: DiagnosticRepository
) {
    operator fun invoke() = diagnosticRepository.getLatestDiagnostic()
}

/**
 * Use Case para inserir um novo diagnóstico
 */
class InsertDiagnosticUseCase @Inject constructor(
    private val diagnosticRepository: DiagnosticRepository
) {
    suspend operator fun invoke(diagnostic: DiagnosticEntity): Long {
        return diagnosticRepository.insertDiagnostic(diagnostic)
    }
}

/**
 * Use Case para deletar um diagnóstico
 */
class DeleteDiagnosticUseCase @Inject constructor(
    private val diagnosticRepository: DiagnosticRepository
) {
    suspend operator fun invoke(diagnostic: DiagnosticEntity) {
        diagnosticRepository.deleteDiagnostic(diagnostic)
    }
}
