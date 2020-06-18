package wizley.android.playground.worker.single

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.work.Constraints
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkInfo
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit

class KTWorkerActivity : AppCompatActivity() {
    private val TAG = "ktWorkerActivity"

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.e(TAG, "onCreate")

        val constraints = Constraints.Builder()
                .setRequiresDeviceIdle(false)
                .setRequiresCharging(true)
                .build()

        val workRequest = OneTimeWorkRequest.Builder(KTMyWorker::class.java)
                .setConstraints(constraints)
                .setInitialDelay(3, TimeUnit.SECONDS)
                .build()

        WorkManager.getInstance(this).enqueue(workRequest)

        WorkManager.getInstance(this).getWorkInfoByIdLiveData(workRequest.id)
                .observe(this, Observer { workInfo ->
                    if(workInfo != null && workInfo.state == WorkInfo.State.SUCCEEDED){
                        Log.e(TAG, "Work Finished")
                    }
                })

        val progressRequest = OneTimeWorkRequest.Builder(KTProgressWorker::class.java)
                .setInitialDelay(1, TimeUnit.SECONDS)
                .build()

        WorkManager.getInstance(this).enqueue(progressRequest)

        WorkManager.getInstance(this).getWorkInfoByIdLiveData(progressRequest.id)
                .observe(this, Observer { workInfo ->
                    val progress = workInfo.progress;
                    val value = progress.getInt("PROGRESS", 0)
                    Log.e(TAG, value.toString())

                    if(workInfo.state == WorkInfo.State.SUCCEEDED){
                        Log.e(TAG, "Cancel Work")
                        WorkManager.getInstance(applicationContext).cancelWorkById(progressRequest.id)
                    }
                })
    }
}