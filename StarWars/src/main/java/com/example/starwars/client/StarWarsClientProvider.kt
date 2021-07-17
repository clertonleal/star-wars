package com.example.starwars.client

import com.example.starwars.CallbackExecutor
import com.example.starwars.network.NetworkProvider
import com.example.starwars.network.StartWarsNetwork
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit


object StarWarsClientProvider {

    fun provideStarWarsSyncClient(): StarWarsSyncClient {
        return StarWarsSyncClient(provideStarWarsNetwork())
    }

    fun provideStarWarsClient(): StarWarsClient {
        return StarWarsClient(provideStarWarsNetwork(), CallbackExecutor())
    }

    private fun provideStarWarsNetwork(): StartWarsNetwork {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://swapi.dev/api/")
            .client(client)
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()

        val networkProvider = NetworkProvider(retrofit)
        return networkProvider.provideStarWarsNetwork()
    }

}