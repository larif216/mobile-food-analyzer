package academy.bangkit.muhamadlutfiarif.foodanalyzer.utils

import academy.bangkit.muhamadlutfiarif.foodanalyzer.data.source.local.entity.FoodEntity
import academy.bangkit.muhamadlutfiarif.foodanalyzer.data.source.remote.response.FoodResponse
import android.content.Context
import org.json.JSONObject
import java.io.IOException

class JsonHelper(private val context: Context) {

    companion object {
        const val FILE = "SampleResponse.json"
    }

    private fun parsingFileToString(fileName: String): String? {
        return try {
            val `is` = context.assets.open(fileName)
            val buffer = ByteArray(`is`.available())
            `is`.read(buffer)
            `is`.close()
            String(buffer)

        } catch (ex: IOException) {
            ex.printStackTrace()
            null
        }
    }

    fun getFoodInfo(fileName: String): FoodResponse {
        val responseObject = JSONObject(parsingFileToString(fileName).toString())

        val name = responseObject.getString("name")
        val calories = responseObject.getInt("calories")
        val proteins = responseObject.getInt("proteins")
        val fats = responseObject.getInt("fats")

        val food = FoodResponse(
            name,
            calories,
            proteins,
            fats
        )

        return food
    }
}