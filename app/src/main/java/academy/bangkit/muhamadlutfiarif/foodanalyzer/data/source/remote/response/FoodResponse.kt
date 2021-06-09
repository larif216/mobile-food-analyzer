package academy.bangkit.muhamadlutfiarif.foodanalyzer.data.source.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FoodResponse(
    @SerializedName("name")
    val name: String,

    @SerializedName("calories")
    val calories: Double,

    @SerializedName("proteins")
    val proteins: Double,

    @SerializedName("fats")
    val fats: Double,

    @SerializedName("carbohydrates")
    val carbohydrates: Double,

    @SerializedName("sugars")
    val sugars: Double,

    @SerializedName("fibers")
    val fibers: Double,

    @SerializedName("cholesterols")
    val cholesterols: Double,
): Parcelable
