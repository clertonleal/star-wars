package com.example.starwars

import com.example.starwars.client.RequestExecutor
import com.example.starwars.client.StarWarsClient
import com.example.starwars.model.People
import com.example.starwars.model.PeopleList
import com.example.starwars.network.StartWarsNetwork
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Test
import retrofit2.Call
import retrofit2.Callback

class StarWarsClientTest {

    private lateinit var startWarsNetwork: StartWarsNetwork
    private lateinit var requestExecutor: RequestExecutor
    private lateinit var starWarsClient: StarWarsClient

    @Before
    fun setUp() {
        startWarsNetwork = mockk()
        requestExecutor = mockk()
        starWarsClient = StarWarsClient(startWarsNetwork, requestExecutor)
    }

    @Test
    fun whenCallGetPeopleByIdShouldCallStartWarsNetwork() {
        val peopleRetrofitCallback: Call<People> = mockk(relaxed = true)
        every { startWarsNetwork.getPeople("5") } returns peopleRetrofitCallback
        val requestExecutor: Callback<People> = mockk()
        every { this@StarWarsClientTest.requestExecutor.executeRequest<People>(any(), any(), true) } returns requestExecutor

        starWarsClient.getPeopleById("5", mockk(), mockk(), true)

        verify { startWarsNetwork.getPeople("5") }
        verify { peopleRetrofitCallback.enqueue(requestExecutor) }
    }

    @Test
    fun whenCallGetPeopleShouldCallStartWarsNetwork() {
        val peopleRetrofitCallback: Call<PeopleList> = mockk(relaxed = true)
        every { startWarsNetwork.getPeople() } returns peopleRetrofitCallback
        val requestExecutor: Callback<PeopleList> = mockk()
        every { this@StarWarsClientTest.requestExecutor.executeRequest<PeopleList>(any(), any(), true) } returns requestExecutor

        starWarsClient.getPeople(mockk(), mockk(), true)

        verify { startWarsNetwork.getPeople() }
        verify { peopleRetrofitCallback.enqueue(requestExecutor) }
    }
}