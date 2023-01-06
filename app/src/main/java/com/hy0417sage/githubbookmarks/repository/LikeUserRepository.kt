package com.hy0417sage.githubbookmarks.repository

import androidx.lifecycle.LiveData
import com.hy0417sage.githubbookmarks.repository.data.LikeUserEntity

interface LikeUserRepository {
    fun wholeLikeUserData(): LiveData<List<LikeUserEntity>>
    suspend fun insertLikeUser(likeUserEntity: LikeUserEntity)
    suspend fun deleteLikeUser(likeUserEntity: LikeUserEntity)
    fun searchLikeUserDB(searchQuery: String): LiveData<List<LikeUserEntity>>
}