package wizley.android.playground.components.activity.implicit;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {
    private static final String TAG = "SettingsActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        openWifiSettings();
    }

    private void openWifiSettings() {
        Log.e(TAG, "openWifiSettings");
        Intent intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
        if(intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        }
    }
}
