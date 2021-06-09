package academy.bangkit.muhamadlutfiarif.foodanalyzer.data.source.remote

import academy.bangkit.muhamadlutfiarif.foodanalyzer.data.source.remote.response.FoodPredictResponse
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*

interface RemoteService {
    @Multipart
    @POST("predict")
    fun getFoodFromImage(@Part image: MultipartBody.Part)
            : Call<FoodPredictResponse>
}

