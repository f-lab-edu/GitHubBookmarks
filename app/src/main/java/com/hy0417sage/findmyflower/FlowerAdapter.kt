package com.hy0417sage.findmyflower

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hy0417sage.findmyflower.databinding.ItemFlowerBinding
import com.hy0417sage.findmyflower.db.FlowerEntity

class FlowerAdapter :
    RecyclerView.Adapter<FlowerAdapter.MyViewHolder>() {

    private var flowerList: List<FlowerEntity> = ArrayList()
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

    override fun getItemCount(): Int = flowerList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val flowerData = flowerList[position]
        holder.textView.text = flowerData.text
        holder.imageView.clipToOutline = true
        Glide.with(holder.itemView).load(flowerData.img).into(holder.imageView)

        holder.itemView.setOnClickListener{
            itemClickListener?.onClick(position)
        }
    }

    fun updateFlowerList(flowerList: List<FlowerEntity>) {
        this.flowerList = flowerList
        notifyDataSetChanged()
    }

    fun deleteFlowerItem(position: Int) {
//        this.flowerList.remove(position)
        notifyDataSetChanged()
    }

    fun interface OnItemClickListener {
        fun onClick(position: Int)
    }

    fun setItemClickListener(onItemClickListener: OnItemClickListener) {
        this.itemClickListener = onItemClickListener
    }
}