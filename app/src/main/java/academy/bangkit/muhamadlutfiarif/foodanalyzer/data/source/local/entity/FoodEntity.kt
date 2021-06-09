package academy.bangkit.muhamadlutfiarif.foodanalyzer.data.source.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.util.*


@Parcelize
@Entity(tableName = "food_table")
data class FoodEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "calories")
    val calories: Double,

    @ColumnInfo(name = "proteins")
    val proteins: Double,

    @ColumnInfo(name = "fat")
    val fat: Double,

    @ColumnInfo(name = "carb")
    val carb: Int,

    @ColumnInfo(name = "date")
    val date: String,

    @ColumnInfo(name = "user")
    val userId: Int
): Parcelable
