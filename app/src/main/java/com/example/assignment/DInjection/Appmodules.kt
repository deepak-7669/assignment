package com.example.assignment.DInjection

import android.app.Application
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
@Module()
class Appmodules(val application: Application) {
    @Provides
    @Singleton
    fun provideContext(): Application {
        return application
    }
}