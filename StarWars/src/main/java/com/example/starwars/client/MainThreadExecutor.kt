package com.example.starwars.client

import android.os.Handler
import android.os.Looper

/**
 * Class responsible to execute callback on the Android Main Thread
 */
class MainThreadExecutor {

    fun executeOnMainThread(block: () -> Unit) {
        Handler(Looper.getMainLooper()).post {
            block()
        }
    }

}