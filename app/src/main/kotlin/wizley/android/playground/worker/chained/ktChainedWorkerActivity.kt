package wizley.android.playground.worker.chained

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.work.*
import java.util.*

class ktChainedWorkerActivity : AppCompatActivity(){
    private val TAG = "ktChainedWorkerActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val constraints = Constraints.Builder()
                .setRequiresCharging(true)
                .build();

        val data1 = Data.Builder()
        data1.putInt("value", 1)

        val data2 = Data.Builder()
        data2.putInt("value", 2)

        val data3 = Data.Builder()
        data3.putInt("valueA", 1)
        data3.putInt("valueB", 2)

        val workA = OneTimeWorkRequest.Builder(ktWorkerA::class.java)
                .setConstraints(constraints)
                .setInputData(data1.build())
                .build();

        val workB = OneTimeWorkRequest.Builder(ktWorkerB::class.java)
                .setConstraints(constraints)
                .setInputData(data2.build())
                .build();

        val workC = OneTimeWorkRequest.Builder(ktWorkerC::class.java)
                .setConstraints(constraints)
                .setInputData(data3.build())
                .build();

        val workD = OneTimeWorkRequest.Builder(ktWorkerD::class.java)
                .setConstraints(constraints)
                .setInputData(data1.build())
                .build();

        val workE = OneTimeWorkRequest.Builder(ktWorkerE::class.java)
                .setConstraints(constraints)
                .setInputData(data2.build())
                .build();


        var hasPrecedence = false


        if(hasPrecedence){
        WorkManager.getInstance(this)
                .beginWith(listOf(workA, workB))
                .then(workC)
                .then(listOf(workD, workE))
                .enqueue();
        } else {
            WorkManager.getInstance(this)
                    .beginWith(workA)
                    .then(workB)
                    .then(workC)
                    .enqueue();
        }

        WorkManager.getInstance(this).getWorkInfoByIdLiveData(workC.id)
                .observe(this, Observer { workInfo ->
                    if(workInfo.state == WorkInfo.State.SUCCEEDED){
                        val output = workInfo.outputData.getInt("RESULT", -1)
                        Log.e(TAG, output.toString())
                    }
                })
    }
}