package wizley.android.playground.components.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.Lifecycle.Event;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;

import wizley.android.playground.R;

public class LifeCycleActivity extends Activity implements LifecycleOwner {

    private static final String VALUE = "";
    Button btn;
    String value;

    private LifecycleRegistry lifecycleRegistry;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(savedInstanceState != null){
            value = savedInstanceState.getString(VALUE);
        }

        lifecycleRegistry = new LifecycleRegistry(this);
        lifecycleRegistry.addObserver(new CustomLifeCycleObserver());
        lifecycleRegistry.setCurrentState(Lifecycle.State.CREATED);

        setContentView(R.layout.activity_lifecycle);

        btn = (Button) findViewById(R.id.button);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        btn.setText(savedInstanceState.getString(VALUE));
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putString(VALUE, value);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onStart() {
        super.onStart();

        lifecycleRegistry.setCurrentState(Lifecycle.State.STARTED);
    }

    @Override
    protected void onResume() {
        super.onResume();

        lifecycleRegistry.setCurrentState(Lifecycle.State.RESUMED);
    }

    @Override
    protected void onPause() {
        super.onPause();

        lifecycleRegistry.setCurrentState(Lifecycle.State.STARTED);
    }

    @Override
    protected void onStop() {
        super.onStop();

        lifecycleRegistry.setCurrentState(Lifecycle.State.CREATED);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        lifecycleRegistry.setCurrentState(Lifecycle.State.DESTROYED);
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        lifecycleRegistry.setCurrentState(Lifecycle.State.CREATED);
    }

    @NonNull
    @Override
    public Lifecycle getLifecycle() {
        return lifecycleRegistry;
    }
}
