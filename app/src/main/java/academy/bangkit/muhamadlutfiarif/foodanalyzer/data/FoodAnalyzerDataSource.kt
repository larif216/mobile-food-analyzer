package academy.bangkit.muhamadlutfiarif.foodanalyzer.data

import academy.bangkit.muhamadlutfiarif.foodanalyzer.data.source.local.entity.FoodEntity
import academy.bangkit.muhamadlutfiarif.foodanalyzer.data.source.local.entity.UserWithFoods
import academy.bangkit.muhamadlutfiarif.foodanalyzer.vo.Resource
import androidx.lifecycle.LiveData

interface FoodAnalyzerDataSource {

    fun getFoodInfo(): LiveData<Resource<FoodEntity>>

    fun getUserWithFoods(): LiveData<Resource<UserWithFoods>>
}