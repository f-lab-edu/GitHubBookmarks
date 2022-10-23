package com.hy0417sage.findmyflower.data.repository

import androidx.lifecycle.LiveData
import com.hy0417sage.findmyflower.database.FlowerDao
import com.hy0417sage.findmyflower.data.model.FlowerEntity

class FlowerRepository (private val flowerDao: FlowerDao) {

    val getAllFlowerEntityData: LiveData<List<FlowerEntity>> = flowerDao.getAll()

    suspend fun insertFlower(flowerEntity: FlowerEntity){
        flowerDao.insertFlower(flowerEntity)
    }

    suspend fun deleteFlower(flowerEntity: FlowerEntity){
        flowerDao.deleteFlower(flowerEntity)
    }
}