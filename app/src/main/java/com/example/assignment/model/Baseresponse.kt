package com.example.assignment.model

open class Baseresponse<T>(var messages:String) {
    var success: Boolean? = null
    var code: Int? = null
    var message: String? = null
    var data: T? = null
}