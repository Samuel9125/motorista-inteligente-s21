package com.motorista.s21.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.motorista.s21.data.database.dao.DiagnosticDao
import com.motorista.s21.domain.model.DiagnosticEntity

/**
 * Banco de dados Room do aplicativo
 *
 * Responsável por:
 * - Gerenciar as entidades do banco
 * - Fornecer DAOs para acesso aos dados
 * - Versioning do banco
 */
@Database(
    entities = [DiagnosticEntity::class],
    version = 1,
    exportSchema = false
)
abstract class MotoristDatabase : RoomDatabase() {
    
    /**
     * DAO para acesso à tabela de diagnósticos
     */
    abstract fun diagnosticDao(): DiagnosticDao

    companion object {
        const val DATABASE_NAME = "motorista_s21_db"
    }
}
