package wizley.android.playground.ui.settings;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import wizley.android.playground.R;

public class PreferenceActivity extends AppCompatActivity {
    private static final String TAG = "PreferenceActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preference);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment, new PreferenceFragment())
                .commit();

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        Boolean notification = sharedPreferences.getBoolean("notifications", false);

        Log.e(TAG, String.valueOf(notification));
    }

}
