package wizley.android.playground.worker.chained;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class WorkerA extends Worker {
    private static final String TAG = "WorkerA";

    public WorkerA(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        Log.e(TAG, "doWork");
        int value = getInputData().getInt("Value", 0);
        if(value == 0) return Result.failure();
        return Result.success();
    }
}
