package com.example.starwars

import com.example.starwars.client.StarWarsClient
import com.example.starwars.client.StarWarsClientProvider
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

class StarWarsClientIntegrationTest {

    private lateinit var starWarsClient: StarWarsClient
    private lateinit var latch: CountDownLatch

    @Before
    fun setUp() {
        latch = CountDownLatch(1)
        starWarsClient = StarWarsClientProvider.provideStarWarsClient()
    }

    @Test
    fun validateIntegrationPearsonById() {
        starWarsClient.getPearsonById("5", {
            assertEquals(ExpectedResponse.person5, it.toString())
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

    @Test
    fun validateIntegrationPlanetById() {
        starWarsClient.getPlanetById("5", {
            assertEquals(ExpectedResponse.planet5, it.toString())
            latch.countDown()
        } , {  })
        latch.await(15, TimeUnit.SECONDS)
    }

    @Test
    fun validateIntegrationPlanets() {
        starWarsClient.getPlanets({
            assertEquals(ExpectedResponse.planetList, it.toString())
            latch.countDown()
        } , {  })
        latch.await(15, TimeUnit.SECONDS)
    }

    @Test
    fun validateIntegrationFilmById() {
        starWarsClient.getFilmById("5", {
            assertEquals(ExpectedResponse.film5, it.title)
            latch.countDown()
        } , {  })
        latch.await(15, TimeUnit.SECONDS)
    }

    @Test
    fun validateIntegrationFilms() {
        starWarsClient.getFilms({
            assertEquals(ExpectedResponse.filmList, it.count.toString())
            latch.countDown()
        } , {  })
        latch.await(15, TimeUnit.SECONDS)
    }
}