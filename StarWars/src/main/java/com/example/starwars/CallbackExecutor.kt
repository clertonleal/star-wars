package com.example.starwars

import android.os.Handler
import android.os.Looper

class CallbackExecutor() {
    fun executeCallback(mainThread: Boolean, block: () -> Unit) {
        if (mainThread) {
            Handler(Looper.getMainLooper()).post {
                block()
            }
        } else {
            block()
        }
    }
}