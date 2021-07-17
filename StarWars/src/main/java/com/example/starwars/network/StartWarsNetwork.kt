package com.example.starwars.network

import com.example.starwars.model.film.Film
import com.example.starwars.model.film.FilmList
import com.example.starwars.model.people.People
import com.example.starwars.model.people.PeopleList
import com.example.starwars.model.planet.Planet
import com.example.starwars.model.planet.PlanetList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * StartWarsNetwork is the REST client that will call the [swapi](https://swapi.dev/api) endpoints
 */
interface StartWarsNetwork {

    @GET("people/{id}")
    fun getPerson(@Path("id") id: String): Call<People>

    @GET("people")
    fun getPeople(): Call<PeopleList>

    @GET("planets/{id}")
    fun getPlanet(@Path("id") id: String): Call<Planet>

    @GET("planets")
    fun getPlanets(): Call<PlanetList>

    @GET("films/{id}")
    fun getFilm(@Path("id") id: String): Call<Film>

    @GET("films")
    fun getFilms(): Call<FilmList>

}