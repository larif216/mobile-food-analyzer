package academy.bangkit.muhamadlutfiarif.foodanalyzer.viewmodel

import academy.bangkit.muhamadlutfiarif.foodanalyzer.data.FoodAnalyzerRepository
import academy.bangkit.muhamadlutfiarif.foodanalyzer.di.Injection
import academy.bangkit.muhamadlutfiarif.foodanalyzer.ui.home.HomeViewModel
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModelFactory private constructor(private val mFoodAnalyzerRepository: FoodAnalyzerRepository): ViewModelProvider.NewInstanceFactory() {
    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
                instance ?: synchronized(this) {
                    instance ?: ViewModelFactory(Injection.provideRepository(context)).apply { instance = this }
                }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel(mFoodAnalyzerRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }
}