package com.example.assignment.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.assignment.Webservices.ApiResponse

open class BaseViewmodel : ViewModel() {
    val responseLiveData = MutableLiveData<ApiResponse>()
    fun apiResponse(): MutableLiveData<ApiResponse>?{
        return responseLiveData
    }

}