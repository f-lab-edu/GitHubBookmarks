package com.hy0417sage.githubbookmarks.repository

import androidx.lifecycle.LiveData
import com.hy0417sage.githubbookmarks.repository.data.GitHub

interface GitHubRepository {
    fun wholeGitHubData(): LiveData<List<GitHub.Item>>
    fun loadGithubPage(apiKey: String)
}