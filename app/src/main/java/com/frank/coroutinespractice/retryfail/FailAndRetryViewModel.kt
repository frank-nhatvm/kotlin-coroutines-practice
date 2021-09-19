package com.frank.coroutinespractice.retryfail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.frank.coroutinespractice.base.BaseViewModel
import kotlinx.coroutines.launch
import java.util.logging.Logger

class FailAndRetryViewModel : BaseViewModel() {

    var data = MutableLiveData<Int>()
        private set

    override fun fetchData() {

        parentJob = viewModelScope.launch(parentExceptionHandler) {
            showLoading(true)

           failAndRetry(3)

        }

        registerEventParentJobFinish()

    }

    private suspend fun failAndRetry(repeatTime: Int): Int{
        repeat(repeatTime){ index ->
            try {
                val isFail = (index != 2)
                return repository.requestWithIndex(1,isFail)
            }
            catch (e: Exception){
                com.frank.coroutinespractice.common.Logger.log(message = " ${e.message}")
            }
        }
        return repository.requestWithIndex(1,true)
    }


}