package com.example.starwars.client

import com.example.starwars.CallbackExecutor
import com.example.starwars.model.People
import com.example.starwars.model.PeopleList
import com.example.starwars.model.StarWarsException
import com.example.starwars.network.StartWarsNetwork
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StarWarsClient(private val startWarsNetwork: StartWarsNetwork,
                     private val callbackExecutor: CallbackExecutor) {

    fun getPeopleById(id: String,
                      successCallback: (People) -> Unit,
                      errorCallback: (StarWarsException) -> Unit,
                      mainThread: Boolean = true) {
        startWarsNetwork.getPeople(id).enqueue(executeRequest(successCallback, errorCallback, mainThread))
    }

    fun getPeople(successCallback: (PeopleList) -> Unit,
                      errorCallback: (StarWarsException) -> Unit,
                      mainThread: Boolean = true) {
        startWarsNetwork.getPeople().enqueue(executeRequest(successCallback, errorCallback, mainThread))
    }

    private fun <T> executeRequest(successCallback: (T) -> Unit,
                                   errorCallback: (StarWarsException) -> Unit,
                                   mainThread: Boolean = true): Callback<T> {
        return object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                val body: T? = response.body()
                if (response.isSuccessful && body != null) {
                    callbackExecutor.executeCallback(mainThread) {
                        successCallback(body)
                    }
                } else {
                    callbackExecutor.executeCallback(mainThread) {
                        errorCallback(StarWarsException("Network error code: ${response.code()}. Message: ${response.errorBody()}"))
                    }
                }
            }

            override fun onFailure(call: Call<T>, error: Throwable) {
                callbackExecutor.executeCallback(mainThread) {
                    errorCallback(StarWarsException("Internal SDK error", error))
                }
            }
        }
    }
}