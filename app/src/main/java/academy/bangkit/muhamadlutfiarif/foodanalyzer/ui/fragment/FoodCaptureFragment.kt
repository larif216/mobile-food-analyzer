package academy.bangkit.muhamadlutfiarif.foodanalyzer.ui.fragment

import academy.bangkit.muhamadlutfiarif.foodanalyzer.databinding.FragmentFoodCaptureBinding
import academy.bangkit.muhamadlutfiarif.foodanalyzer.ui.bottomsheet.ImageSourceBottomSheetFragment
import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity


class FoodCaptureFragment : Fragment(), ImageSourceBottomSheetFragment.ItemClickListener  {

    companion object{
        const val TAG = "FOOD_CAPTURE_FRAGMENT"
    }

    private var myContext: FragmentActivity? = null
    private var _binding: FragmentFoodCaptureBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        _binding = FragmentFoodCaptureBinding.inflate(layoutInflater)


        binding.btnSelectImageSource.setOnClickListener {
            myContext!!.supportFragmentManager.let {
                ImageSourceBottomSheetFragment.newInstance(Bundle()).apply {
                    show(it, tag)
                }
            }

        }

        return binding.root
    }

    override fun onAttach(activity: Activity) {
        myContext = activity as FragmentActivity
        super.onAttach(activity)
    }

    override fun onItemClick(item: String) {
        when(item){
            "Camera" -> {
                //Handle data
                Toast.makeText(requireContext(), "select from camera", Toast.LENGTH_LONG).show()
            }
            "Gallery" -> {
                Log.d("select from : > ", "gallery")
                Toast.makeText(requireContext(), "select from gallery", Toast.LENGTH_LONG).show()
                //Handle data
            }
            else->{
                Log.d("select from : > ", "else")
                Toast.makeText(requireContext(), "select from gallery", Toast.LENGTH_LONG).show()
                //Handle data
            }
        }
    }


}