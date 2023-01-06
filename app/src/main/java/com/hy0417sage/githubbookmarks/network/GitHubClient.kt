package com.hy0417sage.githubbookmarks.network

import retrofit2.Retrofit

interface GitHubClient{
    fun getGitHubBaseURL() : Retrofit
}