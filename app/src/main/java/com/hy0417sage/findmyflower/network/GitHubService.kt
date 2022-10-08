package com.hy0417sage.findmyflower.network

import com.hy0417sage.findmyflower.github.GitHubData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GitHubService {
    //https://api.github.com/search/users?q=tom+repos:%3E42+followers:%3E1000
    @GET("/search/users?")
    fun getGitHubPage(@Query("q") q: String?): Call<GitHubData>
}