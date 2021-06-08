package academy.bangkit.muhamadlutfiarif.foodanalyzer.data.source.local

import academy.bangkit.muhamadlutfiarif.foodanalyzer.data.source.local.entity.UserEntity
import academy.bangkit.muhamadlutfiarif.foodanalyzer.data.source.local.entity.UserWithFoods
import academy.bangkit.muhamadlutfiarif.foodanalyzer.data.source.local.room.FoodAnalyzerDao
import androidx.lifecycle.LiveData

class LocalDataSource private constructor(private val mFoodAnalyzerDao: FoodAnalyzerDao) {

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(foodAnalyzerDao: FoodAnalyzerDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(foodAnalyzerDao)
    }

    fun getUser(): LiveData<UserEntity> = mFoodAnalyzerDao.getUser()

    fun getUserWithFoods(): LiveData<UserWithFoods> = mFoodAnalyzerDao.getUserWithFoods()
}