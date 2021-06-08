package academy.bangkit.muhamadlutfiarif.foodanalyzer.ui.activity

import academy.bangkit.muhamadlutfiarif.foodanalyzer.databinding.ActivityMainBinding
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity(){

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root

        val cameraButton = binding.cameraButton

        cameraButton.setOnClickListener{
            val myIntent = Intent(this@MainActivity, FoodCaptureActivity::class.java)
            this@MainActivity.startActivity(myIntent)
        }

        setContentView(view)
    }
}