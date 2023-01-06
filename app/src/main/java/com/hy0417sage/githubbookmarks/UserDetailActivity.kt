package com.hy0417sage.githubbookmarks

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.hy0417sage.githubbookmarks.databinding.ActivityUserDetailBinding
import com.hy0417sage.githubbookmarks.repository.data.LikeUserEntity
import com.hy0417sage.githubbookmarks.viewmodel.LikeUserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserDetailActivity : AppCompatActivity() {

    private val TAG = "UserDetailActivity"
    private lateinit var binding: ActivityUserDetailBinding
    private var checkLikeUser: Boolean = false

    private val likeUserViewModel: LikeUserViewModel by viewModels()

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

        likeUserViewModel.searchLikeUserDB(userId.toString()).observe(this, Observer { check ->
            if (check.isEmpty()) {
                binding.likeImage.setImageResource(R.drawable.empty_hreat)
            } else {
                binding.likeImage.setImageResource(R.drawable.full_hreat)
            }
            checkLikeUser = check.isNotEmpty()
        })

        binding.likeImage.setOnClickListener {
            if (checkLikeUser) {
//                likeUserViewModel.deleteLikeUserData()
            } else {
                likeUserViewModel.insertLikeUserData(
                    LikeUserEntity(
                        null,
                        userId,
                        userProfileImg
                    )
                )
            }
        }

    }
}