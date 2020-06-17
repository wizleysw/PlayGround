package wizley.android.playground.worker

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class ktMyWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {
    private val TAG = "ktMyWorker";

    private val name = "Wizley"

    override fun doWork(): Result {
        Log.e(TAG, "doWork")

        var res = checkName()

        return if(res){
            Log.e(TAG, "success")
            Result.success()
        } else {
            Log.e(TAG, "failure")
            Result.failure();
        }
    }

    fun checkName() : Boolean{
        return name == "Wizley"
    }

}