package wizley.android.playground.worker.chained

import android.content.Context
import android.util.Log
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters

class ktWorkerC(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {
    private val TAG = "ktWorkerC";

    override fun doWork(): Result {
        Log.e(TAG, "doWork")

        val value1 = inputData.getInt("valueA", 0)
        val value2 = inputData.getInt("valueB", 0)

        if(value1 == 0 || value2 == 0) return Result.failure()

        val value = value1 + value2

        val output = Data.Builder()
                .putInt("RESULT", value)
                .build();

        return Result.success(output)
    }
}