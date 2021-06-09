package academy.bangkit.muhamadlutfiarif.foodanalyzer.data.source.remote

import academy.bangkit.muhamadlutfiarif.foodanalyzer.data.source.local.entity.FoodEntity
import academy.bangkit.muhamadlutfiarif.foodanalyzer.data.source.remote.response.FoodResponse
import academy.bangkit.muhamadlutfiarif.foodanalyzer.utils.JsonHelper
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import okhttp3.MultipartBody

class RemoteDataSource private constructor(private val jsonHelper: JsonHelper) {

    private val handler = Handler(Looper.getMainLooper())

    companion object {
        private const val SERVICE_LATENCY_IN_MILLIS: Long = 2000

        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(helper: JsonHelper): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(helper).apply { instance = this }
            }
    }

    fun getFoodInfo(): LiveData<ApiResponse<FoodResponse>> {
        val resultFood = MutableLiveData<ApiResponse<FoodResponse>>()
        handler.postDelayed({
            resultFood.value = ApiResponse.success(jsonHelper.getFoodInfo(JsonHelper.FILE))
        }, SERVICE_LATENCY_IN_MILLIS)
        return resultFood
    }

    fun getFoodPrediction(image : MultipartBody.Part) : LiveData<ApiResponse<List<FoodResponse>>> {
        val resultFood = MutableLiveData<ApiResponse<List<FoodResponse>>>()
        handler.postDelayed({
            resultFood.value = ApiResponse.success(jsonHelper.getFoodPrediction(image))
        }, SERVICE_LATENCY_IN_MILLIS)
        return resultFood
    }
}