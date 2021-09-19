package com.frank.coroutinespractice.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.frank.coroutinespractice.common.Logger
import com.frank.coroutinespractice.data.repositories.FakeRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

open class BaseViewModel: ViewModel() {

    protected var repository: FakeRepository = FakeRepository(Dispatchers.IO)

     var error = MutableLiveData<String>()
    protected set

    var isShowLoading = MutableLiveData<Boolean>()
    protected set

    protected val parentExceptionHandler = CoroutineExceptionHandler{_,t ->
        val message = t.message ?: "Something went wrong"
        Logger.log("Parent Exception Handler $message")
        error.postValue(message)
    }

    protected var parentJob: Job? = null


    open fun fetchData() {


    }

    protected fun showLoading(isShow: Boolean){
        isShowLoading.postValue(isShow)
    }

    protected fun registerEventParentJobFinish(){
        parentJob?.invokeOnCompletion {
            Logger.log("Parent jOb finish")
            showLoading(false)
        }
    }


}