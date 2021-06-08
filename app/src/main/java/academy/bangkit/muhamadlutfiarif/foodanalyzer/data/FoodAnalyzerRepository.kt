package academy.bangkit.muhamadlutfiarif.foodanalyzer.data

import academy.bangkit.muhamadlutfiarif.foodanalyzer.data.source.local.LocalDataSource
import academy.bangkit.muhamadlutfiarif.foodanalyzer.data.source.local.entity.FoodEntity
import academy.bangkit.muhamadlutfiarif.foodanalyzer.data.source.local.entity.UserWithFoods
import academy.bangkit.muhamadlutfiarif.foodanalyzer.data.source.remote.ApiResponse
import academy.bangkit.muhamadlutfiarif.foodanalyzer.data.source.remote.RemoteDataSource
import academy.bangkit.muhamadlutfiarif.foodanalyzer.data.source.remote.response.FoodResponse
import academy.bangkit.muhamadlutfiarif.foodanalyzer.utils.AppExecutors
import academy.bangkit.muhamadlutfiarif.foodanalyzer.vo.Resource
import androidx.lifecycle.LiveData

class FoodAnalyzerRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
): FoodAnalyzerDataSource {
    companion object {
        @Volatile
        private var instance: FoodAnalyzerRepository? = null

        fun getInstance(remoteData: RemoteDataSource, localData: LocalDataSource, appExecutors: AppExecutors): FoodAnalyzerRepository =
            instance ?: synchronized(this) {
                instance ?: FoodAnalyzerRepository(remoteData, localData, appExecutors).apply { instance = this }
            }
    }

    override fun getFoodInfo(): LiveData<Resource<FoodEntity>> {
        return object : NetworkBoundResource<FoodEntity, FoodResponse>(appExecutors) {
            public override fun loadFromDB(): LiveData<FoodEntity> =
                localDataSource.getNullFood()

            public override fun shouldFetch(data: FoodEntity?): Boolean =
                data == null

            public override fun createCall(): LiveData<ApiResponse<FoodResponse>> =
                remoteDataSource.getFoodInfo()

            public override fun saveCallResult(data: FoodResponse) {
                val food = FoodEntity(
                    0,
                    data.name,
                    data.calories,
                    data.proteins,
                    data.fat,
                    "Rabu, 9 Juni 2021",
                    0
                )

                localDataSource.insertFood(food)
            }
        }.asLiveData()
    }

    override fun getUserWithFoods(): LiveData<Resource<UserWithFoods>> {
        return object : NetworkBoundResource<UserWithFoods, FoodResponse>(appExecutors) {
            public override fun loadFromDB(): LiveData<UserWithFoods> =
                localDataSource.getUserWithFoods()

            public override fun shouldFetch(data: UserWithFoods?): Boolean =
                false

            public override fun createCall(): LiveData<ApiResponse<FoodResponse>>? =
                null

            public override fun saveCallResult(data: FoodResponse) {

            }
        }.asLiveData()
    }
}