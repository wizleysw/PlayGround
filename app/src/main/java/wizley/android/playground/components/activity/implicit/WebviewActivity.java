package wizley.android.playground.components.activity.implicit;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;

import wizley.android.playground.R;

public class WebviewActivity extends Activity implements View.OnClickListener {
    private static String TAG = "WebviewActivity";

    private Button button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_implicit);

        button = (Button) findViewById(R.id.implicit_button);
        button.setText("WebView");
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.implicit_button){
            Uri webpage = Uri.parse("https://bughunting.kr");
            Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
            startActivity(webIntent);
        }
    }
}
