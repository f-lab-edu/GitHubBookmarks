package com.hy0417sage.githubbookmarks.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hy0417sage.githubbookmarks.repository.data.GitHub
import com.hy0417sage.githubbookmarks.network.GitHubClient
import com.hy0417sage.githubbookmarks.network.GitHubService
import com.hy0417sage.githubbookmarks.repository.data.LikeUserEntity
import com.hy0417sage.githubbookmarks.repository.database.LikeUserDao
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class GitHubRepository(retrofit: Retrofit, private val likeUserDao: LikeUserDao) {

    val getGithubData: MutableLiveData<List<GitHub.Item>> = MutableLiveData()
    val getLikeUserData: MutableLiveData<List<LikeUserEntity>> = likeUserDao.getLikeUser()

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

    suspend fun insertLikeUser(likeUserEntity: LikeUserEntity){
        likeUserDao.insertUser(likeUserEntity)
    }

    suspend fun deleteLikeUser(likeUserEntity: LikeUserEntity){
        likeUserDao.deleteUser(likeUserEntity)
    }
}