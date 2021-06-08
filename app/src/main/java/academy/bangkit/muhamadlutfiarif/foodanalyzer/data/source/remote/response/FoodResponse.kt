package academy.bangkit.muhamadlutfiarif.foodanalyzer.data.source.remote.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FoodResponse(
    val name: String,
    val calories: Int,
    val proteins: Int,
    val fat: Int,
): Parcelable
