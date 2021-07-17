package com.example.starwars.client

import com.example.starwars.model.StarWarsException
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

/**
 * RequestExecutor is a internal class responsible to centralize a logic of all REST calls
 */
class RequestExecutor(private val mainThreadExecutor: MainThreadExecutor) {

    fun <T> executeRequest(
        successCallback: (T) -> Unit,
        errorCallback: (StarWarsException) -> Unit,
        mainThread: Boolean = true
    ): Callback<T> {
        return object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                val body: T? = response.body()
                if (response.isSuccessful && body != null) {
                    if (mainThread) {
                        mainThreadExecutor.executeOnMainThread { successCallback(body) }
                    } else {
                        successCallback(body)
                    }
                } else {
                    Timber.e("Network error code: ${response.code()}. Message: ${response.errorBody()}")
                    if (mainThread) {
                        mainThreadExecutor.executeOnMainThread { errorCallback(StarWarsException("Network error code: ${response.code()}. Message: ${response.errorBody()}")) }
                    } else {
                        errorCallback(StarWarsException("Network error code: ${response.code()}. Message: ${response.errorBody()}"))
                    }
                }
            }

            override fun onFailure(call: Call<T>, error: Throwable) {
                Timber.e(error, "Internal SDK error")
                if (mainThread) {
                    mainThreadExecutor.executeOnMainThread {
                        errorCallback(
                            StarWarsException(
                                "Internal SDK error",
                                error
                            )
                        )
                    }
                } else {
                    errorCallback(StarWarsException("Internal SDK error", error))
                }
            }
        }
    }
}