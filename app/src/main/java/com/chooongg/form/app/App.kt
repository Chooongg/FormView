package com.chooongg.form.app

import android.app.Application

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        FlipperInitializer.initialize(this)
    }
}