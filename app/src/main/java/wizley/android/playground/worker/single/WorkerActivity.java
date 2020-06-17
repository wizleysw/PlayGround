package wizley.android.playground.worker.single;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import java.util.concurrent.TimeUnit;

public class WorkerActivity extends AppCompatActivity {
    private static final String TAG = "WorkerActivity";

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.e(TAG, "onCreate");

        Constraints constraints = new Constraints.Builder()
                .setRequiresDeviceIdle(false)
                .setRequiresCharging(true)
                .build();

        final OneTimeWorkRequest workRequest = new OneTimeWorkRequest.Builder(MyWorker.class)
                .setConstraints(constraints)
                .setInitialDelay(3, TimeUnit.SECONDS)
                .build();

        WorkManager.getInstance(this).enqueue(workRequest);

        WorkManager.getInstance(this).getWorkInfoByIdLiveData(workRequest.getId())
                .observe(this, new Observer<WorkInfo>(){
                    @Override
                    public void onChanged(WorkInfo workInfo) {
                        if(workInfo != null && workInfo.getState() == WorkInfo.State.SUCCEEDED){
                            Log.e(TAG, "Work Finished");
                        }
                    }
                });


        final OneTimeWorkRequest progressRequest = new OneTimeWorkRequest.Builder(ProgressWorker.class)
                .setInitialDelay(1, TimeUnit.SECONDS)
                .build();

        WorkManager.getInstance(this).enqueue(progressRequest);

        WorkManager.getInstance(this).getWorkInfoByIdLiveData(progressRequest.getId())
                .observe(this, new Observer<WorkInfo>() {
                    @Override
                    public void onChanged(WorkInfo workInfo) {
                        Data progress = workInfo.getProgress();
                        int value = progress.getInt("PROGRESS", 0);
                        Log.e(TAG, String.valueOf(value));

                        if(workInfo.getState() == WorkInfo.State.SUCCEEDED){
                            Log.e(TAG, "Cancel Work");
                            WorkManager.getInstance(getApplicationContext()).cancelWorkById(progressRequest.getId());
                        }
                    }
                });
    }
}
