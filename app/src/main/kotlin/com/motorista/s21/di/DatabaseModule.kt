package com.motorista.s21.di

import android.content.Context
import androidx.room.Room
import com.motorista.s21.data.database.MotoristDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Módulo de injeção de dependência para banco de dados
 *
 * Fornece as instâncias singleton do banco de dados e DAOs
 */
@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    /**
     * Fornece a instância única do banco de dados
     */
    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ): MotoristDatabase {
        return Room.databaseBuilder(
            context,
            MotoristDatabase::class.java,
            MotoristDatabase.DATABASE_NAME
        ).build()
    }

    /**
     * Fornece o DAO de diagnóstico
     */
    @Singleton
    @Provides
    fun provideDiagnosticDao(
        database: MotoristDatabase
    ) = database.diagnosticDao()
}
