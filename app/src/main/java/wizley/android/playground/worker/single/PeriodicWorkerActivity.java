package wizley.android.playground.worker.single;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.work.Constraints;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import java.util.concurrent.TimeUnit;

public class PeriodicWorkerActivity extends AppCompatActivity {
    private static final String TAG = "PeriodicWorkerActivity";

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.e(TAG, "onCreate");

        Constraints constraints = new Constraints.Builder()
                .setRequiresCharging(true)
                .build();

        // This can not be chained
        // min period length is 15 minutes
        PeriodicWorkRequest request = new PeriodicWorkRequest.Builder(MyWorker.class, 15, TimeUnit.MINUTES, 5, TimeUnit.MINUTES)
                .setConstraints(constraints)
                .build();

        WorkManager.getInstance(this).enqueue(request);

    }
}