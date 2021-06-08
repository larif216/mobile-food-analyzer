package academy.bangkit.muhamadlutfiarif.foodanalyzer.data.source.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class FoodEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "calories")
    val calories: Int,

    @ColumnInfo(name = "proteins")
    val proteins: Int,

    @ColumnInfo(name = "fat")
    val fat: Int,

    @ColumnInfo(name = "date")
    val date: Date,

    @ColumnInfo(name = "user")
    val userId: Int
): Parcelable
