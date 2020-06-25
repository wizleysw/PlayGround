package wizley.android.playground.storage;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SharedPrefActivity extends AppCompatActivity {
    private static final String TAG = "SharedPrefActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        writeOK();
        readOK();
    }

    private void writeOK(){
        SharedPreferences sharedPreferences = this.getSharedPreferences("customPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("is_ok", true);
        // commit is synchronized way -> ui rendering could be paused
        editor.apply();
    }

    private Boolean readOK(){
        SharedPreferences sharedPreferences = this.getSharedPreferences("customPref", Context.MODE_PRIVATE);
        Boolean is_ok = sharedPreferences.getBoolean("is_ok", false);

        Log.e(TAG, String.valueOf(is_ok));

        return is_ok;
    }
}
