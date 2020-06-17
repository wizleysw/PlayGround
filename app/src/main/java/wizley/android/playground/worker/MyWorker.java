package wizley.android.playground.worker;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class MyWorker extends Worker {
    private static final String TAG = "MyWorker";

    private static String name = "Wizley";

    public MyWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        Log.e(TAG, "doWork");

        boolean res = checkName();

        if(res){
            Log.e(TAG, "success");
            return Result.success();
        } else {
            Log.e(TAG, "failure");
            return Result.failure();
//             return Result.retry();
        }
    }

    private boolean checkName(){
        return name.equals("Wizley");
    }
}
