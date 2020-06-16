package wizley.android.playground.components.activity.implicit;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.List;

import wizley.android.playground.R;

public class PackageManagerActivity extends Activity implements View.OnClickListener {
    private static String TAG = "PackageManagerActivity";

    private Button button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_implicit);

        button = (Button) findViewById(R.id.implicit_button);
        button.setText("PhoneCall with PackageManager");
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.implicit_button){
            Uri number = Uri.parse("tel:5551234");
            Intent callIntent = new Intent(Intent.ACTION_DIAL, number);

            PackageManager packageManager = getPackageManager();
            List<ResolveInfo> activities = packageManager.queryIntentActivities(callIntent, 0);
            boolean isIntentSafe = activities.size() > 0;

            Log.e(TAG, String.valueOf(activities.size()));

            if(isIntentSafe) {
                startActivity(callIntent);
            } else {
                Toast.makeText(this, "It is not safe to call Intent", Toast.LENGTH_LONG).show();
            }
        }
    }
}
