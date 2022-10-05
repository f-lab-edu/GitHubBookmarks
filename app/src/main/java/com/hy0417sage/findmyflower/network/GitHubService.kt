package com.hy0417sage.findmyflower.network

import com.hy0417sage.findmyflower.github.GitHubData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GitHubService {

//    @GET("/search/users?q=tom+repos:>42+followers:>{page}")
//    fun getGitHubPage(@Query("page") page: String): Call<GitHubData>

    @GET("/search/{page}")
    fun getGitHubPage(@Query("page") page: String): Call<GitHubData>
}

