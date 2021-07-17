package com.example.starwars.network

import com.example.starwars.model.People
import com.example.starwars.model.PeopleList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * StartWarsNetwork is the REST client that will call the [swapi](https://swapi.dev/api) endpoints
 */
interface StartWarsNetwork {

    @GET("people/{id}")
    fun getPeople(@Path("id") id: String): Call<People>

    @GET("people")
    fun getPeople(): Call<PeopleList>

}