package com.hy0417sage.findmyflower.data.repository

import android.util.Log
import android.widget.Toast
import com.hy0417sage.findmyflower.ui.MainActivity
import com.hy0417sage.findmyflower.github.GitHubData
import com.hy0417sage.findmyflower.network.GithubClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class GitHubRepository {

    fun loadGithub(key: String, repos: Int, followers: Int){
        val call = GithubClient.gitHubService.getGitHubPage(key, repos, followers)

        call.enqueue(object : Callback<GitHubData> {
            override fun onResponse(
                call: Call<GitHubData>,
                response: Response<GitHubData>
            ) {
                if(response.isSuccessful){
                    Log.d("callback", "성공 : " + response.body()?.total_count)
                }else{
                    Log.d("callback", "실패")
                }
            }

            override fun onFailure(call: Call<GitHubData>, t: Throwable) {
                Log.d("callback", "진짜 실패")
            }
        })


    }
}