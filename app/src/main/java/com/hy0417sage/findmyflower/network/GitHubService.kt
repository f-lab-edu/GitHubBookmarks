package com.hy0417sage.findmyflower.network

import com.hy0417sage.findmyflower.data.model.GitHub
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GitHubService {
    //https://api.github.com/search/users?q=tom+repos:%3E42+followers:%3E1000
    @GET("/search/users?")
    fun getGitHubPage(@Query(value = "q", encoded = true) q: String?): Call<GitHub>
}