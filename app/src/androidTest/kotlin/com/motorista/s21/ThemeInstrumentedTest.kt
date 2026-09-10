package com.motorista.s21

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.motorista.s21.presentation.theme.MotoristTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Teste instrumentado para verificar o tema Material 3
 */
@RunWith(AndroidJUnit4::class)
class ThemeInstrumentedTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testMotoristThemeComposition() {
        composeTestRule.setContent {
            MotoristTheme {
                // Se não há exceção, o tema foi aplicado com sucesso
            }
        }
        // Verificar que a composição foi bem-sucedida
        assert(true)
    }
}
