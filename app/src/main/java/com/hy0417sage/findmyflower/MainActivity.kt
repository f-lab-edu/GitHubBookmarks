package com.hy0417sage.findmyflower

import android.content.DialogInterface
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.hy0417sage.findmyflower.databinding.ActivityMainBinding
import com.hy0417sage.findmyflower.db.AppDatabase
import com.hy0417sage.findmyflower.db.FlowerDao
import com.hy0417sage.findmyflower.db.FlowerEntity

class MainActivity : AppCompatActivity() { //클릭 이벤트 처리 인터페이스

    private lateinit var binding: ActivityMainBinding
    private lateinit var db: AppDatabase
    private lateinit var flowerDao: FlowerDao
    private lateinit var adapter: FlowerAdapter
    private var index = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initClickButton()
        initRecyclerView()

        //DB 인스턴스와 DB 작업을 할 수 있는 DAO 를 가져온다.
        db = AppDatabase.getInstance(this)!!
        flowerDao = db.getFlowerDao()

//        getFlowerList() //저장되어있는 데이터 불러오기
    }

//    private fun getFlowerList() {
//        Thread {
////            flowerList = ArrayList(flowerDao.getAll())
//            initRecyclerView()
//        }.start()
//    }

    private fun initRecyclerView() {
        //리사이클러 뷰 설정
        runOnUiThread {
            adapter = FlowerAdapter() //어댑터 객체 할당
            binding.recyclerView.adapter = adapter //리사이클러뷰 어댑터로 위에서 만든 어댑터 설정
            binding.recyclerView.layoutManager = GridLayoutManager(this, 2) //레이아웃 매니저 설정
//            deleteFlowerItem()
        }
    }

//    private fun deleteFlowerItem() {
//        adapter.setItemClickListener { position ->
//            Thread {
//                flowerDao.deleteFlower(flowerList[position])
//                flowerList.removeAt(position)
//                runOnUiThread {
//                    adapter.notifyDataSetChanged()
//                }
//            }.start()
//            Toast.makeText(
//                this@MainActivity,
//                "${flowerList[position].text}가 삭제되었습니다.",
//                Toast.LENGTH_SHORT
//            ).show()
//        }
//    }

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

        val listener = DialogInterface.OnClickListener { alertDialog: DialogInterface, num: Int ->
            val alert = alertDialog as AlertDialog //p에 해당 AlertDialog 가 들어온다.
            val flowerName: EditText? =
                alert.findViewById<EditText>(R.id.flowerName) //findViewById를 통해 view 를 가져와서 사용
            val flowerImage: ImageView? =
                alert.findViewById<ImageView>(R.id.imageView)

            //flowerImage?.setImageResource(R.drawable.null_profile)
            //TODO : 갤러리 사진 불러오기
            val bitmapDrawable = getDrawable(R.drawable.null_profile) as BitmapDrawable
            val bitmap = bitmapDrawable.bitmap

            Thread {
                flowerDao.insertFlower(
                    FlowerEntity(
                        null,
                        bitmap,
                        "${flowerName?.text}".toString()
                    )
                )
//                getFlowerList()
            }.start()
        }
        builder.setPositiveButton("확인", listener)
        builder.setNegativeButton("취소", null)
        builder.show()
    }

    private fun initClickButton(){
        binding.addButton.setOnClickListener {
            addFlower()
        }
        binding.changeLayoutManagerButton.setOnClickListener {
            changeLayoutManager()
        }
    }

}