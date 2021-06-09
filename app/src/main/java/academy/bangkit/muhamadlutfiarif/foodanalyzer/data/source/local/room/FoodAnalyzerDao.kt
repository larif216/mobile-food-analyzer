package academy.bangkit.muhamadlutfiarif.foodanalyzer.data.source.local.room

import academy.bangkit.muhamadlutfiarif.foodanalyzer.data.source.local.entity.FoodEntity
import academy.bangkit.muhamadlutfiarif.foodanalyzer.data.source.local.entity.UserEntity
import academy.bangkit.muhamadlutfiarif.foodanalyzer.data.source.local.entity.UserWithFoods
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface FoodAnalyzerDao {
    @Query("SELECT * FROM user_table WHERE id = 0")
    fun getUser(): LiveData<UserEntity>

    @Transaction
    @Query("SELECT * FROM user_table WHERE id = 0")
    fun getUserWithFoods(): LiveData<UserWithFoods>

    @Query("SELECT * FROM food_table WHERE name = 'dummy'")
    fun getNullFood(): LiveData<List<FoodEntity>>

    @Insert
    fun insertUser(user: UserEntity)

    @Query("SELECT * FROM food_table")
    fun getAllFood(): LiveData<List<FoodEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertFood(food: FoodEntity)

}