package wizley.android.playground.worker.single

import android.content.Context
import android.util.Log
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.lang.Exception

class KTProgressWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {
    private val TAG = "ktProgressWorker";

    private val DELAY = 1000L

    init {
        setProgressAsync(Data.Builder().putInt("PROGRESS", 0).build())
    }

    override fun doWork(): Result {
        Log.e(TAG, "doWork")

        try {
            Thread.sleep(DELAY)
            setProgressAsync(Data.Builder().putInt("PROGRESS", 20).build())
            Thread.sleep(DELAY)
            setProgressAsync(Data.Builder().putInt("PROGRESS", 40).build())
            Thread.sleep(DELAY)
            setProgressAsync(Data.Builder().putInt("PROGRESS", 60).build())
            Thread.sleep(DELAY)
            setProgressAsync(Data.Builder().putInt("PROGRESS", 80).build())
            Thread.sleep(DELAY)
            setProgressAsync(Data.Builder().putInt("PROGRESS", 100).build())
        } catch(e : Exception){
            e.printStackTrace()
            return Result.failure()
        }

        Log.e(TAG, "set progress as 100")
        return Result.success()
    }


}