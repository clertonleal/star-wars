package com.example.starwars.client

import com.example.starwars.CallbackExecutor
import com.example.starwars.model.People
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
                      mainThread: Boolean = true,) {
        startWarsNetwork.getPeople(id).enqueue(object : Callback<People> {
            override fun onResponse(call: Call<People>, response: Response<People>) {
                val body = response.body()
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

            override fun onFailure(call: Call<People>, error: Throwable) {
                callbackExecutor.executeCallback(mainThread) {
                    errorCallback(StarWarsException("Internal SDK error", error))
                }
            }
        })
    }

}