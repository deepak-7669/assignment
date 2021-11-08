package com.example.assignment.Webservices

import android.util.Log
import com.google.gson.Gson
import com.example.assignment.model.Baseresponse
import com.example.assignment.model.MainResponse
import retrofit2.HttpException
import java.io.IOException

class ApiResponse //  @Nullable
//  public Context context;

private constructor(val status: Status, val data: Baseresponse<*>?, val error: Throwable?) {

    companion object {
        @JvmStatic
        fun loading(): ApiResponse {
            Log.d("APIRESPONSE", "LOADING")
            return ApiResponse(Status.LOADING, null, null)
        }

        @JvmStatic
        fun success(data: Baseresponse<*>): ApiResponse {
            Log.d("APIRESPONSE", "RESponse")
            return ApiResponse(Status.SUCCESS, data, null)
        }

        @JvmStatic
        fun error(error: Throwable, data: Baseresponse<MainResponse?>?): ApiResponse {
            var datasss:Baseresponse<Any?>? =null
            when(error){
                is IOException->{}
                is HttpException->{
                    if (error.code()==502 || error.code()==504){
                        Log.d("Badgateway getting =>",error.code().toString())
                    }else{
                        val body = error.response()?.errorBody()
                        val adapter = Gson().getAdapter(Baseresponse::class.java)
                        val errorParser = adapter.fromJson(body?.string())
                        datasss = errorParser as Baseresponse<Any?>?
                    }
                }
            }
            Log.d("APIRESPONSE", "ERROR data = " + data)
//            if (error.cause != null && error.cause is UnknownHostException) {
//                Log.d("error", "Network error")
//            } else if (error.cause == null) {
//                if ((error as HttpException).response()!!.code() == 401) {
//                    //Utility.Logout(activity)
//                } else if ((error as HttpException).response()!!.code() == 405) {
//                }
//            }
            Log.d("APIRESPONSE", error.message.toString())
            Log.d("gettting", "Error")
            return ApiResponse(Status.ERROR, datasss, error)
        }
    }
}