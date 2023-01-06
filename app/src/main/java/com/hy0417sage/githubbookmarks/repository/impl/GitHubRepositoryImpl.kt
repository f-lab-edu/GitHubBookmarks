package com.hy0417sage.githubbookmarks.repository.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hy0417sage.githubbookmarks.network.GitHubService
import com.hy0417sage.githubbookmarks.repository.GitHubRepository
import com.hy0417sage.githubbookmarks.repository.data.GitHub
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

class GitHubRepositoryImpl @Inject constructor(private val retrofit: Retrofit) : GitHubRepository {

    val wholeGithubData: MutableLiveData<List<GitHub.Item>> = MutableLiveData()
    private val gitHubService by lazy {
        retrofit.create(GitHubService::class.java)
    }

    override fun wholeGitHubData(): LiveData<List<GitHub.Item>> {
        return wholeGithubData
    }

    override fun loadGithubPage(apiKey: String) {
        return gitHubService.getGitHubService(apiKey).enqueue(object : Callback<GitHub> {
            override fun onResponse(
                call: Call<GitHub>,
                response: Response<GitHub>
            ) {
                if (response.isSuccessful) {
                    wholeGithubData.value = response.body()?.items
                }
            }

            override fun onFailure(call: Call<GitHub>, t: Throwable) {

            }
        })
    }
}