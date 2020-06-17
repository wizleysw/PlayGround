package wizley.android.playground.worker;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class ProgressWorker extends Worker {
    private static final String TAG = "ProgressWorker";

    private static final long DELAY = 1000L;

    public ProgressWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);

        setProgressAsync(new Data.Builder().putInt("PROGRESS", 0).build());
    }

    @NonNull
    @Override
    public Result doWork() {
        Log.e(TAG, "doWork");

        try {
            Thread.sleep(DELAY);
            setProgressAsync(new Data.Builder().putInt("PROGRESS", 20).build());
            Thread.sleep(DELAY);
            setProgressAsync(new Data.Builder().putInt("PROGRESS", 40).build());
            Thread.sleep(DELAY);
            setProgressAsync(new Data.Builder().putInt("PROGRESS", 60).build());
            Thread.sleep(DELAY);
            setProgressAsync(new Data.Builder().putInt("PROGRESS", 80).build());
            Thread.sleep(DELAY);
            setProgressAsync(new Data.Builder().putInt("PROGRESS", 100).build());
            Thread.sleep(DELAY);
        } catch (Exception e){
            e.printStackTrace();
            return Result.failure();
        }

        Log.e(TAG, "set progress as 100");

        return Result.success();
    }
}
