package com.example.starwars.model

import kotlinx.serialization.Serializable

@Serializable
data class PeopleList(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<People>
)
