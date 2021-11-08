package com.example.assignment.Webservices

import com.google.gson.JsonObject
import com.example.assignment.model.*
class Repository(private val apiservices: Apiservices) {
    suspend fun callSearchApi(jsonObject: JsonObject): Baseresponse<MainResponse?>? {
        return apiservices.getToken(jsonObject)
    }

}