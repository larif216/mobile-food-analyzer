package academy.bangkit.muhamadlutfiarif.foodanalyzer.utils

import academy.bangkit.muhamadlutfiarif.foodanalyzer.data.source.remote.RemoteService
import academy.bangkit.muhamadlutfiarif.foodanalyzer.data.source.remote.response.FoodResponse
import android.content.Context
import okhttp3.MultipartBody
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

class JsonHelper(private val context: Context) {

    companion object {
        const val FILE = "SampleResponse.json"
        const val MAIN_DOMAIN = "http://34.101.205.136:5000/"
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

        return FoodResponse(
            name,
            calories.toDouble(),
            proteins.toDouble(),
            fats.toDouble(),
                0.toDouble(),
                0.toDouble(),
                0.toDouble(),
                0.toDouble(),
        )
    }

    fun getFoodPrediction(image : MultipartBody.Part) : List<FoodResponse> {

        val retrofit = Retrofit.Builder()
                .baseUrl(MAIN_DOMAIN)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val retrofitService = retrofit.create(RemoteService::class.java)

        val statusResponse = retrofitService.getFoodFromImage(image).execute()

        val foodResponse = statusResponse.body()!!.listFoodResponse
        return foodResponse!!
    }


}