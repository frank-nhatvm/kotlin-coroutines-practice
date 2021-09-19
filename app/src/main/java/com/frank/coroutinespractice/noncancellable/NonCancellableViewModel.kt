package com.frank.coroutinespractice.noncancellable

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.frank.coroutinespractice.base.BaseViewModel
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NonCancellableViewModel : BaseViewModel() {

    var data = MutableLiveData<Int>()
        private set

    override fun fetchData() {
        parentJob = viewModelScope.launch(parentExceptionHandler) {
            showLoading(true)

            // call to the server side and get the data
            val serverSideData = repository.requestWithIndex(1,false)

            // show it in the UI
            data.postValue(serverSideData)

            // save it in the database
            withContext(NonCancellable){
                repository.saveToDB(serverSideData)
            }

        }

        registerEventParentJobFinish()
    }

}