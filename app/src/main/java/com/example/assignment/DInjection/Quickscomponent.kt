package com.example.assignment.DInjection
import com.example.assignment.activities.*

import dagger.Component
import javax.inject.Singleton
@Singleton
@Component(modules = [NetworkModule::class,Appmodules::class])
interface Quickscomponent {
    fun doinject(mainActivity: MainActivity)

}