package com.kalex.melichallenge.search.model.dto

data class Installments(
    val amount: Double,
    val currency_id: String,
    val quantity: Int,
    val rate: Double
)