package com.example.assignment.Webservices

import com.example.assignment.model.Baseresponse

// TODO: 11/24/20 make response cleared
interface Consumes {
    fun _consumerespo(apiResponse: ApiResponse) {
        when (apiResponse.status) {
            Status.LOADING -> {
            }
            Status.SUCCESS -> handleresponse(apiResponse.data)
            Status.ERROR -> {
                errorresponse(apiResponse.error, apiResponse.data)
            }
        }
    }

    fun errorresponse(error: Throwable?, data: Baseresponse<*>?)

    fun handleresponse(data: Baseresponse<*>?)
}