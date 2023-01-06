package com.hy0417sage.githubbookmarks.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hy0417sage.githubbookmarks.databinding.BestUserlistLayoutBinding
import com.hy0417sage.githubbookmarks.repository.data.LikeUserEntity

class GitHubBestAdapter :
    RecyclerView.Adapter<GitHubBestAdapter.ViewHolder>() {

    private var likeUserList: List<LikeUserEntity> = ArrayList()

    class ViewHolder(binding: BestUserlistLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        val imageView = binding.imageView
        val userId = binding.userId
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding: BestUserlistLayoutBinding =
            BestUserlistLayoutBinding.inflate(
                LayoutInflater.from(viewGroup.context),
                viewGroup,
                false
            )

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val likeUserListData = likeUserList?.get(position)
        Glide.with(viewHolder.itemView).load(likeUserListData?.userProfileImg)
            .into(viewHolder.imageView)
        viewHolder.userId.text = likeUserListData?.userId

    }

    override fun getItemCount() = likeUserList.size

    fun updateLikeUser(likeUserList: List<LikeUserEntity>) {
        this.likeUserList = likeUserList
        notifyDataSetChanged()
    }

}



