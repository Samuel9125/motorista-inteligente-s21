package com.motorista.s21

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.motorista.s21.data.database.MotoristDatabase
import com.motorista.s21.domain.model.DiagnosticEntity
import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Testes instrumentados para o banco de dados Room
 */
@RunWith(AndroidJUnit4::class)
class DatabaseInstrumentedTest {

    private lateinit var database: MotoristDatabase

    @Before
    fun setupDatabase() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(
            context,
            MotoristDatabase::class.java
        ).build()
    }

    @After
    fun closeDatabase() {
        database.close()
    }

    @Test
    fun testInsertAndRetrieveDiagnostic() = runTest {
        // Arrange
        val diagnostic = DiagnosticEntity(
            id = 0,
            timestamp = System.currentTimeMillis(),
            deviceHealth = 85,
            temperature = 35.5f,
            batteryPercentage = 90
        )
        val dao = database.diagnosticDao()

        // Act
        dao.insert(diagnostic)
        val retrievedDiagnostic = dao.getLatestDiagnostic()

        // Assert - Este é um teste assíncrono com Flow
        retrievedDiagnostic.collect { latestDiag ->
            assert(latestDiag?.deviceHealth == 85)
        }
    }

    @Test
    fun testDeleteDiagnostic() = runTest {
        // Arrange
        val diagnostic = DiagnosticEntity(
            id = 1,
            timestamp = System.currentTimeMillis(),
            deviceHealth = 70
        )
        val dao = database.diagnosticDao()
        dao.insert(diagnostic)

        // Act
        dao.delete(diagnostic)
        val allDiagnostics = dao.getAllDiagnostics()

        // Assert
        allDiagnostics.collect { diagnostics ->
            assert(diagnostics.isEmpty())
        }
    }
}
