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

import androidx.annotation.Nullable;

import java.util.List;

import wizley.android.playground.R;

public class SendTextActivity extends Activity implements View.OnClickListener {
    private static String TAG = "SendTextActivity";
    private static int SEND_TEXT = 100;

    private Button button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_implicit);

        button = (Button) findViewById(R.id.implicit_button);
        button.setText("Send Text");
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.implicit_button){
            Intent newIntent = new Intent(Intent.ACTION_SEND);
            newIntent.setType("text/plain");
            newIntent.putExtra(Intent.EXTRA_TEXT, "Hello Wizley!");

            PackageManager packageManager = getPackageManager();
            List<ResolveInfo> activities = packageManager.queryIntentActivities(newIntent, 0);
            boolean isIntentSafe = activities.size() > 0;

            if(isIntentSafe) {
                String title = getResources().getString(R.string.chooser_title);
                Intent chooser = Intent.createChooser(newIntent, title);

                if (newIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(chooser, SEND_TEXT);
                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == SEND_TEXT){
            if(resultCode == RESULT_OK){
                button.setText("Send Text OK!");
            }
        }
    }
}
