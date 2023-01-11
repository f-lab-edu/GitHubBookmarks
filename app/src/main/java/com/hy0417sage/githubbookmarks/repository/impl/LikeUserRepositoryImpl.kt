package com.hy0417sage.githubbookmarks.repository.impl

import androidx.lifecycle.LiveData
import com.hy0417sage.githubbookmarks.repository.LikeUserRepository
import com.hy0417sage.githubbookmarks.repository.data.LikeUserEntity
import com.hy0417sage.githubbookmarks.repository.database.LikeUserDao
import javax.inject.Inject

class LikeUserRepositoryImpl @Inject constructor(private val likeUserDao: LikeUserDao) :
    LikeUserRepository {

    override fun wholeLikeUserData(): LiveData<List<LikeUserEntity>> {
        return likeUserDao.getLikeUser()
    }

    override suspend fun insertLikeUser(likeUserEntity: LikeUserEntity) {
        likeUserDao.insertUser(likeUserEntity)
    }

    override suspend fun deleteLikeUser(likeUserEntity: LikeUserEntity) {
        likeUserDao.deleteUser(likeUserEntity)
    }

    override fun searchLikeUserDB(searchQuery: String): LiveData<List<LikeUserEntity>> {
        return likeUserDao.searchDatabase(searchQuery)
    }
}