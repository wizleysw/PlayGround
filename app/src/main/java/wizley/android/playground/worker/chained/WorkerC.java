package wizley.android.playground.worker.chained;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class WorkerC extends Worker {
    private static final String TAG = "WorkerC";

    public WorkerC(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        Log.e(TAG, "doWork");
        int value1 = getInputData().getInt("ValueA", 0);
        int value2 = getInputData().getInt("ValueB", 0);

        int value = value1 + value2;
        if(value1 == 0 || value2 == 0) return Result.failure();

        Data output = new Data.Builder()
                .putInt("RESULT", value)
                .build();

        Log.e(TAG, "return Data Result");
        return Result.success(output);
    }
}
