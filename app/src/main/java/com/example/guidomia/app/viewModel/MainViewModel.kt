package com.example.guidomia.app.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.guidomia.data.Repository
import com.example.guidomia.model.CarItem
import kotlinx.coroutines.*

class MainViewModel constructor(val repository: Repository) : ViewModel(){

    val errorMessage = MutableLiveData<String>()
    val carList = MutableLiveData<ArrayList<CarItem>>()
    var job: Job? = null

    fun getCarList() {
        job = CoroutineScope(Dispatchers.IO).launch {
            val response = repository.getCarList()
            withContext(Dispatchers.Main) {
                if (response != null) {
                    carList.value = response

                }
                else {
                   onError("Error")
                }
            }
        }
    }

    private fun onError(message: String) {
        errorMessage.value = message

    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}