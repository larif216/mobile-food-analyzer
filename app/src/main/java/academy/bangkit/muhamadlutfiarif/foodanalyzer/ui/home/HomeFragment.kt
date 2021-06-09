package academy.bangkit.muhamadlutfiarif.foodanalyzer.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import academy.bangkit.muhamadlutfiarif.foodanalyzer.databinding.FragmentHomeBinding
import academy.bangkit.muhamadlutfiarif.foodanalyzer.ui.viewmodel.HomeViewModel
import academy.bangkit.muhamadlutfiarif.foodanalyzer.viewmodel.ViewModelFactory
import academy.bangkit.muhamadlutfiarif.foodanalyzer.vo.Status
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            viewModel = ViewModelProvider(this, factory)[HomeViewModel::class.java]

            viewModel.getUserWithFoods().observe(viewLifecycleOwner, {
               if (it != null)  {
                   Log.d("Isi It", it.data.toString())
                   when (it.status) {
                       Status.LOADING -> binding.progressBar.visibility = View.VISIBLE
                       Status.SUCCESS -> {
                           binding.progressBar.visibility = View.GONE
                           val foodListAdapter = FoodListAdapter(it.data?.foods)
                           foodListAdapter.notifyDataSetChanged()

                           with(binding.rvFood) {
                               layoutManager = LinearLayoutManager(context)
                               setHasFixedSize(true)
                               adapter = foodListAdapter
                           }
                       }
                       Status.ERROR -> {
                           binding.progressBar.visibility = View.GONE
                           Toast.makeText(context, "Terjadi kesalahan!", Toast.LENGTH_SHORT).show()
                       }
                   }
               }
            })
        }
    }
}