package com.frank.coroutinespractice.multiplerequest.sequential

import androidx.lifecycle.viewModelScope
import com.frank.coroutinespractice.base.BaseViewModel
import com.frank.coroutinespractice.common.Logger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class SequentialViewModel : BaseViewModel() {


    override fun fetchData() {
       val parentJob =  viewModelScope.launch(parentExceptionHandler) {
            Logger.log("Show loading ${Thread.currentThread().name}")

            repository.requestWithIndex(1, true)

            repository.requestWithIndex(2)


        }

        parentJob.invokeOnCompletion {
            Logger.log("hide loading ${Thread.currentThread().name}")
        }
    }


}