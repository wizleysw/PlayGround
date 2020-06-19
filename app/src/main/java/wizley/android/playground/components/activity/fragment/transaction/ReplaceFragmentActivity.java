package wizley.android.playground.components.activity.fragment.transaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import wizley.android.playground.R;
import wizley.android.playground.components.activity.fragment.lifecycle.FragmentActivity;
import wizley.android.playground.components.activity.fragment.lifecycle.SimpleFragment;

public class ReplaceFragmentActivity extends FragmentActivity implements View.OnClickListener {
    private static final String TAG = "ReplaceFragmentActivity";

    private SimpleFragment fragmentA;
    private SimpleFragment fragmentB;

    private Button button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_replace_fragment);

        if(savedInstanceState != null){
            return;
        }

        button = findViewById(R.id.button);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentA = new SimpleFragment();
        Bundle bundle = new Bundle();
        bundle.putString("tag", "A");
        fragmentA.setArguments(bundle);

        fragmentB = new SimpleFragment();
        Bundle bundle2 = new Bundle();
        bundle2.putString("tag", "B");
        fragmentB.setArguments(bundle2);

        fragmentTransaction.add(R.id.fragmentA, fragmentA, "A");
        fragmentTransaction.addToBackStack("A");
        fragmentTransaction.commit();

        button.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.button){
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            int idx = getSupportFragmentManager().getBackStackEntryCount()-1;
            String tag = getSupportFragmentManager().getBackStackEntryAt(idx).getName();

            if(tag.equals("A")){
                fragmentTransaction.replace(R.id.fragmentA, fragmentB, "B");
                fragmentTransaction.addToBackStack("B");
            } else if(tag.equals("B")){
                fragmentTransaction.replace(R.id.fragmentA, fragmentA, "A");
                fragmentTransaction.addToBackStack("A");
            }
            fragmentTransaction.commit();
        }
    }
}
