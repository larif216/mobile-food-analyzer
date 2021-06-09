package academy.bangkit.muhamadlutfiarif.foodanalyzer.data.source.local.room

import academy.bangkit.muhamadlutfiarif.foodanalyzer.data.source.local.entity.FoodEntity
import academy.bangkit.muhamadlutfiarif.foodanalyzer.data.source.local.entity.UserEntity
import academy.bangkit.muhamadlutfiarif.foodanalyzer.utils.DataGenerator
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import java.util.concurrent.Executors

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
                ).addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        Executors.newSingleThreadExecutor().execute {
                            getInstance(context).foodAnalyzerDao().insertUser(DataGenerator.getUser())
                            val foods = DataGenerator.getFoods()
                            for (i in foods) {
                                getInstance(context).foodAnalyzerDao().insertFood(i)
                            }
                        }
                    }
                }).build().apply {
                    INSTANCE = this
                }
            }
    }
}