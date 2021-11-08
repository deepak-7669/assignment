package com.example.assignment


import android.app.Application
import com.arefbhrn.eprdownloader.EPRDownloader
import com.arefbhrn.eprdownloader.EPRDownloaderConfig

import com.example.assignment.DInjection.DaggerQuickscomponent
import com.example.assignment.DInjection.Quickscomponent


class Assignment : Application() {

    lateinit var quickscomponent: Quickscomponent


    override fun onCreate() {
        super.onCreate()
        quickscomponent = DaggerQuickscomponent.create()
        val config = EPRDownloaderConfig.newBuilder()
            .setDatabaseEnabled(true)
            .build()
        EPRDownloader.initialize(this, config)
    }

    fun getappcomponannet(): Quickscomponent {
        return quickscomponent
    }


}