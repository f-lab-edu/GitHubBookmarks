package com.hy0417sage.githubbookmarks

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.hy0417sage.githubbookmarks.databinding.ActivityUserDetailBinding

class UserDetailActivity : AppCompatActivity() {

    private val TAG = "UserDetailActivity"
    private lateinit var binding: ActivityUserDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_user_detail)

        val userId = intent.getStringExtra("userId")
        val userProfileImg = intent.getStringExtra("userProfileImg")
        val userGitHubPage = intent.getStringExtra("userGitHubPage")

        Log.d(TAG, "${userId.toString()} ${userProfileImg.toString()} ${userGitHubPage.toString()}")

        binding.userId.text = userId
        Glide.with(binding.profileImage).load(userProfileImg).into(binding.profileImage)

        binding.goGitHubButton.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(userGitHubPage)))
        }
    }
}