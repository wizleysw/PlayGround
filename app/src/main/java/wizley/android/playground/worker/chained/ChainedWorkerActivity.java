package wizley.android.playground.worker.chained;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

import java.util.Arrays;

public class ChainedWorkerActivity extends AppCompatActivity {
    private static String TAG = "ChainedWorkerActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.e(TAG, "onCreate");

        Constraints constraints = new Constraints.Builder()
                .setRequiresCharging(true)
                .build();

        Data.Builder data1 = new Data.Builder();
        data1.putInt("Value", 5);

        Data.Builder data2 = new Data.Builder();
        data2.putInt("Value", 8);

        Data.Builder data3 = new Data.Builder();
        data3.putInt("ValueA", 1);
        data3.putInt("ValueB", 2);

        final OneTimeWorkRequest workA = new OneTimeWorkRequest.Builder(WorkerA.class)
                .setConstraints(constraints)
                .setInputData(data1.build())
                .build();

        final OneTimeWorkRequest workB = new OneTimeWorkRequest.Builder(WorkerB.class)
                .setConstraints(constraints)
                .setInputData(data2.build())
                .build();

        final OneTimeWorkRequest workC = new OneTimeWorkRequest.Builder(WorkerC.class)
                .setConstraints(constraints)
                .setInputData(data3.build())
                .build();

        final OneTimeWorkRequest workD = new OneTimeWorkRequest.Builder(WorkerD.class)
                .setConstraints(constraints)
                .setInputData(data1.build())
                .build();

        final OneTimeWorkRequest workE = new OneTimeWorkRequest.Builder(WorkerE.class)
                .setConstraints(constraints)
                .setInputData(data2.build())
                .build();


        boolean hasPrecedence = false;

        if(hasPrecedence){
            WorkManager.getInstance(this)
                    .beginWith(Arrays.asList(workA, workB))
                    .then(workC)
                    .then(Arrays.asList(workD, workE))
                    .enqueue();
        } else {
            WorkManager.getInstance(this)
                    .beginWith(workA)
                    .then(workB)
                    .then(workC)
                    .enqueue();
        }

        WorkManager.getInstance(this).getWorkInfoByIdLiveData(workC.getId())
                .observe(this, new Observer<WorkInfo>() {
                    @Override
                    public void onChanged(WorkInfo info) {
                        if (info != null && info.getState().isFinished()) {
                            int output = info.getOutputData().getInt("RESULT", -1);
                            Log.e(TAG, String.valueOf(output));
                        }
                    }
                });
    }
}
