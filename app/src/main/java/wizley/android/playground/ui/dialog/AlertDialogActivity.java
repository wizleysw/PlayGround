package wizley.android.playground.ui.dialog;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


public class AlertDialogActivity extends AppCompatActivity {
    private static final String TAG = "AlertDialogActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String[] items = {"Red", "Green", "Blue"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("This is Title")
                // .setMessage("This is Message")
                .setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.e(TAG, "onClicked");
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
