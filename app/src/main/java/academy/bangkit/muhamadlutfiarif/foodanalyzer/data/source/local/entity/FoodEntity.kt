package academy.bangkit.muhamadlutfiarif.foodanalyzer.data.source.local.entity

import java.util.*

data class FoodEntity(
    private val id: Int,
    private val name: String,
    private val calories: Int,
    private val proteins: Int,
    private val fat: Int,
    private val date: Date
)
