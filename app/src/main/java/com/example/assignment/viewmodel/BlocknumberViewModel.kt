package com.example.assignment.viewmodel


import com.example.assignment.Webservices.Repository
import com.example.assignment.base.BaseViewmodel


class BlocknumberViewModel(val repository:Repository):BaseViewmodel() {
    /*suspend fun changePassword(_jsonObject: JsonObject, authToken: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                var data: Baseresponse<Token?>?=null
                try {
                    data = repository.changePassword(_jsonObject, authToken)
                    responseLiveData.postValue(ApiResponse.success(data))
                } catch (t: Throwable) {
                    responseLiveData.postValue(ApiResponse.error(t,data))
                }
            }
        }
    }*/
}