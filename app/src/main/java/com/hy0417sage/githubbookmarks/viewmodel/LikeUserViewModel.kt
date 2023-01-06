package com.hy0417sage.githubbookmarks.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hy0417sage.githubbookmarks.repository.LikeUserRepository
import com.hy0417sage.githubbookmarks.repository.data.LikeUserEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LikeUserViewModel(private val likeUserRepository: LikeUserRepository) : ViewModel() {

    fun wholeLikeUserData(): LiveData<List<LikeUserEntity>> {
        return likeUserRepository.getLikeUserData
    }

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