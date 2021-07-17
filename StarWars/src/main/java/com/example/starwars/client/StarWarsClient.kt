package com.example.starwars.client

import com.example.starwars.model.people.People
import com.example.starwars.model.people.PeopleList
import com.example.starwars.model.StarWarsException
import com.example.starwars.model.film.Film
import com.example.starwars.model.film.FilmList
import com.example.starwars.model.planet.Planet
import com.example.starwars.model.planet.PlanetList
import com.example.starwars.network.StartWarsNetwork

/**
 * StarWarsClient is the responsible class to provide all Start Wars information.
 * All data is provided by [swapi](https://swapi.dev/api)
 *
 * Note: Its not necessary to instantiate this class manually, if you want to use the StarWarsClient, please use StarWarsClientProvider to get a instance.
 * @see StarWarsClientProvider
 */
class StarWarsClient(private val startWarsNetwork: StartWarsNetwork,
                    private val requestExecutor: RequestExecutor) {

    /**
     * This call provides a people of the Star Wars world indicated by id parameter
     *
     * @param id unique identifier of the people
     * @param successCallback callback that will be called if the call is succeed
     * @param errorCallback callback that will be called if the call is failed
     * @param mainThread indicates if the callback should return into main thread or not
     * @see People
     */
    fun getPearsonById(id: String,
                      successCallback: (People) -> Unit,
                      errorCallback: (StarWarsException) -> Unit,
                      mainThread: Boolean = true) {
        startWarsNetwork.getPerson(id).enqueue(requestExecutor.executeRequest(successCallback, errorCallback, mainThread))
    }

    /**
     * This call provides a list of people at the Star Wars world
     *
     * @param successCallback callback that will be called if the call is succeed
     * @param errorCallback callback that will be called if the call is failed
     * @param mainThread indicates if the callback should return into main thread or not
     * @see PeopleList
     */
    fun getPeople(successCallback: (PeopleList) -> Unit,
                  errorCallback: (StarWarsException) -> Unit,
                  mainThread: Boolean = true) {
        startWarsNetwork.getPeople().enqueue(requestExecutor.executeRequest(successCallback, errorCallback, mainThread))
    }

    /**
     * This call provides a planet of the Star Wars world indicated by id parameter
     *
     * @param id unique identifier of the planet
     * @param successCallback callback that will be called if the call is succeed
     * @param errorCallback callback that will be called if the call is failed
     * @param mainThread indicates if the callback should return into main thread or not
     * @see People
     */
    fun getPlanetById(id: String,
                      successCallback: (Planet) -> Unit,
                      errorCallback: (StarWarsException) -> Unit,
                      mainThread: Boolean = true) {
        startWarsNetwork.getPlanet(id).enqueue(requestExecutor.executeRequest(successCallback, errorCallback, mainThread))
    }

    /**
     * This call provides a list of planets at the Star Wars world
     *
     * @param successCallback callback that will be called if the call is succeed
     * @param errorCallback callback that will be called if the call is failed
     * @param mainThread indicates if the callback should return into main thread or not
     * @see PeopleList
     */
    fun getPlanets(successCallback: (PlanetList) -> Unit,
                  errorCallback: (StarWarsException) -> Unit,
                  mainThread: Boolean = true) {
        startWarsNetwork.getPlanets().enqueue(requestExecutor.executeRequest(successCallback, errorCallback, mainThread))
    }

    /**
     * This call provides a film of the Star Wars world indicated by id parameter
     *
     * @param id unique identifier of the film
     * @param successCallback callback that will be called if the call is succeed
     * @param errorCallback callback that will be called if the call is failed
     * @param mainThread indicates if the callback should return into main thread or not
     * @see People
     */
    fun getFilmById(id: String,
                      successCallback: (Film) -> Unit,
                      errorCallback: (StarWarsException) -> Unit,
                      mainThread: Boolean = true) {
        startWarsNetwork.getFilm(id).enqueue(requestExecutor.executeRequest(successCallback, errorCallback, mainThread))
    }

    /**
     * This call provides a list of films at the Star Wars world
     *
     * @param successCallback callback that will be called if the call is succeed
     * @param errorCallback callback that will be called if the call is failed
     * @param mainThread indicates if the callback should return into main thread or not
     * @see PeopleList
     */
    fun getFilms(successCallback: (FilmList) -> Unit,
                  errorCallback: (StarWarsException) -> Unit,
                  mainThread: Boolean = true) {
        startWarsNetwork.getFilms().enqueue(requestExecutor.executeRequest(successCallback, errorCallback, mainThread))
    }
}