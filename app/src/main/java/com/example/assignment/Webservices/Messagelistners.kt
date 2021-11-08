package com.example.assignment.Webservices

import android.telephony.SmsMessage

interface Messagelistners {
    fun getsmsm(msg: SmsMessage, msgbody: String?)
}