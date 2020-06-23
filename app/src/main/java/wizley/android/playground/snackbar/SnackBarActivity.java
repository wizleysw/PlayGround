package wizley.android.playground.snackbar;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import wizley.android.playground.R;

public class SnackBarActivity extends AppCompatActivity {
    private static final String TAG = "SnackBarActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snackbar);

        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.activity_snackbar, (ViewGroup) findViewById(R.id.coordinatorLayout));

        Snackbar snackbar = Snackbar.make(layout, "SnackBar", Snackbar.LENGTH_LONG);
        snackbar.setAction("UNDO", new UndoListener());
        snackbar.show();

        // easy way
        // Snackbar.make(findViewById(R.id.coordinatorLayout), "SnackBar", Snackbar.LENGTH_LONG).show();
    }

    public class UndoListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Log.e(TAG, "onClick");
        }
    }
}
