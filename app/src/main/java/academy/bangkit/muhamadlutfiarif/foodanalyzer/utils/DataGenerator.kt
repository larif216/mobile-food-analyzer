package academy.bangkit.muhamadlutfiarif.foodanalyzer.utils

import academy.bangkit.muhamadlutfiarif.foodanalyzer.data.source.local.entity.FoodEntity
import academy.bangkit.muhamadlutfiarif.foodanalyzer.data.source.local.entity.UserEntity
import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class DataGenerator {
    companion object {
        @RequiresApi(Build.VERSION_CODES.O)
        val currentDateTime = LocalDateTime.now()

        fun getUser(): UserEntity {
            return UserEntity(
                    0,
                    1200,
                    1203,
                    1110,
                    139,
            )
        }

        @RequiresApi(Build.VERSION_CODES.O)
        fun getFoods(): List<FoodEntity> {
            return listOf(
                    FoodEntity(
                            name = "Martabak",
                            calories = 120,
                            proteins = 23,
                            fat = 300,
                            carb = 30,
                            date = currentDateTime.format(DateTimeFormatter.ISO_DATE),
                            userId = 0
                    ),
                    FoodEntity(
                            name ="Ketoprak",
                            calories = 90,
                            proteins = 48,
                            fat = 110,
                            carb = 23,
                            date = currentDateTime.format(DateTimeFormatter.ISO_DATE),
                            userId = 0
                    ),
                    FoodEntity(
                            name = "Susu Jahe",
                            calories = 105,
                            proteins = 27,
                            fat = 280,
                            carb = 10,
                            date = currentDateTime.format(DateTimeFormatter.ISO_DATE),
                            userId = 0
                    ),
                    FoodEntity(
                            name = "Nasi Goreng",
                            calories = 140,
                            proteins = 10,
                            fat = 550,
                            carb = 35,
                            date = currentDateTime.format(DateTimeFormatter.ISO_DATE),
                            userId = 0
                    ),
            )
        }
    }
}