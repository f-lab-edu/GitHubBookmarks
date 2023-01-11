package com.hy0417sage.githubbookmarks.repository.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.hy0417sage.githubbookmarks.repository.data.LikeUserEntity

@Dao
interface LikeUserDao {

    @Query("SELECT * FROM LikeUser")
    fun getLikeUser(): LiveData<List<LikeUserEntity>>

    @Query("SELECT * FROM LikeUser WHERE userId LIKE :searchQuery")
    fun searchDatabase(searchQuery: String): LiveData<List<LikeUserEntity>>

    @Insert
    suspend fun insertUser(user: LikeUserEntity)

    @Delete
    suspend fun deleteUser(user: LikeUserEntity)
}