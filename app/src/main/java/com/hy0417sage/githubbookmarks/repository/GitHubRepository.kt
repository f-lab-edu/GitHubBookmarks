package com.hy0417sage.githubbookmarks.repository

import androidx.lifecycle.MutableLiveData
import com.hy0417sage.githubbookmarks.repository.data.GitHub
import com.hy0417sage.githubbookmarks.network.GitHubService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

class GitHubRepository @Inject constructor(private val retrofit: Retrofit) {

    val getGithubData: MutableLiveData<List<GitHub.Item>> = MutableLiveData()

    private val gitHubService by lazy {
        retrofit.create(GitHubService::class.java)
    }

    fun loadGithub(apiKey: String) =
        gitHubService.getGitHubService(apiKey).enqueue(object : Callback<GitHub> {
            override fun onResponse(
                call: Call<GitHub>,
                response: Response<GitHub>
            ) {
                if (response.isSuccessful) {
                    getGithubData.value = response.body()?.items
                }
            }

            override fun onFailure(call: Call<GitHub>, t: Throwable) {
            }
        })
}