package com.hy0417sage.findmyflower.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hy0417sage.findmyflower.databinding.ItemFlowerBinding
import com.hy0417sage.findmyflower.data.model.FlowerEntity
import com.hy0417sage.findmyflower.data.model.GitHub

class GithubAdapter :
    RecyclerView.Adapter<GithubAdapter.MyViewHolder>() {

    private var flowerList: List<FlowerEntity> = ArrayList()
    private var gitHubList: List<GitHub.Item> = ArrayList()
    private var itemClickListener: OnItemClickListener? = null

    class MyViewHolder(binding: ItemFlowerBinding) : RecyclerView.ViewHolder(binding.root) {
        val imageView = binding.imageView
        val textView = binding.textView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding: ItemFlowerBinding =
            ItemFlowerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int = gitHubList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val flowerData = gitHubList?.get(position)
        holder.textView.text = flowerData?.userId
        holder.imageView.clipToOutline = true
        Glide.with(holder.itemView).load(flowerData?.userProfileImg).into(holder.imageView)

        holder.itemView.setOnClickListener{
            itemClickListener?.onClick(position)
        }
    }

    fun updateGitHudList(gitHubList: List<GitHub.Item>) {
        this.gitHubList = gitHubList
        notifyDataSetChanged()
    }

    fun getItemGitHudData(position: Int) : GitHub.Item = gitHubList[position]

    fun interface OnItemClickListener {
        fun onClick(position: Int)
    }

    fun setItemClickListener(onItemClickListener: OnItemClickListener) {
        this.itemClickListener = onItemClickListener
    }
}