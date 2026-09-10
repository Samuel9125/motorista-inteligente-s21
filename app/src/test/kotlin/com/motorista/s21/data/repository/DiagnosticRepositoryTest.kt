package com.motorista.s21.data.repository

import com.motorista.s21.data.database.dao.DiagnosticDao
import com.motorista.s21.domain.model.DiagnosticEntity
import io.mockk.MockKAnnotations
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

/**
 * Testes unitários para o repositório de diagnóstico
 */
class DiagnosticRepositoryTest {

    @MockK
    private lateinit var diagnosticDao: DiagnosticDao

    private lateinit var diagnosticRepository: DiagnosticRepository

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        diagnosticRepository = DiagnosticRepository(diagnosticDao)
    }

    @Test
    fun `getAllDiagnostics should return flow from dao`() {
        // Arrange
        val expectedDiagnostics = listOf(
            DiagnosticEntity(id = 1, deviceHealth = 80),
            DiagnosticEntity(id = 2, deviceHealth = 70)
        )
        io.mockk.every { diagnosticDao.getAllDiagnostics() } returns flowOf(expectedDiagnostics)

        // Act
        val result = diagnosticRepository.getAllDiagnostics()

        // Assert
        io.mockk.verify { diagnosticDao.getAllDiagnostics() }
    }

    @Test
    fun `insertDiagnostic should call dao insert`() = runTest {
        // Arrange
        val diagnostic = DiagnosticEntity(id = 1, deviceHealth = 85)
        io.mockk.coEvery { diagnosticDao.insert(diagnostic) } returns 1L

        // Act
        diagnosticRepository.insertDiagnostic(diagnostic)

        // Assert
        coVerify { diagnosticDao.insert(diagnostic) }
    }

    @Test
    fun `deleteDiagnostic should call dao delete`() = runTest {
        // Arrange
        val diagnostic = DiagnosticEntity(id = 1, deviceHealth = 85)
        io.mockk.coEvery { diagnosticDao.delete(diagnostic) } returns Unit

        // Act
        diagnosticRepository.deleteDiagnostic(diagnostic)

        // Assert
        coVerify { diagnosticDao.delete(diagnostic) }
    }
}
