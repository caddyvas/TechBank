package com.deep.techbank

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class TechBankApp : Application() {
    override fun onCreate() {
        super.onCreate()
        // Future initialization code will go here
    }
}
