package wizley.android.playground.worker.chained

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class KTWorkerD(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {
    private val TAG = "ktWorkerD";

    override fun doWork(): Result {
        Log.e(TAG, "doWork")

        val value = inputData.getInt("value", 0)
        if(value == 0) return Result.failure()
        return Result.success()
    }
}