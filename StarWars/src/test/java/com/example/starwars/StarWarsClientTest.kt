package com.example.starwars

import com.example.starwars.client.RequestExecutor
import com.example.starwars.client.StarWarsClient
import com.example.starwars.model.film.Film
import com.example.starwars.model.film.FilmList
import com.example.starwars.model.people.People
import com.example.starwars.model.people.PeopleList
import com.example.starwars.model.planet.Planet
import com.example.starwars.model.planet.PlanetList
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
    fun whenCallGetPearsonByIdShouldCallStartWarsNetwork() {
        val peopleRetrofitCallback: Call<People> = mockk(relaxed = true)
        every { startWarsNetwork.getPerson("5") } returns peopleRetrofitCallback
        val requestExecutor: Callback<People> = mockk()
        every { this@StarWarsClientTest.requestExecutor.executeRequest<People>(any(), any(), true) } returns requestExecutor

        starWarsClient.getPearsonById("5", mockk(), mockk(), true)

        verify { startWarsNetwork.getPerson("5") }
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

    @Test
    fun whenCallGetPlanetByIdShouldCallStartWarsNetwork() {
        val peopleRetrofitCallback: Call<Planet> = mockk(relaxed = true)
        every { startWarsNetwork.getPlanet("5") } returns peopleRetrofitCallback
        val requestExecutor: Callback<Planet> = mockk()
        every { this@StarWarsClientTest.requestExecutor.executeRequest<Planet>(any(), any(), true) } returns requestExecutor

        starWarsClient.getPlanetById("5", mockk(), mockk(), true)

        verify { startWarsNetwork.getPlanet("5") }
        verify { peopleRetrofitCallback.enqueue(requestExecutor) }
    }

    @Test
    fun whenCallGetPlanetsShouldCallStartWarsNetwork() {
        val peopleRetrofitCallback: Call<PlanetList> = mockk(relaxed = true)
        every { startWarsNetwork.getPlanets() } returns peopleRetrofitCallback
        val requestExecutor: Callback<PlanetList> = mockk()
        every { this@StarWarsClientTest.requestExecutor.executeRequest<PlanetList>(any(), any(), true) } returns requestExecutor

        starWarsClient.getPlanets(mockk(), mockk(), true)

        verify { startWarsNetwork.getPlanets() }
        verify { peopleRetrofitCallback.enqueue(requestExecutor) }
    }

    @Test
    fun whenCallGetFilmByIdShouldCallStartWarsNetwork() {
        val peopleRetrofitCallback: Call<Film> = mockk(relaxed = true)
        every { startWarsNetwork.getFilm("5") } returns peopleRetrofitCallback
        val requestExecutor: Callback<Film> = mockk()
        every { this@StarWarsClientTest.requestExecutor.executeRequest<Film>(any(), any(), true) } returns requestExecutor

        starWarsClient.getFilmById("5", mockk(), mockk(), true)

        verify { startWarsNetwork.getFilm("5") }
        verify { peopleRetrofitCallback.enqueue(requestExecutor) }
    }

    @Test
    fun whenCallGetFilmsShouldCallStartWarsNetwork() {
        val peopleRetrofitCallback: Call<FilmList> = mockk(relaxed = true)
        every { startWarsNetwork.getFilms() } returns peopleRetrofitCallback
        val requestExecutor: Callback<FilmList> = mockk()
        every { this@StarWarsClientTest.requestExecutor.executeRequest<FilmList>(any(), any(), true) } returns requestExecutor

        starWarsClient.getFilms(mockk(), mockk(), true)

        verify { startWarsNetwork.getFilms() }
        verify { peopleRetrofitCallback.enqueue(requestExecutor) }
    }
}