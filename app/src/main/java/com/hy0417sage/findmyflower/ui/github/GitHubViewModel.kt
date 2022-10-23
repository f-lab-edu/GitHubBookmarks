package com.hy0417sage.findmyflower.ui.github

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.hy0417sage.findmyflower.data.model.GitHub
import com.hy0417sage.findmyflower.data.repository.GitHubRepository

class GitHubViewModel : ViewModel() {

    private val gitHubData : LiveData<List<GitHub.Item>>
    private val gitHubRepository = GitHubRepository()

    init {
        getGitHubData()
        gitHubData = gitHubRepository.getGithubData
    }

    private fun getGitHubData(){
        gitHubRepository.loadGithub("tom+repos:%3E42+followers:%3E1000")
    }

    fun getAll() : LiveData<List<GitHub.Item>>{
        return gitHubData
    }
}