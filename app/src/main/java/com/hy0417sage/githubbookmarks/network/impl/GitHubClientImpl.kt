package com.hy0417sage.githubbookmarks.network.impl

import com.hy0417sage.githubbookmarks.network.GitHubClient
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object GitHubClientImpl : GitHubClient {

    private const val BASE_URL = "https://api.github.com/"

    private val interceptor by lazy {
        HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        }
    }
    private val httpClient by lazy {
        OkHttpClient.Builder().addInterceptor(interceptor).build()
    }

    private val client = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(httpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    override fun getGitHubBaseURL(): Retrofit = client
}