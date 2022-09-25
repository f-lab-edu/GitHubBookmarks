package com.hy0417sage.findmyflower.db

import androidx.lifecycle.LiveData

class FlowerRepository (private val flowerDao: FlowerDao) {

    val getAllFlowerData: LiveData<List<FlowerEntity>> = flowerDao.getAll()

    suspend fun insertFlower(flowerEntity: FlowerEntity){
        flowerDao.insertFlower(flowerEntity)
    }

    suspend fun deleteFlower(flowerEntity: FlowerEntity){
        flowerDao.deleteFlower(flowerEntity)
    }
}