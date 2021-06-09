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
                            calories = 120.toDouble(),
                            proteins = 23.toDouble(),
                            fat = 300.toDouble(),
                            date = currentDateTime.format(DateTimeFormatter.ISO_DATE),
                            userId = 0
                    ),
                    FoodEntity(
                            name ="Ketoprak",
                            calories = 90.toDouble(),
                            proteins = 48.toDouble(),
                            fat = 110.toDouble(),
                            date = currentDateTime.format(DateTimeFormatter.ISO_DATE),
                            userId = 0
                    ),
                    FoodEntity(
                            name = "Susu Jahe",
                            calories = 105.toDouble(),
                            proteins = 27.toDouble(),
                            fat = 280.toDouble(),
                            date = currentDateTime.format(DateTimeFormatter.ISO_DATE),
                            userId = 0
                    ),
                    FoodEntity(
                            name = "Nasi Goreng",
                            calories = 140.toDouble(),
                            proteins = 10.toDouble(),
                            fat = 550.toDouble(),
                            date = currentDateTime.format(DateTimeFormatter.ISO_DATE),
                            userId = 0
                    ),
            )
        }
    }
}