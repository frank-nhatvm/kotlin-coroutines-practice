package com.frank.coroutinespractice.singlerequest


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.frank.coroutinespractice.base.BaseViewModel
import kotlinx.coroutines.launch

class SingleViewModel : BaseViewModel() {

    var data = MutableLiveData<Int>()
        private set


    override fun fetchData() {
        val job =  viewModelScope.launch(parentExceptionHandler) {
            showLoading(true)
            val result =  repository.requestWithIndex(1,true)
            data.postValue(result)

        }

        job.invokeOnCompletion {
            showLoading(false)
        }

    }


}