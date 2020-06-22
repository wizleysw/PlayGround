package wizley.android.playground.notification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
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

class KTBasicNotiActivity : AppCompatActivity(), View.OnClickListener{

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

        val notificationIntent = Intent(this, KTPendingNotificationActivity::class.java)
        notificationIntent.putExtra("notificationId", notificationId)

        val pendingIntent = PendingIntent.getActivity(this,
                0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT)

        val notificationManager : NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val channel = NotificationChannel("channelId", "pendingActivity", NotificationManager.IMPORTANCE_DEFAULT)
        notificationManager.createNotificationChannel(channel)

        val notification = Notification.Builder(this, "channelId")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setTicker("pendingActivity")
                .setWhen(System.currentTimeMillis())
                .setContentTitle("pendingActivity")
                .setContentText("pendingActivity")
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .addAction(R.drawable.ic_baseline_add_24, "ADD", pendingIntent)
                .addAction(R.drawable.ic_baseline_remove_24, "REMOVE", pendingIntent)
                .setStyle(Notification.BigTextStyle()
                        .bigText("this is big text !!!"))
                .build();

        notificationManager.notify(notificationId, notification)
    }

}