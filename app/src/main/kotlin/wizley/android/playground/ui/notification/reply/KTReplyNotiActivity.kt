package wizley.android.playground.ui.notification.reply

import android.app.*
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import wizley.android.playground.R
import wizley.android.playground.components.activity.pending.KTPendingNotificationActivity
import wizley.android.playground.components.activity.pending.PendingNotificationActivity

class KTReplyNotiActivity : AppCompatActivity(), View.OnClickListener{
    private val KEY_TEXT_REPLY = "key_text_reply"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_button)

        val button : Button = findViewById(R.id.button)
        button.setOnClickListener(this)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onClick(v: View?) {
        if(v!!.id == R.id.button){
            generateNotification()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun generateNotification() {
        val notificationId = 100

        val replyLabel = "Reply"
        val remoteInput = RemoteInput.Builder(KEY_TEXT_REPLY)
                .setLabel(replyLabel)
                .build()

        val replyPendingIntent = PendingIntent.getBroadcast(this, 0,
                Intent(this, KTReplyNotiReceiver::class.java), PendingIntent.FLAG_UPDATE_CURRENT)

        val action = Notification.Action.Builder(R.drawable.ic_baseline_add_24,
        "REPLY", replyPendingIntent)
                .addRemoteInput(remoteInput)
                .build()

        val notificationIntent = Intent(this, PendingNotificationActivity::class.java)
        notificationIntent.putExtra("notificationId", notificationId)

        val notificationManager : NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val channel = NotificationChannel("channelId", "pendingActivity", NotificationManager.IMPORTANCE_DEFAULT)
        notificationManager.createNotificationChannel(channel)

        val notification = Notification.Builder(this, "channelId")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setTicker("pendingActivity")
                .setWhen(System.currentTimeMillis())
                .setContentTitle("Notification")
                .setContentText("Plez Reply")
                .setAutoCancel(true)
                .addAction(action)
                .build();

        notificationManager.notify(notificationId, notification)
    }

}