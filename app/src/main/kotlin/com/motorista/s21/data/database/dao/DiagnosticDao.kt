package com.motorista.s21.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.motorista.s21.domain.model.DiagnosticEntity
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object (DAO) para a entidade de Diagnóstico
 *
 * Define as operações disponíveis no banco de dados para diagnósticos
 */
@Dao
interface DiagnosticDao {
    
    /**
     * Insere um novo diagnóstico no banco
     */
    @Insert
    suspend fun insert(diagnostic: DiagnosticEntity): Long

    /**
     * Atualiza um diagnóstico existente
     */
    @Update
    suspend fun update(diagnostic: DiagnosticEntity)

    /**
     * Deleta um diagnóstico específico
     */
    @Delete
    suspend fun delete(diagnostic: DiagnosticEntity)

    /**
     * Obtém todos os diagnósticos ordenados por timestamp decrescente
     */
    @Query("SELECT * FROM diagnostics ORDER BY timestamp DESC")
    fun getAllDiagnostics(): Flow<List<DiagnosticEntity>>

    /**
     * Obtém o diagnóstico mais recente
     */
    @Query("SELECT * FROM diagnostics ORDER BY timestamp DESC LIMIT 1")
    fun getLatestDiagnostic(): Flow<DiagnosticEntity?>

    /**
     * Obtém diagnósticos de um período específico
     */
    @Query("SELECT * FROM diagnostics WHERE timestamp BETWEEN :startTime AND :endTime ORDER BY timestamp DESC")
    fun getDiagnosticsInTimeRange(startTime: Long, endTime: Long): Flow<List<DiagnosticEntity>>

    /**
     * Deleta diagnósticos mais antigos que uma data específica
     */
    @Query("DELETE FROM diagnostics WHERE timestamp < :beforeTime")
    suspend fun deleteOlderThan(beforeTime: Long)
}
