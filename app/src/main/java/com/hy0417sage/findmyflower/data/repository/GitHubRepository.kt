package com.hy0417sage.findmyflower.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.hy0417sage.findmyflower.data.model.GitHub
import com.hy0417sage.findmyflower.network.GithubClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class GitHubRepository {

    val getGithubData : MutableLiveData<List<GitHub.Item>> = MutableLiveData()

    fun loadGithub(key: String){
        val call = GithubClient.gitHubService.getGitHubPage(key)

        call.enqueue(object : Callback<GitHub> {
            override fun onResponse(
                call: Call<GitHub>,
                response: Response<GitHub>
            ) {
                if(response.isSuccessful){
                    Log.d("GitHubRepository", "성공 : " +
                            response.body()?.items)
                    getGithubData.value = response.body()?.items
                }else{
                    Log.d("GitHubRepository", "실패")
                }
            }

            override fun onFailure(call: Call<GitHub>, t: Throwable) {
                Log.d("GitHubRepository", "진짜 실패")
            }
        })
    }
}