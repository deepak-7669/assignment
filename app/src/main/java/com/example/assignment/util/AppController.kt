package com.example.assignment.util

import android.app.Application
import android.content.SharedPreferences
import android.preference.PreferenceManager

class AppController : Application() {

    companion object {
        var currentfragment = ""
        var sharedPreferences: SharedPreferences? = null
        var latitude = 0.0
        var longitude = 0.0
        val SPEEDDIALCONTACT: String?="sppedDialcontact"
        const val first: String = "first"
        const val second: String = "second"
        const val ask: String = "ask"
        const val simPreference: String = "simprefrese"
    }

    override fun onCreate() {
        super.onCreate()

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(applicationContext)
    }

}