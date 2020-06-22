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
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import wizley.android.playground.R
import wizley.android.playground.components.activity.pending.KTPendingNotificationActivity

class KTProgressNotiActivity : AppCompatActivity(), View.OnClickListener{

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
        val CHANNEL_ID = "channel_id"

        val notificationManager = NotificationManagerCompat.from(this)

        val channel = NotificationChannel(CHANNEL_ID, "ProgressNoti", NotificationManager.IMPORTANCE_DEFAULT)
        notificationManager.createNotificationChannel(channel)

        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
        builder.setContentTitle("New Download")
                .setContentText("Downloading...")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)

        val thread = Thread(Runnable { ->
            val PROGRESS_MAX = 100
            var PROGRESS_CURRENT = 0

            while(PROGRESS_CURRENT <= 100){
                builder.setProgress(PROGRESS_MAX, PROGRESS_CURRENT, false)
                notificationManager.notify(notificationId, builder.build())
                Thread.sleep(1000)
                PROGRESS_CURRENT += 10
            }

            builder.setContentText("Download Complete")
            builder.setProgress(PROGRESS_MAX, PROGRESS_CURRENT, false)
            notificationManager.notify(notificationId, builder.build())
        })

        thread.start()
    }
}