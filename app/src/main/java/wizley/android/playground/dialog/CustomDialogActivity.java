package wizley.android.playground.dialog;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

public class CustomDialogActivity extends FragmentActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FragmentManager fm = getSupportFragmentManager();
        CustomDialogFragment dialogFragment = new CustomDialogFragment();
        dialogFragment.show(fm, "dialog fragment");
    }
}
