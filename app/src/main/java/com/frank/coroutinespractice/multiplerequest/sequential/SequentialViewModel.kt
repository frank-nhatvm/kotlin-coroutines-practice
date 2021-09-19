package com.frank.coroutinespractice.multiplerequest.sequential

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.frank.coroutinespractice.base.BaseViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope

class SequentialViewModel : BaseViewModel() {

    var data1 = MutableLiveData<Int>()
        private set

    var data2 = MutableLiveData<Int>()
        private set

    override fun fetchData() {
        parentJob = viewModelScope.launch(parentExceptionHandler) {
            showLoading(true)

            // get the product's information

            val productInfo = repository.requestWithIndex(1, true)
            data1.postValue(productInfo)


            // product sku
            // get the options for the product

            val productOptions = repository.requestWithIndex(2, false)
            data2.postValue(productOptions)


        }

        registerEventParentJobFinish()

    }

}