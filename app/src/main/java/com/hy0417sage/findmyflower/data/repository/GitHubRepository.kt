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

    fun loadGithub(page: String, callback: MainActivity){
        val call = GithubClient.gitHubService.getGitHubPage(page.toString())

        call.enqueue(object : Callback<GitHubData> {
            override fun onResponse(
                call: Call<GitHubData>,
                response: Response<GitHubData>
            ) {
                if(response.isSuccessful){
                    Toast.makeText(callback,"성공 : " + response.body()?.userId ,Toast.LENGTH_SHORT).show()
                    Log.d("callback", "성공 : " + response.body()?.userId)
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