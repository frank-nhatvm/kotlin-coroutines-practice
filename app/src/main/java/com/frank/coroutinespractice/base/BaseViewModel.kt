package com.frank.coroutinespractice.base

import androidx.lifecycle.ViewModel
import com.frank.coroutinespractice.data.repositories.FakeRepository
import kotlinx.coroutines.Dispatchers

open class BaseViewModel: ViewModel() {

    protected var repository: FakeRepository = FakeRepository(Dispatchers.IO)

    open fun fetchData() {


    }

}