package com.chooongg.formView.app

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val holderScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        holderScope.launch {
            Log.e("Scope", "launch1")
            delay(2000)
            Log.e("Scope", "launch2")
        }
        holderScope.cancel()
        holderScope.launch {
            delay(2000)
            Log.e("Scope", "launch3")
        }
    }
}