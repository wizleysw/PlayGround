package wizley.android.playground.components.activity.fragment.lifecycle;

import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import wizley.android.playground.R;

public class FragmentActivity extends androidx.fragment.app.FragmentActivity implements SimpleFragment.FragmentCallback{
    private static final String TAG = "FragmentActivity";

    FrameLayout frameLayoutA;
    FrameLayout frameLayoutB;

    SimpleFragment fragmentA;
    SimpleFragment fragmentB;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.e(TAG, "onCreate");

        setContentView(R.layout.activity_fragment);

        // If this not added, fragment which restored will be created
        // as well as new fragment
        if(savedInstanceState != null){
            return;
        }

        frameLayoutA = findViewById(R.id.fragmentA);
        frameLayoutB = findViewById(R.id.fragmentB);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentA = new SimpleFragment();
        fragmentB = new SimpleFragment();

        fragmentTransaction.add(R.id.fragmentA, fragmentA);
//        fragmentTransaction.add(R.id.fragmentB, fragmentB);
        fragmentTransaction.commit();
    }

    @Override
    public void onAttachFragment(@NonNull Fragment fragment) {
        super.onAttachFragment(fragment);

        Log.e(TAG, "onAttachFragment");
    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.e(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.e(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.e(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.e(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.e(TAG, "onDestroy");
    }


    @Override
    public void onImageChanged() {
        Log.e(TAG, "onImageChanged");
    }
}
