package com.example.starwars

import com.example.starwars.client.StarWarsClient
import com.example.starwars.client.StarWarsClientProvider
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.Timeout
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

class StarWarsClientIntegrationTest {

    @get:Rule
    var globalTimeout: Timeout = Timeout(10, TimeUnit.SECONDS)

    private lateinit var starWarsClient: StarWarsClient
    private lateinit var latch: CountDownLatch

    @Before
    fun setUp() {
        latch = CountDownLatch(1)
        starWarsClient = StarWarsClientProvider.provideStarWarsClient()
    }

    @Test
    fun validateIntegrationPeopleById() {
        starWarsClient.getPeopleById("5", {
            assertEquals(ExpectedResponse.people5, it.toString())
            latch.countDown()
        } , {  })
        latch.await(15, TimeUnit.SECONDS)
    }

    @Test
    fun validateIntegrationPeople() {
        starWarsClient.getPeople({
            assertEquals(ExpectedResponse.peopleList, it.toString())
            latch.countDown()
        } , {  })
        latch.await(15, TimeUnit.SECONDS)
    }
}