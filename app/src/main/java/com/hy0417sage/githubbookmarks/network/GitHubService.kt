package com.hy0417sage.githubbookmarks.network

import com.hy0417sage.githubbookmarks.repository.data.GitHub
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GitHubService {
    //https://api.github.com/search/users?q=tom+repos:%3E42+followers:%3E1000
    @GET("/search/users?")
    fun getGitHubService(@Query(value = "q", encoded = true) q: String?): Call<GitHub>
}