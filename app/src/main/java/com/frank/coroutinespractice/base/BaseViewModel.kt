package com.frank.coroutinespractice.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.frank.coroutinespractice.common.Logger
import com.frank.coroutinespractice.data.repositories.FakeRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers


open class BaseViewModel: ViewModel() {

    protected var repository: FakeRepository = FakeRepository(Dispatchers.IO)

     var error = MutableLiveData<String>()
    protected set

    protected val parentExceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        Logger.log("Parent exception handler : ${throwable.message}")
        error.postValue(throwable.message)
    }

    open fun fetchData() {


    }

}