package com.kalex.melichallenge.search.model.dto

data class ValueAttribute(
    val id: String,
    val name: String,
    val source: Long,
    val struct: Any?
)