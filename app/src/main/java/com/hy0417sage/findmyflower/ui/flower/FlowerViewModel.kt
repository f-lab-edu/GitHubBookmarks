package com.hy0417sage.findmyflower.ui.flower

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.hy0417sage.findmyflower.data.model.FlowerEntity
import com.hy0417sage.findmyflower.data.repository.FlowerRepository
import com.hy0417sage.findmyflower.database.AppDatabase
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
        viewModelScope.launch(Dispatchers.Default) {
            repository.insertFlower(flowerEntity)
        }
    }

    fun deleteFlowerData(flowerEntity: FlowerEntity){
        viewModelScope.launch(Dispatchers.Default) {
            repository.deleteFlower(flowerEntity)
        }
    }

}
