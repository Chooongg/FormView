package com.chooongg.formView.app

import android.app.Application

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        FlipperInitializer.initialize(this)
    }
}