package com.hy0417sage.findmyflower.data.repository

import androidx.lifecycle.LiveData
import com.hy0417sage.findmyflower.database.FlowerDao
import com.hy0417sage.findmyflower.data.model.FlowerEntity

class FlowerRepository (private val flowerDao: FlowerDao) {

    val getAllFlowerData: LiveData<List<FlowerEntity>> = flowerDao.getAll()

    suspend fun insertFlower(flowerEntity: FlowerEntity){
        flowerDao.insertFlower(flowerEntity)
    }

    suspend fun deleteFlower(flowerEntity: FlowerEntity){
        flowerDao.deleteFlower(flowerEntity)
    }
}

/*코루틴을 사용하는 큰 이유가
네트워킹 또는 데이터베이스 기능을 추가할 경우 데이터를 불러오거나 이미지 다운로드 같은 실행작업이 올바르게 처리되지 않으면
스크롤이 버벅거려 UI가 응답하지 않는것처럼 보이기 때문에 스레드 혹은 코루틴을 사용.
사용자 인터페이스 응답성을 유지하면서 앱이 이미지 다운로드와 같은 복잡한작업을 백그라운드에서 실행하기 위해서*/