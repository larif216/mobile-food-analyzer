package academy.bangkit.muhamadlutfiarif.foodanalyzer.data.source.remote

import academy.bangkit.muhamadlutfiarif.foodanalyzer.data.source.local.entity.FoodEntity
import academy.bangkit.muhamadlutfiarif.foodanalyzer.data.source.remote.response.FoodResponse
import academy.bangkit.muhamadlutfiarif.foodanalyzer.utils.JsonHelper
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

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
}