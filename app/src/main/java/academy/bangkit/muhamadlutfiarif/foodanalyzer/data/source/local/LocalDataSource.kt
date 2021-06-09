package academy.bangkit.muhamadlutfiarif.foodanalyzer.data.source.local

import academy.bangkit.muhamadlutfiarif.foodanalyzer.data.source.local.entity.FoodEntity
import academy.bangkit.muhamadlutfiarif.foodanalyzer.data.source.local.entity.UserEntity
import academy.bangkit.muhamadlutfiarif.foodanalyzer.data.source.local.entity.UserWithFoods
import academy.bangkit.muhamadlutfiarif.foodanalyzer.data.source.local.room.FoodAnalyzerDao
import android.util.Log
import androidx.lifecycle.LiveData
class LocalDataSource private constructor(private val mFoodAnalyzerDao: FoodAnalyzerDao) {

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(foodAnalyzerDao: FoodAnalyzerDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(foodAnalyzerDao)
    }

    fun getUser(): LiveData<UserEntity> = mFoodAnalyzerDao.getUser()

    fun getUserWithFoods(): LiveData<UserWithFoods> {
        Log.d("UserWithFood", mFoodAnalyzerDao.getUserWithFoods().value.toString())
        return mFoodAnalyzerDao.getUserWithFoods()
    }

    fun getNullFood(): LiveData<List<FoodEntity>> = mFoodAnalyzerDao.getNullFood()

    fun getNullListFood(): LiveData<List<FoodEntity>> = mFoodAnalyzerDao.getNullFood()

    fun getAllFood(): LiveData<List<FoodEntity>> = mFoodAnalyzerDao.getAllFood()

    fun insertFood(food: FoodEntity) = mFoodAnalyzerDao.insertFood(food)
}

