package wizley.android.playground.components.activity.explicit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;

import wizley.android.playground.R;


public class NavigateToActivity extends Activity implements View.OnClickListener {
    private static final int NEW_INTENT_RESPONSE = 300;

    private static final String TAG = "NavigateToActivity";
    private int status = -1;

    private Button ok;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigateto);

        status = getIntent().getIntExtra("status", -1);

        if(status == -1){
            finish();
        }

        ok = (Button) findViewById(R.id.ok);

        ok.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ok:
                Intent intent = new Intent();
                if(status == 2){
                    setResult(NEW_INTENT_RESPONSE, intent);
                }
                Log.e(TAG, "RETURN TO NavigateFromActivity");
                finish();
                break;
            default:
                break;
        }
    }
}
