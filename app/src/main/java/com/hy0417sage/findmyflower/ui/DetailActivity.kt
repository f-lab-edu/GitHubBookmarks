package com.hy0417sage.findmyflower.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.hy0417sage.findmyflower.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity()  {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userId = intent.getStringExtra("userId")
        val userProfileImg = intent.getStringExtra("userProfileImg")
        val userGitHubPage = intent.getStringExtra("userGitHubPage")

        binding.userId.text = userId
        Glide.with(binding.userProfile).load(userProfileImg).into(binding.userProfile)
//        binding.githubPage.setOnClickListener {  }
    }
}