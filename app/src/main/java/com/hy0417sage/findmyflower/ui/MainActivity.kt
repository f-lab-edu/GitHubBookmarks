package com.hy0417sage.findmyflower.ui

import android.content.DialogInterface
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.hy0417sage.findmyflower.R
import com.hy0417sage.findmyflower.databinding.ActivityMainBinding
import com.hy0417sage.findmyflower.database.AppDatabase
import com.hy0417sage.findmyflower.database.FlowerDao
import com.hy0417sage.findmyflower.data.model.FlowerEntity
import com.hy0417sage.findmyflower.data.repository.GitHubRepository
import com.hy0417sage.findmyflower.network.GitHubService
import com.hy0417sage.findmyflower.ui.flower.FlowerAdapter
import com.hy0417sage.findmyflower.ui.flower.FlowerViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var db: AppDatabase
    private lateinit var flowerDao: FlowerDao
    private lateinit var flowerViewModel: FlowerViewModel
    private var adapter: FlowerAdapter = FlowerAdapter()
    private var index = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = AppDatabase.getInstance(this)!!
        flowerDao = db.getFlowerDao()

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = GridLayoutManager(this, 2)

        flowerViewModel = ViewModelProvider(this).get(FlowerViewModel::class.java)
        flowerViewModel.getAllFlowerData.observe(this, Observer { flowerEntity->
            adapter.updateFlowerList(flowerEntity)
        })

        val gitHubRepository = GitHubRepository()
        gitHubRepository.loadGithub("incomplete_results", this)

        initClickButton()
        deleteFlowerItem()
    }

    private fun deleteFlowerItem() {
        adapter.setItemClickListener { position ->
            adapter.deleteFlowerItem(position)
        }
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