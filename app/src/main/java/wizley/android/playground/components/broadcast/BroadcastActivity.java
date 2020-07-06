package wizley.android.playground.components.broadcast;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class BroadcastActivity extends AppCompatActivity {
    private MyBroadcastReceiver receiver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        receiver = new MyBroadcastReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("wizley.android.playground.broadcast");
        registerReceiver(receiver, filter);
    }

    @Override
    protected void onStart() {
        super.onStart();

        Intent intent = new Intent();
        intent.setAction("wizley.android.playground.broadcast");
        intent.putExtra("data", "this is broadcast value");
        sendBroadcast(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
