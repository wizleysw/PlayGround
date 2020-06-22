package wizley.android.playground.notification.reply

import android.app.Notification
import android.app.RemoteInput
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationManagerCompat
import wizley.android.playground.R

class KTReplyNotiReceiver : BroadcastReceiver(){
    val TAG = "KTReplyNotiReceiver"
    val KEY_TEXT_REPLY = "key_text_reply"

    @RequiresApi(Build.VERSION_CODES.KITKAT_WATCH)
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.e(TAG, "onReceive")

        getMessage(context, intent)
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT_WATCH)
    private fun getMessage(context: Context?, intent: Intent?) : CharSequence? {
        Log.e(TAG, "getMessage")
        val remoteInput = RemoteInput.getResultsFromIntent(intent)
        if(remoteInput != null){
            val res = remoteInput.getCharSequence(KEY_TEXT_REPLY)
            Log.e(TAG, res.toString())

            if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O){
                val repliedNotification = Notification.Builder(context, "channelId")
                        .setSmallIcon(R.drawable.ic_launcher_foreground)
                        .setContentText("OK")
                        .build()

                val notificationid = 100
                val notificationManager = NotificationManagerCompat.from(context!!)
                notificationManager.notify(notificationid, repliedNotification)
            }

            return res
        }
        return null
    }

}