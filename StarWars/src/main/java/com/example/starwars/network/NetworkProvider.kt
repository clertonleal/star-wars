package com.example.starwars.network

import retrofit2.Retrofit

class NetworkProvider(private val retrofit: Retrofit) {

    fun provideStarWarsNetwork(): StartWarsNetwork {
        return retrofit.create(StartWarsNetwork::class.java)
    }

}