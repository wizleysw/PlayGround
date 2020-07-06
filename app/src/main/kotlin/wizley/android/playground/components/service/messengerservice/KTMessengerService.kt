package wizley.android.playground.components.service.messengerservice

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.os.Message
import android.os.Messenger
import android.util.Log
import android.widget.Toast

const val MSG_SAY_HELLO = 1

class KTMessengerService : Service(){
    private val TAG = "KTMessengerService"

    private lateinit var messenger : Messenger

    internal class IncomingHandler(
            context: Context,
            private val applicationContext: Context = context.applicationContext) : Handler() {
        private val TAG = "IncomingHandler"

        override fun handleMessage(msg: Message) {
            Log.e(TAG, "handleMessage")

            when(msg.what){
                MSG_SAY_HELLO ->
                    Toast.makeText(applicationContext, "hello!", Toast.LENGTH_LONG).show()
                else -> super.handleMessage(msg)
            }
        }
    }

    override fun onBind(intent: Intent?): IBinder? {
        Log.e(TAG, "onBind")
        Toast.makeText(applicationContext, "Binding", Toast.LENGTH_LONG).show()
        messenger = Messenger(IncomingHandler(this))
        return messenger.binder
    }

}