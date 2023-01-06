package com.hy0417sage.githubbookmarks.repository

import androidx.lifecycle.LiveData
import com.hy0417sage.githubbookmarks.repository.data.LikeUserEntity
import com.hy0417sage.githubbookmarks.repository.database.LikeUserDao
import kotlinx.coroutines.flow.Flow

class LikeUserRepository(private val likeUserDao: LikeUserDao) {

    val getLikeUserData: LiveData<List<LikeUserEntity>> = likeUserDao.getLikeUser()

    suspend fun insertLikeUser(likeUserEntity: LikeUserEntity){
        likeUserDao.insertUser(likeUserEntity)
    }

    suspend fun deleteLikeUser(likeUserEntity: LikeUserEntity){
        likeUserDao.deleteUser(likeUserEntity)
    }

    fun searchLikeUserDB(searchQuery: String): LiveData<List<LikeUserEntity>> {
        return likeUserDao.searchDatabase(searchQuery)
    }

    fun searchDatabase(searchQuery: String): LiveData<List<LikeUserEntity>> {
        return likeUserDao.searchDatabase(searchQuery)
    }
}