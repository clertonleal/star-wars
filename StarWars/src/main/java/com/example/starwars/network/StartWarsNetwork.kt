package com.example.starwars.network

import com.example.starwars.model.People
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface StartWarsNetwork {

    @GET("people/{id}")
    fun getPeople(@Path("id") id: String): Call<People>

    @GET("people")
    fun getPeople(): Call<List<People>>

}