package wizley.android.playground.databinding;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import wizley.android.playground.R;

public class DataBindingActivity extends AppCompatActivity {
    private static final String TAG = "DataBindingActivity";

    ActivityDatabindingBinding binding;
    MyViewModel model;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // important !!!
        // root of layout should be layout
        // if not error => no unique maximal instance exists for type variable T with upper bounds
        binding = DataBindingUtil.setContentView(this, R.layout.activity_databinding);

        model = new ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())).get(MyViewModel.class);

        binding.setCustomViewModel(model);
        binding.setLifecycleOwner(this);
    }
}
