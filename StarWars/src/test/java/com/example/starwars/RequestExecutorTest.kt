package com.example.starwars

import com.example.starwars.client.MainThreadExecutor
import com.example.starwars.client.RequestExecutor
import com.example.starwars.model.StarWarsException
import io.mockk.*
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class RequestExecutorTest {

    private lateinit var mainThreadExecutor: MainThreadExecutor
    private lateinit var requestExecutor: RequestExecutor

    @Before
    fun setUp() {
        mainThreadExecutor = mockk(relaxed = true)
        requestExecutor= RequestExecutor(mainThreadExecutor)
    }

    @Test
    fun whenExecuteARequestWithSuccessSerializableBodyAndInMainThreadShouldCallSuccessCallback() {
        val successCallback: (Unit) -> Unit = mockk(relaxed = true)
        val errorCallback: (StarWarsException) -> Unit = mockk(relaxed = true)
        val returnedCallback = requestExecutor.executeRequest(successCallback, errorCallback)

        val response: Response<Unit> = mockk()
        val responseBody: Unit = mockk()
        every { response.isSuccessful } returns true
        every { response.body() } returns responseBody

        returnedCallback.onResponse(mockk(), response)

        verify { mainThreadExecutor.executeOnMainThread(any()) }
    }

    @Test
    fun whenExecuteARequestWithSuccessSerializableBodyAndNotInMainThreadShouldCallSuccessCallback() {
        val successCallback: (Unit) -> Unit = mockk(relaxed = true)
        val errorCallback: (StarWarsException) -> Unit = mockk(relaxed = true)
        val returnedCallback = requestExecutor.executeRequest(successCallback, errorCallback, false)

        val response: Response<Unit> = mockk()
        val responseBody: Unit = mockk()
        every { response.isSuccessful } returns true
        every { response.body() } returns responseBody

        returnedCallback.onResponse(mockk(), response)

        verify(exactly = 0) { mainThreadExecutor.executeOnMainThread(any()) }
        verify { successCallback(responseBody) }
    }

    @Test
    fun whenExecuteARequestWithErrorInMainThreadShouldCallErrorCallback() {
        val successCallback: (Unit) -> Unit = mockk(relaxed = true)
        val errorCallback: (StarWarsException) -> Unit = mockk(relaxed = true)
        val returnedCallback = requestExecutor.executeRequest(successCallback, errorCallback)

        val response: Response<Unit> = mockk(relaxed = true)
        val responseBody: Unit = mockk()
        every { response.isSuccessful } returns false
        every { response.body() } returns responseBody

        returnedCallback.onResponse(mockk(), response)

        verify { mainThreadExecutor.executeOnMainThread(any()) }
    }

    @Test
    fun whenExecuteARequestWithErrorNotInMainThreadShouldCallSuccessCallback() {
        val successCallback: (Unit) -> Unit = mockk(relaxed = true)
        val errorCallback: (StarWarsException) -> Unit = mockk(relaxed = true)
        val returnedCallback = requestExecutor.executeRequest(successCallback, errorCallback, false)

        val response: Response<Unit> = mockk(relaxed = true)
        val responseBody: Unit = mockk()
        every { response.isSuccessful } returns false
        every { response.body() } returns responseBody

        returnedCallback.onResponse(mockk(), response)

        verify(exactly = 0) { mainThreadExecutor.executeOnMainThread(any()) }
        verify { errorCallback(any()) }
    }

    @Test
    fun whenExecuteARequestWithInternalErrorInMainThreadShouldCallErrorCallback() {
        val successCallback: (Unit) -> Unit = mockk(relaxed = true)
        val errorCallback: (StarWarsException) -> Unit = mockk(relaxed = true)
        val returnedCallback = requestExecutor.executeRequest(successCallback, errorCallback)

        returnedCallback.onFailure(mockk(), mockk())

        verify { mainThreadExecutor.executeOnMainThread(any()) }
    }

    @Test
    fun whenExecuteARequestWithInternalErrorNotInMainThreadShouldCallSuccessCallback() {
        val successCallback: (Unit) -> Unit = mockk(relaxed = true)
        val errorCallback: (StarWarsException) -> Unit = mockk(relaxed = true)
        val returnedCallback = requestExecutor.executeRequest(successCallback, errorCallback, false)

        returnedCallback.onFailure(mockk(), mockk())

        verify(exactly = 0) { mainThreadExecutor.executeOnMainThread(any()) }
        verify { errorCallback(any()) }
    }
}