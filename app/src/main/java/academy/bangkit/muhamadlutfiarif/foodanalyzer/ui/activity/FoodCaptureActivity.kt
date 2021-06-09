package academy.bangkit.muhamadlutfiarif.foodanalyzer.ui.activity

import academy.bangkit.muhamadlutfiarif.foodanalyzer.R
import academy.bangkit.muhamadlutfiarif.foodanalyzer.databinding.ActivityFoodCaptureBinding
import academy.bangkit.muhamadlutfiarif.foodanalyzer.ui.bottomsheet.ImageSourceBottomSheetFragment
import academy.bangkit.muhamadlutfiarif.foodanalyzer.ui.viewmodel.PredictionResultViewModel
import android.content.Intent
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_food_prediction_result.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.InputStream


class FoodCaptureActivity : AppCompatActivity(), ImageSourceBottomSheetFragment.ItemClickListener  {

    private var imageView: ImageView? = null
    private var _binding: ActivityFoodCaptureBinding? = null
    private val binding get() = _binding!!
    private var predictionResultViewModel : PredictionResultViewModel? = null
    private var imageUri : Uri? = null

    companion object{
        const val TAG = "FOOD_CAPTURE_FRAGMENT"
        const val REQUEST_IMAGE_CAPTURE = 1
        const val REQUEST_IMAGE_GALLERY = 2
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityFoodCaptureBinding.inflate(layoutInflater)
        val view = binding.root

        predictionResultViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            PredictionResultViewModel::class.java
        )

        binding.btnSelectImage.setOnClickListener(){
            if(binding.btnSelectImage.text == String.format(getString(R.string.food_image_submit))) {
                val myIntent = Intent(this@FoodCaptureActivity, FoodPredictionResultActivity::class.java)
                this@FoodCaptureActivity.startActivity(myIntent)

//                val foodImage = convertUriToMultipartBody(imageUri!!)
//                predictionResultViewModel?.setPredictionResult(foodImage)
            } else {
                supportFragmentManager.let {
                    ImageSourceBottomSheetFragment.newInstance(Bundle()).apply {
                        show(it, tag)
                    }
                }
            }
        }




        imageView = binding.imageFoodCapture


        predictionResultViewModel!!.getPredictionResult().observe(this, { listFood ->

            Log.d("predict : ", listFood.toString())

        })


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
                val pickPhoto = Intent(
                    Intent.ACTION_PICK,
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                )
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
            imageUri  = data?.data
            updateInterfaceAfterSelectImg()
            imageView?.setImageURI(imageUri)


        }

    }

    private fun convertUriToMultipartBody(uri:Uri) : MultipartBody.Part{
        //uri converted to file
        val arr = arrayOf(MediaStore.Images.Media.DATA)
        var foodImage : MultipartBody.Part? = null
        val cursor: Cursor? = imageUri?.let { contentResolver.query(it, arr, null, null, null) }
        if (cursor != null) {
            val img_index: Int = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
            cursor.moveToFirst()
            val img_path: String = cursor.getString(img_index)
            val file = File(img_path)
            cursor.close()

            val photoRequestBody: RequestBody =
                    RequestBody.create(MediaType.parse("image/*"), file)
            foodImage = MultipartBody.Part.createFormData("file", file.name, photoRequestBody)

            Log.d("file RequestBody", photoRequestBody.toString())
            Log.d("file details", foodImage.toString())

        }

        return foodImage!!
    }

    fun upload(inputStream: InputStream) {
        val part = MultipartBody.Part.createFormData(
            "pic", "myPic", RequestBody.create(
                MediaType.parse("image/*"),
                inputStream.readBytes()
            )
        )
    }

    private fun updateInterfaceAfterSelectImg(){
        binding.tvSelectInstruction.text = getString(R.string.food_image_confirmation)
        binding.btnSelectImage.text = getString(R.string.food_image_submit)
        binding.btnSelectImageSecondary.visibility = View.VISIBLE
    }

    private fun ConvertBitmapToString(bitmap: Bitmap): String? {
        var options: BitmapFactory.Options? = null
        options = BitmapFactory.Options()
        options.inSampleSize = 3
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 40, byteArrayOutputStream)
        val byteArrayImage: ByteArray = byteArrayOutputStream.toByteArray()
        return Base64.encodeToString(byteArrayImage, Base64.DEFAULT)
    }
}