package academy.bangkit.muhamadlutfiarif.foodanalyzer.data.source.local.room

import academy.bangkit.muhamadlutfiarif.foodanalyzer.data.source.local.entity.FoodEntity
import academy.bangkit.muhamadlutfiarif.foodanalyzer.data.source.local.entity.UserEntity
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [UserEntity::class, FoodEntity::class],
    version = 1,
    exportSchema = false)
abstract class FoodAnalyzerDatabase: RoomDatabase() {
    abstract fun foodAnalyzerDao(): FoodAnalyzerDao

    companion object {
        @Volatile
        private var INSTANCE: FoodAnalyzerDatabase? = null

        fun getInstance(context: Context): FoodAnalyzerDatabase =
            INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    FoodAnalyzerDatabase::class.java,
                    "FoodAnalyzer.db"
                ).build().apply {
                    INSTANCE = this
                }
            }
    }
}