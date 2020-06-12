package wizley.android.playground.components.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;

import wizley.android.playground.R;

public class NavigateFromActivity extends Activity implements View.OnClickListener {
    private static final int NEW_INTENT_REQUEST = 200;
    private static final int NEW_INTENT_RESPONSE = 300;

    private static final String TAG = "NavigateFromActivity";

    private Button start_activity;
    private Button start_activity_for_result;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigatefrom);

        start_activity = (Button) findViewById(R.id.start_btn);
        start_activity_for_result = (Button) findViewById(R.id.start_for_result_btn);

        start_activity.setOnClickListener(this);
        start_activity_for_result.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.start_btn:
                Log.e(TAG, "startActivity");
                Intent start_intent = new Intent(this, NavigateToActivity.class);
                start_intent.putExtra("status", 1);
                startActivity(start_intent);
                break;
            case R.id.start_for_result_btn:
                Log.e(TAG, "startActivityForResult");
                Intent start_for_result_intent = new Intent(this, NavigateToActivity.class);
                start_for_result_intent.putExtra("status", 2);
                startActivityForResult(start_for_result_intent, NEW_INTENT_REQUEST);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == NEW_INTENT_REQUEST) {
            if (resultCode == NEW_INTENT_RESPONSE) {
                Log.e(TAG, "Intent was successful");
            } else {
                Log.e(TAG, "Intent was not successful");
            }
        }
    }
}
