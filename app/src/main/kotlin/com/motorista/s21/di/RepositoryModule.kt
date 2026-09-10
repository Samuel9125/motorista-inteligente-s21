package com.motorista.s21.di

import com.motorista.s21.data.repository.DiagnosticRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Módulo de injeção de dependência para repositórios
 *
 * Fornece as instâncias dos repositórios como singleton
 */
@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    /**
     * Fornece a instância do repositório de diagnóstico
     */
    @Singleton
    @Provides
    fun provideDiagnosticRepository(
        repository: DiagnosticRepository
    ): DiagnosticRepository = repository
}
