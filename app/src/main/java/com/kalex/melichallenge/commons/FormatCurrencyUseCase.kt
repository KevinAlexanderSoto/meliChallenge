package com.kalex.melichallenge.commons

import java.text.NumberFormat
import java.util.Currency

/**
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