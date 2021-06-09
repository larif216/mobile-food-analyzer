package academy.bangkit.muhamadlutfiarif.foodanalyzer.ui.activity

import academy.bangkit.muhamadlutfiarif.foodanalyzer.R
import academy.bangkit.muhamadlutfiarif.foodanalyzer.data.source.local.entity.FoodEntity
import academy.bangkit.muhamadlutfiarif.foodanalyzer.databinding.ActivityFoodCaptureBinding
import academy.bangkit.muhamadlutfiarif.foodanalyzer.databinding.ActivityFoodPredictionResultBinding
import academy.bangkit.muhamadlutfiarif.foodanalyzer.ui.bottomsheet.ImageSourceBottomSheetFragment
import academy.bangkit.muhamadlutfiarif.foodanalyzer.ui.viewmodel.HomeViewModel
import academy.bangkit.muhamadlutfiarif.foodanalyzer.utils.DataGenerator
import academy.bangkit.muhamadlutfiarif.foodanalyzer.viewmodel.ViewModelFactory
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import java.time.format.DateTimeFormatter
import kotlin.concurrent.thread

class FoodPredictionResultActivity : AppCompatActivity() {

    private var _binding: ActivityFoodPredictionResultBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityFoodPredictionResultBinding.inflate(layoutInflater)
        val view = binding.root
        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, factory)[HomeViewModel::class.java]

        binding.btnSaveFood.setOnClickListener {
            val foodEntity = FoodEntity(
                name = "Spagheti",
                calories = 120.toDouble(),
                proteins = 23.toDouble(),
                fat = 300.toDouble(),
                date = "Rabu, 21 Juni 2021",
                userId = 0
            )

            thread(start = true){
                viewModel.addFood(foodEntity)
            }

            val myIntent = Intent(this@FoodPredictionResultActivity, MainActivity::class.java)
            this@FoodPredictionResultActivity.startActivity(myIntent)

        }


        setContentView(view)
    }
}