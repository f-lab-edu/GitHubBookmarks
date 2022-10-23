package com.hy0417sage.findmyflower.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.hy0417sage.findmyflower.databinding.ActivityMainBinding
import com.hy0417sage.findmyflower.database.AppDatabase
import com.hy0417sage.findmyflower.database.FlowerDao
import com.hy0417sage.findmyflower.data.model.GitHub
import com.hy0417sage.findmyflower.ui.github.FlowerViewModel
import com.hy0417sage.findmyflower.ui.github.GitHubViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var db: AppDatabase
    private lateinit var flowerDao: FlowerDao
    private lateinit var flowerViewModel: FlowerViewModel
    private lateinit var gitHubViewModel : GitHubViewModel
    private val githubAdapter: GithubAdapter = GithubAdapter()
    private var index = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = AppDatabase.getInstance(this)!!
        flowerDao = db.getFlowerDao()

        binding.recyclerView.adapter = githubAdapter
        binding.recyclerView.layoutManager = GridLayoutManager(this, 2)

        gitHubViewModel = ViewModelProvider(this).get(GitHubViewModel::class.java)
        gitHubViewModel.getAll().observe(this, Observer { gitHubData ->
            githubAdapter.updateGitHudList(gitHubData)
        })

        initClickButton()
    }


    private fun clickItem() {
        githubAdapter.setItemClickListener { position ->
            openDetailPage(githubAdapter.getItemGitHudData(position))
        }
    }

    private fun openDetailPage(userData : GitHub.Item) {
        val goDetailView = Intent(this, DetailActivity::class.java)
        goDetailView.putExtra("userId", userData.userId)
        goDetailView.putExtra("userProfileImg", userData.userProfileImg)
        goDetailView.putExtra("userGitHubPage", userData.userGitHubPage)
        startActivity(goDetailView)
    }

    private fun changeLayoutManager() {
        index += 1
        when (index % 3) {
            0 -> binding.recyclerView.layoutManager = GridLayoutManager(this, 2)
            1 -> binding.recyclerView.layoutManager =
                StaggeredGridLayoutManager(3, LinearLayoutManager.VERTICAL)
            else -> binding.recyclerView.layoutManager = LinearLayoutManager(this)
        }
    }

    private fun initClickButton(){
        clickItem()
        binding.changeLayoutManagerButton.setOnClickListener {
            changeLayoutManager()
        }
    }

}