package wizley.android.playground.components.service.messengerservice;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


public class MessengerService extends Service {
    private static final String TAG = "MessengerService";

    private Messenger messenger;
    static final int MSG_SAY_HELLO = 1;

    static class IncomingHandler extends Handler {
        private static final String TAG = "IncomingHandler";

        private Context applicationContext;

        IncomingHandler(Context context){
            applicationContext = context.getApplicationContext();
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            Log.e(TAG, "handleMessage");

            if (msg.what == MSG_SAY_HELLO) {
                Toast.makeText(applicationContext, "Hello!", Toast.LENGTH_LONG).show();
                try {
                    Thread.sleep(2000);
                    Toast.makeText(applicationContext, "Finished!", Toast.LENGTH_LONG).show();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                super.handleMessage(msg);
            }
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e(TAG, "onBind");
        Toast.makeText(getApplicationContext(), "Binding", Toast.LENGTH_LONG).show();
        messenger = new Messenger(new IncomingHandler(this));
        return messenger.getBinder();
    }

}
