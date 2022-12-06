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

class GitHubViewModel(private val gitHubRepository: GitHubRepository): ViewModel() {

    private val gitHubUserData: LiveData<List<GitHub.Item>>
    init {
        loadGithubPage()
        gitHubUserData = gitHubRepository.getGithubData
    }

    private fun loadGithubPage() =
        gitHubRepository.loadGithub("tom+repos:%3E42+followers:%3E1000")

    fun getGitHubUserData() = gitHubUserData

}
