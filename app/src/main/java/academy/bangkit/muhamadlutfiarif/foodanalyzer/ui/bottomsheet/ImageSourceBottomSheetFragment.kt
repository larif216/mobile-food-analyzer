package academy.bangkit.muhamadlutfiarif.foodanalyzer.ui.bottomsheet

import academy.bangkit.muhamadlutfiarif.foodanalyzer.R
import academy.bangkit.muhamadlutfiarif.foodanalyzer.databinding.BottomsheetSelectPictureBinding
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.*
import kotlinx.android.synthetic.main.bottomsheet_select_picture.*

class ImageSourceBottomSheetFragment : BottomSheetDialogFragment() {

    private var _binding: BottomsheetSelectPictureBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = BottomsheetSelectPictureBinding.inflate(layoutInflater)
        return inflater.inflate(R.layout.bottomsheet_select_picture, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews()
    }

    private fun setUpViews() {
        from_camera.setOnClickListener {
            dismissAllowingStateLoss()
            Log.d("select from : > ", "camera")
            mListener?.onItemClick("Camera")
        }

        from_gallery.setOnClickListener {
            dismissAllowingStateLoss()
            mListener?.onItemClick("Gallery")
        }
    }

    private var mListener: ItemClickListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ItemClickListener) {
            mListener = context as ItemClickListener
        } else {
            Log.d("context attach : >", context.toString())
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }
    interface ItemClickListener {
        fun onItemClick(item: String)
    }

    companion object {
        @JvmStatic
        fun newInstance(bundle: Bundle): ImageSourceBottomSheetFragment {
            val fragment = ImageSourceBottomSheetFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}