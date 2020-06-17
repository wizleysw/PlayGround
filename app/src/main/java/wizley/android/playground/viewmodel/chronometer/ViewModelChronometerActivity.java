package wizley.android.playground.viewmodel.chronometer;

import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Chronometer;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;

import wizley.android.playground.R;

public class ViewModelChronometerActivity extends AppCompatActivity {
    private static String TAG = "ViewModelActivity";

    private MyViewModel model;

    private Chronometer chronometer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewmodel_chronometer);

        chronometer = (Chronometer) findViewById(R.id.chronometer);

        model = new ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())).get(MyViewModel.class);

        Log.e(TAG, String.valueOf(model.getTimeTick()));

        chronometer.setBase(model.getStartTime());
        chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                model.setTimeTick(model.getTimeTick()+1);
            }
        });

        final Observer<Long>  tickObserver = new Observer<Long>(){
            @Override
            public void onChanged(Long aLong) {
                Log.e(TAG, String.valueOf(model.getTimeTick()));
            }
        };

        model.getTick().observe(this, tickObserver);

        chronometer.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy");
        if(isFinishing()){
            Log.e(TAG, "isFinishing");
        } else {
            Log.e(TAG, "Rotating orientation");
        }
    }

}
