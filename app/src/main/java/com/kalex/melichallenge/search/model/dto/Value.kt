package com.kalex.melichallenge.search.model.dto

data class Value(
    val id: String,
    val name: String,
    val path_from_root: List<PathFromRoot>
)