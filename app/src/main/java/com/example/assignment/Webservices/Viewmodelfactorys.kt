package com.example.assignment.Webservices

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.assignment.viewmodel.*
import java.lang.IllegalArgumentException
import javax.inject.Inject

// TODO: 11/23/20 created by jitedra singh viewmodel factory class to make an repository class from a single place

class Viewmodelfactorys @Inject constructor(val repository: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(repository) as T
        }
//        else if (modelClass.isAssignableFrom(Apiviewmodel::class.java)){
//            return Apiviewmodel(repository) as T
//        }
            throw IllegalArgumentException("Please creates Viewmodel")
    }
}