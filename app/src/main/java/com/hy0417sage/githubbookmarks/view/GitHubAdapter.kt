package com.hy0417sage.githubbookmarks.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hy0417sage.githubbookmarks.repository.data.GitHub
import com.hy0417sage.githubbookmarks.databinding.AllUserlistLayoutBinding

class GitHubAdapter :
    RecyclerView.Adapter<GitHubAdapter.ViewHolder>() {

    private var gitHubList: List<GitHub.Item> = ArrayList()
    private var itemClickListener: OnItemClickListener? = null

    class ViewHolder(binding: AllUserlistLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        val profileImage = binding.profileImage
        val userId = binding.userId
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding: AllUserlistLayoutBinding =
            AllUserlistLayoutBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val gitHubData = gitHubList?.get(position)
        Glide.with(viewHolder.itemView).load(gitHubData?.userProfileImg).into(viewHolder.profileImage)
        viewHolder.userId.text = gitHubData?.userId

        viewHolder.itemView.setOnClickListener{
            itemClickListener?.onClick(position)
        }
    }

    override fun getItemCount() = gitHubList.size

    fun updateGitHudUser(gitHubList: List<GitHub.Item>) {
        this.gitHubList = gitHubList
        notifyDataSetChanged()
    }

    fun interface OnItemClickListener {
        fun onClick(position: Int)
    }

    fun setItemClickListener(onItemClickListener: OnItemClickListener) {
        this.itemClickListener = onItemClickListener
    }

    fun getItemGitHudData(position: Int) : GitHub.Item = gitHubList[position]
}



