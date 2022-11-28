package com.hy0417sage.githubbookmarks.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.hy0417sage.githubbookmarks.network.GitHubClient
import com.hy0417sage.githubbookmarks.repository.data.GitHub
import com.hy0417sage.githubbookmarks.repository.GitHubRepository
import com.hy0417sage.githubbookmarks.repository.data.LikeUserEntity
import androidx.lifecycle.viewModelScope
import com.hy0417sage.githubbookmarks.repository.database.LikeUserDataBase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GitHubViewModel(application: Application): ViewModel() {

    private val likeUserDao = LikeUserDataBase.getInstance(application).getLikeUserDao()
    private val likeUserData: LiveData<List<LikeUserEntity>>

    private val gitHubRepository = GitHubRepository(GitHubClient.getGitHubBaseURL(), likeUserDao)
    private val gitHubUserData: LiveData<List<GitHub.Item>> //여기에 github 데이터 다 있음!! 여기서 room 데이터 검사하기!!

    init {
        loadGithubPage()
        gitHubUserData = gitHubRepository.getGithubData
        likeUserData = gitHubRepository.getLikeUserData
    }

    private fun loadGithubPage() =
        gitHubRepository.loadGithub("tom+repos:%3E42+followers:%3E1000")

    fun getGitHubUserData() = gitHubUserData

    fun getLikeUserData() = likeUserData

    suspend fun insertFlowerData(likeUserEntity: LikeUserEntity){
        viewModelScope.launch(Dispatchers.IO) {
            gitHubRepository.insertLikeUser(likeUserEntity)
        }
    }

    suspend fun deleteFlowerData(likeUserEntity: LikeUserEntity){
        viewModelScope.launch(Dispatchers.IO) {
            gitHubRepository.deleteLikeUser(likeUserEntity)
        }
    }
}
