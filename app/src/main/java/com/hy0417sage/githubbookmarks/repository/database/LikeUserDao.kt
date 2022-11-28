package com.hy0417sage.githubbookmarks.repository.database

import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.hy0417sage.githubbookmarks.repository.data.LikeUserEntity

@Dao
interface LikeUserDao {
    @Query("SELECT * FROM LikeUser")
    fun getLikeUser(): MutableLiveData<List<LikeUserEntity>>

    @Insert
    fun insertUser(user: LikeUserEntity)

    @Delete
    fun deleteUser(user: LikeUserEntity)
}