package academy.bangkit.muhamadlutfiarif.foodanalyzer.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import academy.bangkit.muhamadlutfiarif.foodanalyzer.R
import academy.bangkit.muhamadlutfiarif.foodanalyzer.data.source.local.entity.FoodEntity
import academy.bangkit.muhamadlutfiarif.foodanalyzer.databinding.FragmentHomeBinding
import academy.bangkit.muhamadlutfiarif.foodanalyzer.utils.CalProgressFormatter
import academy.bangkit.muhamadlutfiarif.foodanalyzer.viewmodel.ViewModelFactory
import academy.bangkit.muhamadlutfiarif.foodanalyzer.vo.Status
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_home.*

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
                   when (it.status) {
                       Status.LOADING -> binding.progressBar.visibility = View.VISIBLE
                       Status.SUCCESS -> {
                           binding.progressBar.visibility = View.GONE

                           binding.calProgress.setProgressFormatter(CalProgressFormatter())
                           binding.calProgress.max = it.data?.user!!.totalCalories
                           binding.calProgress.progress = getTotalCalConsumed(it.data.foods)

                           binding.tvEatenCal.text = getTotalCalConsumed(it.data.foods).toString()
                           binding.fatProgress.text = getTotalFat(it.data.foods).toString() + " / 100gr"
                           binding.proteinProgress.text = getTotalProtein(it.data.foods).toString() + " / 100gr"
                           binding.carbProgress.text = getTotalCarb(it.data.foods).toString() + " / 100gr"

                           val foodListAdapter = FoodListAdapter(it.data?.foods)
                           foodListAdapter.notifyDataSetChanged()

                           with(binding.rvFood) {
                               layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
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

    private fun getTotalCarb(foods: List<FoodEntity>): Int {
        var result = 0
        for (i in foods) {
            result += i.carb
        }
        return result
    }

    private fun getTotalProtein(foods: List<FoodEntity>): Int {
        var result = 0
        for (i in foods) {
            result += i.proteins
        }
        return result
    }

    private fun getTotalFat(foods: List<FoodEntity>): Int {
        var result = 0
        for (i in foods) {
            result += i.fat
        }
        return result
    }

    private fun getTotalCalConsumed(foods: List<FoodEntity>): Int {
        var result = 0
        for (i in foods) {
            result += i.calories
        }
        return result
    }
}