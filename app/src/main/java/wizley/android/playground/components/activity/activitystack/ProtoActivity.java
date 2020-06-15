package wizley.android.playground.components.activity.activitystack;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;

import wizley.android.playground.R;

public class ProtoActivity extends Activity implements View.OnClickListener {
    private static String TAG = "ProtoActivity";

    private Button standard;
    private Button singletop;
    private Button singletask;
    private Button singleinstance;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stack);

        standard = (Button) findViewById(R.id.btn_standard);
        singletop = (Button) findViewById(R.id.btn_singletop);
        singletask = (Button) findViewById(R.id.btn_singletask);
        singleinstance = (Button) findViewById(R.id.btn_singleinstance);

        standard.setOnClickListener(this);
        singletop.setOnClickListener(this);
        singletask.setOnClickListener(this);
        singleinstance.setOnClickListener(this);

        Log.e(TAG, "onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.e(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.e(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG, "onPause");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_standard:
                Intent standard_intent = new Intent(this, StandardActivity.class);
                startActivity(standard_intent);
                break;
            case R.id.btn_singletop:
                Intent singletop_intent = new Intent(this, SingleTopActivity.class);
                startActivity(singletop_intent);
                break;
            case R.id.btn_singletask:
                Intent singletask_intent = new Intent(this, SingleTaskActivity.class);
                startActivity(singletask_intent);
                break;
            case R.id.btn_singleinstance:
                Intent singleinstance_intent = new Intent(this, SingleInstanceActivity.class);
                startActivity(singleinstance_intent);
                break;
        }
    }
}

