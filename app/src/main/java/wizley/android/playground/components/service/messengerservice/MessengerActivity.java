package wizley.android.playground.components.service.messengerservice;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import wizley.android.playground.R;

public class MessengerActivity extends AppCompatActivity implements View.OnClickListener {
    private Messenger messenger;
    private boolean bound;

    private Button button;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            messenger = new Messenger(service);
            bound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            messenger = null;
            bound = false;
        }
    };

    public void sayHello() throws RemoteException {
        if(!bound) return;
        Message msg = Message.obtain(null, MessengerService.MSG_SAY_HELLO, 0, 0);
        messenger.send(msg);
    }

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

        bindService(new Intent(this, MessengerService.class), connection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();

        if(bound){
            unbindService(connection);
            bound = false;
        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.button && bound){
            try {
                sayHello();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }
}
