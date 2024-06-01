package com.kalex.melichallenge.search.model.dto

data class Filter(
    val id: String,
    val name: String,
    val type: String,
    val values: List<Value>
)