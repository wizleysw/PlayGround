package wizley.android.playground.ui.pip;

import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import wizley.android.playground.R;

// add android:supportsPictureInPicture="true" to manifest
public class PIPActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "PIPActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pip);

        Button btn = findViewById(R.id.button);
        btn.setOnClickListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.button){
            Log.e(TAG, "enterPictureInPictureMode");

            PackageManager pm = this.getPackageManager();
            if(pm.hasSystemFeature(PackageManager.FEATURE_PICTURE_IN_PICTURE)){
                 this.enterPictureInPictureMode();
            }
        }
    }

    @Override
    public void onPictureInPictureModeChanged(boolean isInPictureInPictureMode, Configuration newConfig) {
        if(isInPictureInPictureMode){
            Log.e(TAG, "isInPictureInPictureMode");
        } else {
            Log.e(TAG, "Not isInPictureInPictureMode");
        }
        super.onPictureInPictureModeChanged(isInPictureInPictureMode, newConfig);
    }
}
