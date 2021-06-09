package academy.bangkit.muhamadlutfiarif.foodanalyzer.data

import academy.bangkit.muhamadlutfiarif.foodanalyzer.data.source.local.entity.FoodEntity
import academy.bangkit.muhamadlutfiarif.foodanalyzer.data.source.local.entity.UserWithFoods
import academy.bangkit.muhamadlutfiarif.foodanalyzer.data.source.remote.ApiResponse
import academy.bangkit.muhamadlutfiarif.foodanalyzer.data.source.remote.response.FoodResponse
import academy.bangkit.muhamadlutfiarif.foodanalyzer.vo.Resource
import androidx.lifecycle.LiveData
import okhttp3.MultipartBody

interface FoodAnalyzerDataSource {

    fun getFoodPrediction(image : MultipartBody.Part) : LiveData<Resource<List<FoodEntity>>>
    fun getUserWithFoods(): LiveData<Resource<UserWithFoods>>
}