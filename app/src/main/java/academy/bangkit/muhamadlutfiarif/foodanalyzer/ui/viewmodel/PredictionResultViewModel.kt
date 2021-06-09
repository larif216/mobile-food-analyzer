package academy.bangkit.muhamadlutfiarif.foodanalyzer.ui.viewmodel

import academy.bangkit.muhamadlutfiarif.foodanalyzer.data.source.local.entity.FoodEntity
import academy.bangkit.muhamadlutfiarif.foodanalyzer.data.source.remote.RemoteService
import academy.bangkit.muhamadlutfiarif.foodanalyzer.utils.JsonHelper
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import kotlin.concurrent.thread


class PredictionResultViewModel(): ViewModel() {

    private val liveDataFoodPrediction = MutableLiveData<ArrayList<FoodEntity>>()

    fun setPredictionResult(image: MultipartBody.Part){

        thread(start = true){
            val listFoodEntity = ArrayList<FoodEntity>()

            val client = OkHttpClient.Builder()
                .connectTimeout(100, TimeUnit.SECONDS)
                .readTimeout(100, TimeUnit.SECONDS).build()

            val retrofit = Retrofit.Builder()
                .baseUrl(JsonHelper.MAIN_DOMAIN).client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val retrofitService = retrofit.create(RemoteService::class.java)

            val statusResponse = retrofitService.getFoodFromImage(image).execute()

            val listFoodResponse = statusResponse.body()?.listFoodResponse
            if (listFoodResponse != null) {
                for(food in listFoodResponse){
                    val foodEntity = FoodEntity(
                        name = food.name,
                        calories = food.calories,
                        proteins = food.proteins,
                        fat = food.fats,
                        date = "Rabu, 9 Juni 2021",
                        userId = 0
                    )
                    listFoodEntity.add(foodEntity)
                    Log.d("prediction result : ", food.name)

                }
            }
            liveDataFoodPrediction.postValue(listFoodEntity)
        }
    }

    fun getPredictionResult(): MutableLiveData<ArrayList<FoodEntity>> {
        return liveDataFoodPrediction
    }


}


