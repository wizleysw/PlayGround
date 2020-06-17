package wizley.android.playground.viewmodel.counter;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import wizley.android.playground.R;

public class ViewModelCounterActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "ViewModelCounterActivity";

    private FloatingActionButton fabPlus;
    private FloatingActionButton fabMinus;
    private TextView textView;

    CounterViewModel model;

    private int counter = 0;

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

    @SuppressLint("SetTextI18n")
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fabPlus:
                model.increase();
                textView.setText(model.getValue() + "");
                break;
            case R.id.fabMinus:
                if(counter >= 1) {
                    model.decrease();
                    textView.setText(model.getValue() + "");
                }
                break;
        }
    }
}
