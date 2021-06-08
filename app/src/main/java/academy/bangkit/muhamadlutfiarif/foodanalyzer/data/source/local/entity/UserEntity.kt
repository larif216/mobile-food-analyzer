package academy.bangkit.muhamadlutfiarif.foodanalyzer.data.source.local.entity

import android.os.Parcelable
import androidx.room.*
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "user_table")
data class UserEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "total_calories")
    var totalCalories: Int,

    @ColumnInfo(name = "total_carbohydrates")
    var totalCarbohydrates: Int,

    @ColumnInfo(name = "total_proteins")
    var totalProteins: Int,

    @ColumnInfo(name = "total_fat")
    var totalFat: Int
): Parcelable

data class UserWithFoods(
    @Embedded
    var user: UserEntity,

    @Relation(
        parentColumn = "id",
        entityColumn = "userId"
    )
    var foods: UserWithFoods
)
