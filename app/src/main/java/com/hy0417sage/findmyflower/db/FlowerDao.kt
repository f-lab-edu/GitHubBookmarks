package com.hy0417sage.findmyflower.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FlowerDao {

    @Query("SELECT * FROM FlowerEntity")
    fun getAll(): List<FlowerEntity>

    @Insert
    fun insertFlower(flower: FlowerEntity)

    @Delete
    fun deleteFlower(flower: FlowerEntity)
}