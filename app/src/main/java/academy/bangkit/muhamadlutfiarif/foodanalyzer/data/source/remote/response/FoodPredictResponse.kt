package academy.bangkit.muhamadlutfiarif.foodanalyzer.data.source.remote.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class FoodPredictResponse (
        @SerializedName("status_code")
        var status : Int?,

        @SerializedName("data")
        var listFoodResponse : List<FoodResponse>?,
)


