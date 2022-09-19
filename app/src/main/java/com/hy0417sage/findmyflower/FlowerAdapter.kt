package com.hy0417sage.findmyflower

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hy0417sage.findmyflower.databinding.ItemFlowerBinding

class FlowerAdapter(private val flowerList: ArrayList<FlowerData>) :
    RecyclerView.Adapter<FlowerAdapter.MyViewHolder>() {

    //뷰홀더 객체를 생성해준다.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding: ItemFlowerBinding =
            ItemFlowerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    //데이터가 몇 개인지 변환해 주어야 한다. 리사이클뷰 아이템 개수 : 리스트의 크기
    override fun getItemCount(): Int = flowerList.size

    //받은 데이터를 onCreateViewHolder 에서 생성한 뷰 홀더 객체에 어떻게 넣어줄지 결정해 준다.
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val flowerData = flowerList[position]
        holder.imageView.clipToOutline = true
        holder.textView.text = flowerData.text
        Glide.with(holder.itemView).load(flowerData.img).into(holder.imageView)

        holder.itemView.setOnClickListener{
            itemClickListener.onClick(it, position)
            flowerList.removeAt(position)
            notifyItemRangeChanged(position, flowerList.size) // 아이템 삭제 후 flowerList.size 재 설정
        }
    }

    //리스너 인터페이스
    interface OnItemClickListener {
        fun onClick(v: View, position: Int)
    }

    //외부에서 클릭 시 이벤트 설정
    fun setItemClickListener(onItemClickListener: OnItemClickListener) {
        this.itemClickListener = onItemClickListener
    }

    //setItemClickListener 로 설정한 함수 실행
    private lateinit var itemClickListener : OnItemClickListener

    //RecyclerView.ViewHolder 를 상속한 viewHolder 클래스
    inner class MyViewHolder(binding: ItemFlowerBinding) : RecyclerView.ViewHolder(binding.root){
        val imageView = binding.imageView
        val textView = binding.textView
    }
}