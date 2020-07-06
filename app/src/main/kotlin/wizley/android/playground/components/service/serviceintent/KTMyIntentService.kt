package wizley.android.playground.components.service.serviceintent

import android.app.IntentService
import android.content.Intent
import android.os.IBinder
import android.util.Log

// This will be executed sequentially in new thread
class KTMyIntentService : IntentService("KTMyIntentService") {
    private val TAG = "KTMyIntentService"

    override fun onCreate() {
        super.onCreate()
        Log.e(TAG, "onCreate")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.e(TAG, "onStartCommand")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent?): IBinder? {
        Log.e(TAG, "onBind")
        return super.onBind(intent)
    }

    override fun onHandleIntent(intent: Intent?) {
        Log.e(TAG, "onHandleIntent")

        Thread.sleep(3000)
        Log.e(TAG, "3 second")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e(TAG, "onDestroy")
    }
}