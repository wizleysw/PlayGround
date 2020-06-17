package wizley.android.playground.viewmodel.counter;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import wizley.android.playground.R;

public class ViewModelCounterObserveActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "ViewModelCounterObserveActivity";

    private FloatingActionButton fabPlus;
    private FloatingActionButton fabMinus;
    private TextView textView;

    CounterViewModel model;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewmodel_counter);

        model = new ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())).get(CounterViewModel.class);

        fabPlus = (FloatingActionButton) findViewById(R.id.fabPlus);
        fabMinus = (FloatingActionButton) findViewById(R.id.fabMinus);
        textView = (TextView) findViewById(R.id.textView);

        textView.setText(model.getValue() + "");

        fabPlus.setOnClickListener(this);
        fabMinus.setOnClickListener(this);

        final Observer<Integer> counterObserver = new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                textView.setText(integer + "");
            }
        };

        model.getCounter().observe(this, counterObserver);
    }

    @SuppressLint("LongLogTag")
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(isFinishing()){
            Log.e(TAG, "isFinishing");
        } else {
            Log.e(TAG, "Rotating orientation");
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fabPlus:
                model.increase();
                break;
            case R.id.fabMinus:
                model.decrease();
                break;
        }
    }
}
