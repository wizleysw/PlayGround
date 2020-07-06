package wizley.android.playground.components.service.bindservice;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import wizley.android.playground.R;

public class BindServiceActivity extends AppCompatActivity implements View.OnClickListener {
    private MyBinderService service;
    private boolean mBound = false;
    private Button button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button);

        button = findViewById(R.id.button);
        button.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

        Intent intent = new Intent(this, MyBinderService.class);
        bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unbindService(connection);
        mBound = false;
    }

    // this is callback for service binding, passed to bindService
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MyBinderService.LocalBinder binder = (MyBinderService.LocalBinder) service;
            BindServiceActivity.this.service = binder.getService();
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mBound = false;
        }
    };

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.button){
            if(mBound){
                int num = service.getRandomNumber();
                Toast.makeText(this, "number : " + num, Toast.LENGTH_LONG).show();
            }
        }
    }
}
