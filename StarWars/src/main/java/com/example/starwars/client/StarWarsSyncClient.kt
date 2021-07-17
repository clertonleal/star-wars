package com.example.starwars.client

import com.example.starwars.model.People
import com.example.starwars.model.PeopleList
import com.example.starwars.model.StarWarsException
import com.example.starwars.network.StartWarsNetwork
import retrofit2.Response

class StarWarsSyncClient(private val startWarsNetwork: StartWarsNetwork) {

    @Throws(StarWarsException::class)
    fun getPeopleById(id: String): People  = executeRequest { startWarsNetwork.getPeople(id).execute() }

    @Throws(StarWarsException::class)
    fun getPeople(): PeopleList = executeRequest { startWarsNetwork.getPeople().execute() }

    @Throws(StarWarsException::class)
    private fun <T> executeRequest(request: () -> Response<T>): T {
        val response: Response<T>
        try {
            response = request()
        } catch (error: Exception) {
            throw StarWarsException("Internal SDK error", error)
        }

        val body = response.body()
        if (response.isSuccessful && body != null) {
            return body
        } else {
            throw StarWarsException("Network error code: ${response.code()}. Message: ${response.errorBody()}")
        }
    }

}