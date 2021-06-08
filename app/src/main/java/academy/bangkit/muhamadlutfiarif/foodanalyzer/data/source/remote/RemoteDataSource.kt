package academy.bangkit.muhamadlutfiarif.foodanalyzer.data.source.remote

import academy.bangkit.muhamadlutfiarif.foodanalyzer.utils.JsonHelper
import android.os.Handler
import android.os.Looper

class RemoteDataSource private constructor(private val jsonHelper: JsonHelper) {

    private val handler = Handler(Looper.getMainLooper())

    companion object {
        private const val SERVICE_LATENCY_IN_MILLIS: Long = 2000

        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(helper: JsonHelper): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(helper).apply { instance = this }
            }
    }
}