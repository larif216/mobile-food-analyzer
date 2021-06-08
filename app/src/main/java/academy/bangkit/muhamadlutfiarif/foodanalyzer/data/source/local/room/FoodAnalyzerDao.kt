package academy.bangkit.muhamadlutfiarif.foodanalyzer.data.source.local.room

import academy.bangkit.muhamadlutfiarif.foodanalyzer.data.source.local.entity.FoodEntity
import academy.bangkit.muhamadlutfiarif.foodanalyzer.data.source.local.entity.UserEntity
import academy.bangkit.muhamadlutfiarif.foodanalyzer.data.source.local.entity.UserWithFoods
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface FoodAnalyzerDao {
    @Query("SELECT * FROM user_table WHERE id = 1")
    fun getUser(): LiveData<UserEntity>

    @Transaction
    @Query("SELECT * FROM user_table WHERE id = 1")
    fun getUserWithFoods(): LiveData<UserWithFoods>

    @Query("SELECT * FROM food_table WHERE name = 'dummy'")
    fun getNullFood(): LiveData<FoodEntity>

    @Insert
    fun insertFood(food: FoodEntity)
}