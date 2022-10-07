package com.hy0417sage.findmyflower.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object GithubClient {

    //https://api.github.com/search/users?q=tom+repos:%3E42+followers:%3E1000

    private const val baseUrl = "https://api.github.com/"
    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val gitHubService = retrofit.create(GitHubService::class.java)


}