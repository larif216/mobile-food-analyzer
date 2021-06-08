package academy.bangkit.muhamadlutfiarif.foodanalyzer.ui.activity

import academy.bangkit.muhamadlutfiarif.foodanalyzer.R
import academy.bangkit.muhamadlutfiarif.foodanalyzer.databinding.ActivityFoodCaptureBinding
import academy.bangkit.muhamadlutfiarif.foodanalyzer.ui.bottomsheet.ImageSourceBottomSheetFragment
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class FoodCaptureActivity : AppCompatActivity(), ImageSourceBottomSheetFragment.ItemClickListener  {

    private var imageView: ImageView? = null

    companion object{
        const val TAG = "FOOD_CAPTURE_FRAGMENT"
        const val REQUEST_IMAGE_CAPTURE = 1
        const val REQUEST_IMAGE_GALLERY = 2
    }

    private var _binding: ActivityFoodCaptureBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityFoodCaptureBinding.inflate(layoutInflater)
        val view = binding.root

        binding.btnSelectImage.setOnClickListener {
            supportFragmentManager.let {
                ImageSourceBottomSheetFragment.newInstance(Bundle()).apply {
                    show(it, tag)
                }
            }
        }

        imageView = binding.imageFoodCapture

        setContentView(view)
    }

    override fun onItemClick(item: String) {
        when(item){
            "Camera" -> {
                //Handle data
                val takePicture = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(takePicture, REQUEST_IMAGE_CAPTURE)
                Toast.makeText(applicationContext, "select from camera", Toast.LENGTH_LONG).show()
            }
            "Gallery" -> {
                val pickPhoto = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(pickPhoto, REQUEST_IMAGE_GALLERY)
                Log.d("select from : > ", "gallery")
                Toast.makeText(applicationContext, "select from gallery", Toast.LENGTH_LONG).show()
                //Handle data
            }
            else->{
                Log.d("select from : > ", "else")
                Toast.makeText(applicationContext, "select from gallery", Toast.LENGTH_LONG).show()
                //Handle data
            }
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Log.d("camera result > ", "cek")
            val imageBitmap = data?.extras?.get("data") as Bitmap
            updateInterfaceAfterSelectImg()
            imageView?.setImageBitmap(imageBitmap)
        }

        else if (requestCode == REQUEST_IMAGE_GALLERY && resultCode == RESULT_OK) {
            val imageUri = data?.data
            updateInterfaceAfterSelectImg()
            imageView?.setImageURI(imageUri)
        }

    }
    private fun updateInterfaceAfterSelectImg(){
        binding.tvSelectInstruction.text = getString(R.string.food_image_confirmation)
        binding.btnSelectImage.text = getString(R.string.food_image_submit)
        binding.btnSelectImageSecondary.visibility = View.VISIBLE
    }

}