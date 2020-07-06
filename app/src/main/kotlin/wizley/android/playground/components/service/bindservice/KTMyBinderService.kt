package wizley.android.playground.components.service.bindservice

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log
import java.util.*

private const val TAG = "KTMyBinderService"

class KTMyBinderService : Service(){
    private val binder = KTLocalBinder()

    inner class KTLocalBinder : Binder() {
        fun getService() : KTMyBinderService = this@KTMyBinderService
    }

    override fun onBind(intent: Intent?): IBinder {
        Log.e(TAG, "onBind")
        return binder
    }

    private val mGenerator = Random()

    val randomNumber : Int
        get() = mGenerator.nextInt(100)


    override fun onDestroy() {
        Log.e(TAG, "onDestroy")
        super.onDestroy()
    }
}