package com.kalex.melichallenge.commons

import java.text.NumberFormat
import java.util.Currency

/**
 * The FormatCurrencyUseCase object provides a utility function to format a given value in a specified currency.
 * Returns a string representation of the formatted value, including the currency symbol.
 * @param currency: The currency code (e.g., "USD", "EUR", "JPY", etc.). Defaults to "USD" if not provided.
 * @param value: The value to be formatted. Defaults to 0.0 if not provided.
 * @throws if an exception occurs during the formatting process, the method returns an empty string (" ").
 * @author kevin Alexander Soto on 6/2/2024
 * **/
object FormatCurrencyUseCase {

    fun format(currency: String = "USD", value: Double = 0.0): String {
        try {
        val format: NumberFormat = NumberFormat.getCurrencyInstance()
        format.maximumFractionDigits = 3
        format.currency = Currency.getInstance(currency)

        return format.format(value)
        } catch (e: Exception){
            return " "
        }
    }
}