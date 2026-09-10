package com.motorista.s21.presentation.viewmodel

import com.motorista.s21.domain.model.DeviceHealthStatus
import com.motorista.s21.domain.usecase.GetDiagnosticsUseCase
import com.motorista.s21.domain.usecase.GetLatestDiagnosticUseCase
import com.motorista.s21.domain.usecase.InsertDiagnosticUseCase
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

/**
 * Testes unitários para o ViewModel de diagnóstico
 */
class DiagnosticViewModelTest {

    @MockK
    private lateinit var getDiagnosticsUseCase: GetDiagnosticsUseCase

    @MockK
    private lateinit var getLatestDiagnosticUseCase: GetLatestDiagnosticUseCase

    @MockK
    private lateinit var insertDiagnosticUseCase: InsertDiagnosticUseCase

    @MockK
    private lateinit var deleteDiagnosticUseCase: com.motorista.s21.domain.usecase.DeleteDiagnosticUseCase

    private lateinit var viewModel: DiagnosticViewModel

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        io.mockk.every { getDiagnosticsUseCase() } returns flowOf(emptyList())
        io.mockk.every { getLatestDiagnosticUseCase() } returns flowOf(null)

        viewModel = DiagnosticViewModel(
            getDiagnosticsUseCase,
            getLatestDiagnosticUseCase,
            insertDiagnosticUseCase,
            deleteDiagnosticUseCase
        )
    }

    @Test
    fun `diagnostics state should be initialized with empty list`() = runTest {
        // Assert
        assert(viewModel.diagnostics.value.isEmpty())
    }

    @Test
    fun `latestDiagnostic state should be initialized with null`() = runTest {
        // Assert
        assert(viewModel.latestDiagnostic.value == null)
    }
}
