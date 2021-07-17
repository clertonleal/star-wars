package com.example.starwars.model.planet

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Planet(private val name: String,
                  @SerialName("rotation_period") private val rotationPeriod: String,
                  @SerialName("orbital_period") private val orbitalPeriod: String,
                  private val diameter: String,
                  private val climate: String,
                  private val gravity: String,
                  private val terrain: String,
                  private val surface_water: String,
                  private val population: String,
                  private val residents: List<String>,
                  private val films: List<String>,
                  private val created: String,
                  private val edited: String,
                  private val url: String)
