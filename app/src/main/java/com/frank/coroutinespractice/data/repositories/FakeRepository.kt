package com.frank.coroutinespractice.data.repositories

import com.frank.coroutinespractice.common.Logger
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class FakeRepository(private  val dispatcher: CoroutineDispatcher) {

    suspend fun requestWithIndex(index: Int, isThrowException: Boolean = false): Int =
        withContext(dispatcher) {
            Logger.log("request with index $index at ${System.currentTimeMillis()} on thread ${Thread.currentThread().name}")
            val delayTime = index * 1000L
            delay(delayTime)
            if (isThrowException) {
                throw Exception("Exception with index $index")
            }
            Logger.log("Done with index $index")
            index
        }

    suspend fun saveToDB(data: Int) = withContext(dispatcher){
        Logger.log("Starting save data to db")
        delay(3000L)
        Logger.log("Saved the data to db: $data")
    }

}