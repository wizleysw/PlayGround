package wizley.android.playground.components.activity.implicit;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;

import wizley.android.playground.R;

public class MapCallActivity extends Activity implements View.OnClickListener {
    private static String TAG = "MapCallActivity";

    private Button button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_implicit);

        button = (Button) findViewById(R.id.implicit_button);
        button.setText("MapCall");
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.implicit_button){
            Uri location = Uri.parse("geo:0,0?q=1600+Amphitheatre+Parkway,+Mountain+View,+California");
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, location);
            startActivity(mapIntent);
        }
    }
}
