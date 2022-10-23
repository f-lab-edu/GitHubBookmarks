package com.hy0417sage.findmyflower.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.hy0417sage.findmyflower.data.model.FlowerEntity

@Dao
interface FlowerDao {

    @Query("SELECT * FROM FlowerEntity")
    fun getAll(): LiveData<List<FlowerEntity>>

    @Insert
    suspend fun insertFlower(flowerEntity: FlowerEntity)

    @Delete
    suspend fun deleteFlower(flowerEntity: FlowerEntity)
}