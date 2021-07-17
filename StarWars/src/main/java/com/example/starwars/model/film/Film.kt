package com.example.starwars.model.film

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Film(
    val title: String,
    @SerialName("episode_id") val episodeId: Int,
    @SerialName("opening_crawl") val openingCrawl: String,
    val director: String,
    val producer: String,
    @SerialName("release_date") val releaseDate: String,
    val characters: List<String>,
    val planets: List<String>,
    val starships: List<String>,
    val vehicles: List<String>,
    val species: List<String>,
    val created: String,
    val edited: String,
    val url: String
)
