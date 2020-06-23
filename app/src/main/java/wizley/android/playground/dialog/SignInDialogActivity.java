package wizley.android.playground.dialog;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import wizley.android.playground.R;

public class SignInDialogActivity extends FragmentActivity implements SignInDialogFragment.SignInDialogListener {
    private static final String TAG = "SignInDialogActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FragmentManager fm = getSupportFragmentManager();
        SignInDialogFragment dialogFragment = new SignInDialogFragment();
        dialogFragment.show(fm, "dialog fragment");
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        Log.e(TAG, "PositiveClick");
        EditText username = dialog.getDialog().findViewById(R.id.username);
        EditText password = dialog.getDialog().findViewById(R.id.password);
        Log.e(TAG, String.valueOf(username.getText()));
        Log.e(TAG, String.valueOf(password.getText()));
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        Log.e(TAG, "NegativeClick");
    }
}
