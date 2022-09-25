package com.hy0417sage.findmyflower.db

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FlowerViewModel (application: Application): AndroidViewModel(application) {

    val getAllFlowerData: LiveData<List<FlowerEntity>>
    private val repository: FlowerRepository

    init {
        val flowerDao = AppDatabase.getInstance(application)!!.getFlowerDao()
        repository = FlowerRepository(flowerDao)
        getAllFlowerData = repository.getAllFlowerData
    }

    fun insertFlowerData(flowerEntity: FlowerEntity){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertFlower(flowerEntity)
        }
    }

    fun deleteFlowerData(flowerEntity: FlowerEntity){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteFlower(flowerEntity)
        }
    }

}
