package wizley.android.playground.ui.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class CustomDialogFragment extends DialogFragment {
    private static final String TAG = "CustomDialogFragment";

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Log.e(TAG, "onCreateDialog");

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Are you OK ?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.e(TAG, "I am OK!");
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.e(TAG, "I am not OK!");
                    }
                });

        return builder.create();
    }
}
