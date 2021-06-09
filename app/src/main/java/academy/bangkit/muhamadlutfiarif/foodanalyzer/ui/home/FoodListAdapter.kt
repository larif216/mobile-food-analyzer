package academy.bangkit.muhamadlutfiarif.foodanalyzer.ui.home

import academy.bangkit.muhamadlutfiarif.foodanalyzer.data.source.local.entity.FoodEntity
import academy.bangkit.muhamadlutfiarif.foodanalyzer.databinding.ItemFoodHistoryBinding
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class FoodListAdapter(private val listItems: List<FoodEntity>?): RecyclerView.Adapter<FoodListAdapter.FoodViewHolder>() {
    inner class FoodViewHolder(private val binding: ItemFoodHistoryBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(food: FoodEntity) {
            with(binding) {
                tvName.text = food.name
                tvCal.text = food.calories.toString()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val itemFoodHistoryBinding = ItemFoodHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FoodViewHolder(itemFoodHistoryBinding)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        val item = listItems!![position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return listItems!!.size
    }

}