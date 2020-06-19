package wizley.android.playground.components.activity.fragment.resultlistener;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import wizley.android.playground.R;

public class FragmentActivity extends androidx.fragment.app.FragmentActivity {
    private static final String TAG = "FragmentActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        Log.e(TAG, "onCreate");

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        FragmentA fragmentA = new FragmentA();
        FragmentB fragmentB = new FragmentB();

        fragmentTransaction.add(R.id.fragmentA, fragmentA, "A");
        fragmentTransaction.add(R.id.fragmentB, fragmentB, "B");
        fragmentTransaction.commit();
    }
}
