package wizley.android.playground.worker

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.work.Constraints
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit

class ktPeriodicWorkerActivity : Activity(){
    private val TAG = "ktPeriodicWorkerActivity"

    @SuppressLint("LongLogTag")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.e(TAG, "onCreate")

        val constraints = Constraints.Builder()
                .setRequiresCharging(true)
                .build()

        // This can not be chained
        // min period length is 15 minutes
        val request = PeriodicWorkRequest.Builder(ktMyWorker::class.java, 15, TimeUnit.MINUTES, 5, TimeUnit.MINUTES)
                .setConstraints(constraints)
                .build()

        WorkManager.getInstance(this).enqueue(request)
    }

}