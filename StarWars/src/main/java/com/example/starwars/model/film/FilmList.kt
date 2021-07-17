package com.example.starwars.model.film

import kotlinx.serialization.Serializable

@Serializable
data class FilmList(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<Film>
)
