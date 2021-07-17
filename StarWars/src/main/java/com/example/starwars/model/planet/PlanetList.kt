package com.example.starwars.model.planet

import kotlinx.serialization.Serializable

@Serializable
data class PlanetList(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<Planet>
)
