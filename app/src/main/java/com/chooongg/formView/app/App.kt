package com.chooongg.formView.app

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.chooongg.manager.NightModeManager

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        NightModeManager.setNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        FlipperInitializer.initialize(this)
    }
}