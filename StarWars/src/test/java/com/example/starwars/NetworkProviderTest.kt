package com.example.starwars

import com.example.starwars.network.NetworkProvider
import com.example.starwars.network.StartWarsNetwork
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Test

import org.junit.Before
import retrofit2.Retrofit

class NetworkProviderTest {

    private lateinit var retrofit: Retrofit
    private lateinit var networkProvider: NetworkProvider

    @Before
    fun setUp() {
        retrofit = mockk()
        networkProvider = NetworkProvider(retrofit)
    }

    @Test
    fun whenProvidingNetworkClientProviderShouldCallRetrofit() {
        val createdNetwork: StartWarsNetwork = mockk()
        every { retrofit.create(StartWarsNetwork::class.java) } returns createdNetwork

        val networkProvided =  networkProvider.provideStarWarsNetwork()

        verify { retrofit.create(StartWarsNetwork::class.java) }
        assertEquals(createdNetwork, networkProvided)
    }
}