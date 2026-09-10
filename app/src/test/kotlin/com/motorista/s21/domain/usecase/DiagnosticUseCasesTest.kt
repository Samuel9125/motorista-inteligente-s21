package com.motorista.s21.domain.usecase

import com.motorista.s21.data.repository.DiagnosticRepository
import com.motorista.s21.domain.model.DiagnosticEntity
import io.mockk.MockKAnnotations
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

/**
 * Testes unitários para os Use Cases de diagnóstico
 */
class DiagnosticUseCasesTest {

    @MockK
    private lateinit var diagnosticRepository: DiagnosticRepository

    private lateinit var getDiagnosticsUseCase: GetDiagnosticsUseCase
    private lateinit var getLatestDiagnosticUseCase: GetLatestDiagnosticUseCase
    private lateinit var insertDiagnosticUseCase: InsertDiagnosticUseCase
    private lateinit var deleteDiagnosticUseCase: DeleteDiagnosticUseCase

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        getDiagnosticsUseCase = GetDiagnosticsUseCase(diagnosticRepository)
        getLatestDiagnosticUseCase = GetLatestDiagnosticUseCase(diagnosticRepository)
        insertDiagnosticUseCase = InsertDiagnosticUseCase(diagnosticRepository)
        deleteDiagnosticUseCase = DeleteDiagnosticUseCase(diagnosticRepository)
    }

    @Test
    fun `getDiagnosticsUseCase should call repository`() {
        // Arrange
        val expectedDiagnostics = listOf(
            DiagnosticEntity(id = 1, deviceHealth = 80),
            DiagnosticEntity(id = 2, deviceHealth = 70)
        )
        io.mockk.every { diagnosticRepository.getAllDiagnostics() } returns flowOf(expectedDiagnostics)

        // Act
        val result = getDiagnosticsUseCase()

        // Assert
        io.mockk.verify { diagnosticRepository.getAllDiagnostics() }
    }

    @Test
    fun `insertDiagnosticUseCase should call repository insert`() = runTest {
        // Arrange
        val diagnostic = DiagnosticEntity(id = 1, deviceHealth = 85)
        io.mockk.coEvery { diagnosticRepository.insertDiagnostic(diagnostic) } returns 1L

        // Act
        insertDiagnosticUseCase(diagnostic)

        // Assert
        coVerify { diagnosticRepository.insertDiagnostic(diagnostic) }
    }

    @Test
    fun `deleteDiagnosticUseCase should call repository delete`() = runTest {
        // Arrange
        val diagnostic = DiagnosticEntity(id = 1, deviceHealth = 85)
        io.mockk.coEvery { diagnosticRepository.deleteDiagnostic(diagnostic) } returns Unit

        // Act
        deleteDiagnosticUseCase(diagnostic)

        // Assert
        coVerify { diagnosticRepository.deleteDiagnostic(diagnostic) }
    }
}
