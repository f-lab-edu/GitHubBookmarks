package com.hy0417sage.githubbookmarks

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.*
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.hy0417sage.githubbookmarks.repository.data.GitHub
import com.hy0417sage.githubbookmarks.view.GitHubAdapter
import com.hy0417sage.githubbookmarks.view.GitHubBestAdapter
import com.hy0417sage.githubbookmarks.viewmodel.GitHubViewModel
import com.hy0417sage.githubbookmarks.databinding.ActivityMainBinding
import com.hy0417sage.githubbookmarks.network.GitHubClient
import com.hy0417sage.githubbookmarks.repository.GitHubRepository
import com.hy0417sage.githubbookmarks.repository.data.LikeUserEntity
import com.hy0417sage.githubbookmarks.viewmodel.LikeUserViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val gitHubViewModel by lazy {
        ViewModelProvider(this, object : AbstractSavedStateViewModelFactory(this@MainActivity, null) {
            override fun <T : ViewModel> create(key: String, modelClass: Class<T>, handle: SavedStateHandle): T {
                val gitHubRepository = GitHubRepository(GitHubClient.getGitHubBaseURL())
                return GitHubViewModel(gitHubRepository) as T
            }
        })[GitHubViewModel::class.java]
    }

    private val likeUserViewModel by lazy {
        ViewModelProvider(this, object : AbstractSavedStateViewModelFactory(this@MainActivity, null) {
            override fun <T : ViewModel> create(key: String, modelClass: Class<T>, handle: SavedStateHandle): T {
                return LikeUserViewModel(application) as T
            }
        })[LikeUserViewModel::class.java]
    }

    private val allAdapter: GitHubAdapter = GitHubAdapter()
    private val bestAdapter: GitHubBestAdapter = GitHubBestAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        gitHubViewModel.getGitHubUserData().observe(this, Observer{ loadGithubUser ->
            allAdapter.updateGitHudUser(loadGithubUser)
        })

        likeUserViewModel.getLikeUserData().observe(this, Observer { loadLikeUser ->
            //TODO : Best Github User Show
            bestAdapter.updateLikeUser(loadLikeUser)
        })

        setRecyclerview()
        selectGitHubUser()
    }

    private fun selectGitHubUser() {
        allAdapter.setItemClickListener { position ->
            openDetailPage(allAdapter.getItemGitHudData(position))
        }
    }

    private fun openDetailPage(userData : GitHub.Item) {
        val goDetailView = Intent(this, UserDetailActivity::class.java)
        goDetailView.putExtra("userId", userData.userId)
        goDetailView.putExtra("userProfileImg", userData.userProfileImg)
        goDetailView.putExtra("userGitHubPage", userData.userGitHubPage)
        Log.d("MainActivity", "${userData.userId}, ${userData.userProfileImg}, ${userData.userGitHubPage}")
        startActivity(goDetailView)
    }

    private fun setRecyclerview(){
        val layoutManagerHorizontal = LinearLayoutManager(this)
        layoutManagerHorizontal.orientation = LinearLayoutManager.HORIZONTAL
        binding.bestGitHubUser.layoutManager = layoutManagerHorizontal
        binding.bestGitHubUser.adapter = bestAdapter
        binding.bestGitHubUser.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))

        binding.allGitHubUser.layoutManager = LinearLayoutManager(this)
        binding.allGitHubUser.adapter = allAdapter
        binding.allGitHubUser.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
    }
}