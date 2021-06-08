package academy.bangkit.muhamadlutfiarif.foodanalyzer.ui

import academy.bangkit.muhamadlutfiarif.foodanalyzer.R
import academy.bangkit.muhamadlutfiarif.foodanalyzer.databinding.ActivityMainBinding
import academy.bangkit.muhamadlutfiarif.foodanalyzer.ui.bottomsheet.ImageSourceBottomSheetFragment
import academy.bangkit.muhamadlutfiarif.foodanalyzer.ui.fragment.FoodCaptureFragment
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction


class MainActivity : AppCompatActivity(){

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root

        val cameraButton = binding.cameraButton

        cameraButton.setOnClickListener{

            val foodCaptureFragment: Fragment = FoodCaptureFragment()
            val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()

            transaction.replace(R.id.container, foodCaptureFragment, FoodCaptureFragment.TAG)
            transaction.addToBackStack(FoodCaptureFragment.TAG)

            transaction.commit()
        }

        setContentView(view)
    }


}