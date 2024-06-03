package com.kalex.melichallenge.commons

import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * @author kevin Alexander Soto on 6/2/2024
 * **/
class FormatCurrencyUseCaseTest {
    private val useCase = FormatCurrencyUseCase

    @Test
    fun testFormatUSD() {
        val formattedValue = useCase.format(currency = "USD", value = 1234.5678)
        assertEquals("$1,234.568", formattedValue)
    }

    @Test
    fun testFormatEUR() {
        val formattedValue = useCase.format(currency = "EUR", value = 987.1234)
        assertEquals("€987.123", formattedValue)
    }

    @Test
    fun testFormatEmptyCurrency() {
        val formattedValue = useCase.format(value = 1234.5678)

        assertEquals("$1,234.568", formattedValue)
    }

    @Test
    fun testFormatInvalidCurrency() {
        val formattedValue = useCase.format(currency = "XYZ", value = 0.0)

        assertEquals(" ", formattedValue)
    }
}