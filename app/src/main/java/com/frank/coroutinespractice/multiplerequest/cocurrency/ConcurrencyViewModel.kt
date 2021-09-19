package com.frank.coroutinespractice.multiplerequest.cocurrency


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.frank.coroutinespractice.base.BaseViewModel
import kotlinx.coroutines.launch


class ConcurrencyViewModel : BaseViewModel() {

    var listBanners = MutableLiveData<Int>()
        private set

    var listLatestProducts = MutableLiveData<Int>()
        private set

    override fun fetchData() {
        parentJob = viewModelScope.launch(parentExceptionHandler) {

            showLoading(true)

            // get the cart items
            launch {
                val banners = repository.requestWithIndex(1, false)
                listBanners.postValue(banners)
            }

            // get the total price
            launch {
                val products = repository.requestWithIndex(2, false)
                listLatestProducts.postValue(products)
            }

        }

        registerEventParentJobFinish()
    }


}