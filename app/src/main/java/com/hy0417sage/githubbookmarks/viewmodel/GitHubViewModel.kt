package com.hy0417sage.githubbookmarks.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.hy0417sage.githubbookmarks.repository.data.GitHub
import com.hy0417sage.githubbookmarks.repository.GitHubRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GitHubViewModel @Inject constructor(private val gitHubRepository: GitHubRepository) :
    ViewModel() {

    init {
        loadGithubPage()
    }

    private fun loadGithubPage() {
        gitHubRepository.loadGithub("tom+repos:%3E42+followers:%3E1000")
    }

    fun getGitHubUserData(): LiveData<List<GitHub.Item>> {
        return gitHubRepository.getGithubData
    }

}
