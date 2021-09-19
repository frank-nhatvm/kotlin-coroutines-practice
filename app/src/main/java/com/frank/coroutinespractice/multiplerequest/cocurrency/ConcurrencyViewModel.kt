package com.frank.coroutinespractice.multiplerequest.cocurrency


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.frank.coroutinespractice.base.BaseViewModel
import com.frank.coroutinespractice.common.Logger
import kotlinx.coroutines.*
import java.lang.Exception

data class Loading(var loadingBanner: Boolean = true, var loadingLatestProduct: Boolean = true)

class ConcurrencyViewModel : BaseViewModel() {

    private var parentJob: Job? = null

    override fun fetchData() {

        parentJob = viewModelScope.launch(parentExceptionHandler) {
            Logger.log("Show loading")

            val result = repository.requestWithIndex(1)
            withContext(NonCancellable){
                repository.saveToDb(result*6)
            }
        }

        parentJob?.invokeOnCompletion {
            // show error
            Logger.log("Hide loading")
        }
    }

    private suspend fun saveToDB() {

    }


}