package wizley.android.playground.components.service.bindservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.Random;

public class MyBinderService extends Service {
    private static final String TAG = "MyBinderService";

    private final IBinder binder = new LocalBinder();
    private final Random mGenerator = new Random();

    public class LocalBinder extends Binder {
        MyBinderService getService(){
            Log.e(TAG, "getService");
            return MyBinderService.this;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e(TAG, "onBind");
        return binder;
    }

    public int getRandomNumber(){
        Log.e(TAG, "getRandomNumber");
        return mGenerator.nextInt(100);
    }

    @Override
    public void onDestroy() {
        Log.e(TAG, "onDestroy");
        super.onDestroy();
    }
}
