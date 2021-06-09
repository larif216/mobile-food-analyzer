package academy.bangkit.muhamadlutfiarif.foodanalyzer.ui.viewmodel

import academy.bangkit.muhamadlutfiarif.foodanalyzer.data.FoodAnalyzerRepository
import academy.bangkit.muhamadlutfiarif.foodanalyzer.data.source.local.entity.FoodEntity
import academy.bangkit.muhamadlutfiarif.foodanalyzer.data.source.local.entity.UserWithFoods
import academy.bangkit.muhamadlutfiarif.foodanalyzer.vo.Resource
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class HomeViewModel(private val foodAnalyzerRepository: FoodAnalyzerRepository): ViewModel() {
    fun getUserWithFoods(): LiveData<Resource<UserWithFoods>> = foodAnalyzerRepository.getUserWithFoods()
    fun addFood(foodEntity : FoodEntity) = foodAnalyzerRepository.insertFood(foodEntity)
}