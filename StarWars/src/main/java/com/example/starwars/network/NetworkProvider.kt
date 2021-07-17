package com.example.starwars.network

import retrofit2.Retrofit

/**
 * NetworkProvider is responsible to provide the StartWarsNetwork class that is responsible to do REST calls
 * @see StartWarsNetwork
 */
internal class NetworkProvider(private val retrofit: Retrofit) {

    fun provideStarWarsNetwork(): StartWarsNetwork {
        return retrofit.create(StartWarsNetwork::class.java)
    }

}