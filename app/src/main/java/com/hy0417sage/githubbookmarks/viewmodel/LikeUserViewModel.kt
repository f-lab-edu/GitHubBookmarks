package com.hy0417sage.githubbookmarks.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hy0417sage.githubbookmarks.repository.LikeUserRepository
import com.hy0417sage.githubbookmarks.repository.data.LikeUserEntity
import com.hy0417sage.githubbookmarks.repository.database.LikeUserDataBase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LikeUserViewModel(application: Application) : ViewModel() {

    private val likeUserDao = LikeUserDataBase.getInstance(application).getLikeUserDao()
    private val likeUserData: LiveData<List<LikeUserEntity>>
    private val likeUserRepository = LikeUserRepository(likeUserDao)

    init {
        likeUserData = likeUserRepository.getLikeUserData
    }

    fun getLikeUserData() = likeUserData

    fun insertLikeUserData(likeUserEntity: LikeUserEntity){
        viewModelScope.launch(Dispatchers.IO) {
            likeUserRepository.insertLikeUser(likeUserEntity)
        }
    }

    fun deleteLikeUserData(likeUserEntity: LikeUserEntity){
        viewModelScope.launch(Dispatchers.IO) {
            likeUserRepository.deleteLikeUser(likeUserEntity)
        }
    }

    fun searchDatabase(searchQuery: String): LiveData<List<LikeUserEntity>> {
        return likeUserRepository.searchDatabase(searchQuery)
    }
}