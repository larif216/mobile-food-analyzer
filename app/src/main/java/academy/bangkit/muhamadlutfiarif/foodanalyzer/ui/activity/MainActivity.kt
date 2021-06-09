package academy.bangkit.muhamadlutfiarif.foodanalyzer.ui.activity

import academy.bangkit.muhamadlutfiarif.foodanalyzer.databinding.ActivityMainBinding
import android.Manifest.permission.*
import android.R
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat


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

        requestPermissionAndContinue()
        setContentView(view)
    }

    private fun requestPermissionAndContinue() {
        if (ContextCompat.checkSelfPermission(this,READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED &&
            ContextCompat.checkSelfPermission(this, INTERNET) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(
                this@MainActivity, arrayOf(
                    INTERNET,
                    READ_EXTERNAL_STORAGE
                ), 200
            )
        } else if(ContextCompat.checkSelfPermission(this,READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(
                this@MainActivity, arrayOf(
                    READ_EXTERNAL_STORAGE
                ), 200
            )
        } else if(ContextCompat.checkSelfPermission(this, INTERNET) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(
                this@MainActivity, arrayOf(
                    INTERNET
                ), 200
            )
        }
    }
}