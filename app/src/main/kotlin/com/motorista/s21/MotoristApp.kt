package com.motorista.s21

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

/**
 * Aplicação principal do Motorista Inteligente S21
 *
 * Responsável por inicializar componentes globais como:
 * - Hilt para injeção de dependência
 * - Timber para logging
 * - Configuração de banco de dados
 */
@HiltAndroidApp
class MotoristApp : Application() {

    override fun onCreate() {
        super.onCreate()
        
        // Inicializa logging
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        
        Timber.d("Motorista Inteligente S21 iniciado")
    }
}
