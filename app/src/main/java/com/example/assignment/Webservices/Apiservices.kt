package com.example.assignment.Webservices

import com.google.gson.JsonObject
import com.example.assignment.model.*
import retrofit2.http.*

// TODO: 11/21/20 Create by jitendra singh for sarv copyrights

interface Apiservices {
    @Headers("Content-Type: application/json")
    @POST("create_token")
    suspend fun getToken(@Body jsonBody: JsonObject): Baseresponse<MainResponse?>?//Response<Any?>?

}