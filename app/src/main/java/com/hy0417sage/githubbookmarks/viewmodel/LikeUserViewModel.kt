//package com.hy0417sage.githubbookmarks.viewmodel
//
//import android.app.Application
//import android.view.View
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.ViewModel
//import com.hy0417sage.githubbookmarks.repository.data.LikeUserEntity
//import com.hy0417sage.githubbookmarks.repository.database.LikeUserDataBase
//
//class LikeUserViewModel(application: Application) : ViewModel() {
//
//    private val likeUserDao = LikeUserDataBase.getInstance(application).getLikeUserDao()
//    private val likeUserData: LiveData<List<LikeUserEntity>>
//
//    init {
//        likeUserData = gitHubRepository.getLikeUserData
//    }
//
//}