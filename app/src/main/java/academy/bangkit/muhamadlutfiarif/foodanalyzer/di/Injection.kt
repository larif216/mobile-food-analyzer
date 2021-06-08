package academy.bangkit.muhamadlutfiarif.foodanalyzer.di

import academy.bangkit.muhamadlutfiarif.foodanalyzer.data.FoodAnalyzerRepository
import academy.bangkit.muhamadlutfiarif.foodanalyzer.data.source.local.LocalDataSource
import academy.bangkit.muhamadlutfiarif.foodanalyzer.data.source.local.room.FoodAnalyzerDatabase
import academy.bangkit.muhamadlutfiarif.foodanalyzer.data.source.remote.RemoteDataSource
import academy.bangkit.muhamadlutfiarif.foodanalyzer.utils.AppExecutors
import academy.bangkit.muhamadlutfiarif.foodanalyzer.utils.JsonHelper
import android.content.Context

object Injection {
    fun provideRepository(context: Context): FoodAnalyzerRepository {
        val database = FoodAnalyzerDatabase.getInstance(context)

        val localDataSource = LocalDataSource.getInstance(database.foodAnalyzerDao())
        val remoteDataSource = RemoteDataSource.getInstance(JsonHelper(context))
        val appExecutors = AppExecutors()

        return FoodAnalyzerRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}