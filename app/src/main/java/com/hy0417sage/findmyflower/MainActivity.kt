package com.hy0417sage.findmyflower

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.hy0417sage.findmyflower.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener { //클릭 이벤트 처리 인터페이스

    private lateinit var binding: ActivityMainBinding
    private lateinit var changeLayout: Button //changeLayout button 변수 선언
    private lateinit var addButton: Button
    private lateinit var flowerList: ArrayList<FlowerData>
    private lateinit var adapter: FlowerAdapter
    private var index = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        changeLayout = findViewById(R.id.changeLayoutManagerButton)
        addButton = findViewById(R.id.addButton)
        changeLayout.setOnClickListener(this)
        addButton.setOnClickListener(this)
        flowerList = ArrayList()

        setFlowerList()
        setRecyclerView()
        deleteFlowerItem()
    }

    private fun deleteFlowerItem() {
        adapter.setItemClickListener(object : FlowerAdapter.OnItemClickListener {
            override fun onClick(v: View, position: Int) {
                adapter.notifyItemRemoved(position)
            }
        })
    }

    private fun setFlowerList() {
        for (i: Int in 1..10) {
            flowerList.add(
                FlowerData(
                    img = ContextCompat.getDrawable(this, R.drawable.ic_launcher_background)!!,
                    text = i.toString()
                )
            )
        }
    }

    private fun setRecyclerView() {
        //리사이클러 뷰 설정
        adapter = FlowerAdapter(flowerList) //어댑터 객체 할당
        binding.recyclerView.adapter = adapter //리사이클러뷰 어댑터로 위에서 만든 어댑터 설정
        binding.recyclerView.layoutManager = GridLayoutManager(this, 2) //레이아웃 매니저 설정
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

    private fun addFlower() {
        val builder = AlertDialog.Builder(this)
        builder.setView(layoutInflater.inflate(R.layout.custom_dialog, null))

        val listener = DialogInterface.OnClickListener { p: DialogInterface, num: Int ->
            val alert = p as AlertDialog //p에 해당 AlertDialog 가 들어온다.
            val flowerName: EditText? =
                alert.findViewById<EditText>(R.id.flowerName) //findViewById를 통해 view 를 가져와서 사용

            flowerList.add(
                FlowerData(
                    img = ContextCompat.getDrawable(this, R.drawable.ic_launcher_background)!!,
                    text = "${flowerName?.text}".toString()
                )
            )
            adapter.notifyItemInserted(flowerList.size) //어댑터에서 리사이클링뷰에 새로운 데이터 반영
            binding.recyclerView.smoothScrollToPosition(flowerList.size) //새로 추가한 뷰로 이동
        }
        builder.setPositiveButton("확인", listener)
        builder.setNegativeButton("취소", null)
        builder.show()
    }

    //클릭 이벤트 처리
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.changeLayoutManagerButton -> {
                changeLayoutManager()
            }
            R.id.addButton -> {
                addFlower()
            }
        }
    }
}