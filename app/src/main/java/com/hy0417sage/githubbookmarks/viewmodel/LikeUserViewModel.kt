package com.hy0417sage.githubbookmarks.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hy0417sage.githubbookmarks.repository.LikeUserRepository
import com.hy0417sage.githubbookmarks.repository.data.LikeUserEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LikeUserViewModel @Inject constructor(private val likeUserRepository: LikeUserRepository) :
    ViewModel() {

    fun wholeLikeUserData(): LiveData<List<LikeUserEntity>> {
        return likeUserRepository.wholeLikeUserData()
    }

    fun insertLikeUserData(likeUserEntity: LikeUserEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            likeUserRepository.insertLikeUser(likeUserEntity)
        }
    }

    fun deleteLikeUserData(likeUserEntity: LikeUserEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            likeUserRepository.deleteLikeUser(likeUserEntity)
        }
    }

    fun searchLikeUserDB(searchQuery: String): LiveData<List<LikeUserEntity>> {
        return likeUserRepository.searchLikeUserDB(searchQuery)
    }
}