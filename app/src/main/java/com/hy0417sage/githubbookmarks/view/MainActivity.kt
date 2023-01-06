package com.hy0417sage.githubbookmarks.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.*
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.internal.bind.ReflectiveTypeAdapterFactory
import com.hy0417sage.githubbookmarks.R
import com.hy0417sage.githubbookmarks.view.UserDetailActivity.Companion.newIntent
import com.hy0417sage.githubbookmarks.repository.data.GitHub
import com.hy0417sage.githubbookmarks.viewmodel.GitHubViewModel
import com.hy0417sage.githubbookmarks.databinding.ActivityMainBinding
import com.hy0417sage.githubbookmarks.view.adapter.GitHubAdapter
import com.hy0417sage.githubbookmarks.view.adapter.GitHubBestAdapter
import com.hy0417sage.githubbookmarks.viewmodel.LikeUserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val gitHubViewModel: GitHubViewModel by viewModels()
    private val likeUserViewModel: LikeUserViewModel by viewModels()
    private val allAdapter: GitHubAdapter = GitHubAdapter()
    private val bestAdapter: GitHubBestAdapter = GitHubBestAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        initRecyclerview()

        gitHubViewModel.getGitHubUserData().observe(this, Observer { loadGithubUser ->
            allAdapter.updateGitHudUser(loadGithubUser)
        })

        likeUserViewModel.wholeLikeUserData().observe(this, Observer { loadLikeUser ->
            bestAdapter.updateLikeUser(loadLikeUser)
        })

        allAdapter.setItemClickListener { position ->
            goDetailAUser(allAdapter.getItemGitHudData(position))
        }
    }

    private fun goDetailAUser(userData: GitHub.Item) {
        val goDetailAUser = newIntent(this, userData.userId, userData.userProfileImg, userData.userGitHubPage)
        startActivity(goDetailAUser)
    }

    private fun initRecyclerview() {
        val layoutManagerHorizontal = LinearLayoutManager(this)
        layoutManagerHorizontal.orientation = LinearLayoutManager.HORIZONTAL
        binding.bestGitHubUser.apply {
            layoutManager = layoutManagerHorizontal
            adapter = bestAdapter
            addItemDecoration(
                DividerItemDecoration(
                    this@MainActivity,
                    LinearLayoutManager.VERTICAL
                )
            )
        }

        binding.allGitHubUser.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = allAdapter
            addItemDecoration(
                DividerItemDecoration(
                    this@MainActivity,
                    LinearLayoutManager.VERTICAL
                )
            )
        }
    }
}