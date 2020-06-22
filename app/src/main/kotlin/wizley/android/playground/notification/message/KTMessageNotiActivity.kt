package wizley.android.playground.notification.message

import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.app.Person
import androidx.core.graphics.drawable.IconCompat
import wizley.android.playground.R

class KTMessageNotiActivity : AppCompatActivity(), View.OnClickListener{
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

        val user1 = Person.Builder().setIcon(IconCompat.createWithResource(this, R.drawable.ic_launcher_foreground)).setName("Wizley").build()
        val user2 = Person.Builder().setIcon(IconCompat.createWithResource(this, R.drawable.ic_launcher_foreground)).setName("Genie").build()

        val message1 = NotificationCompat.MessagingStyle.Message("Ping", System.currentTimeMillis(), user1)
        val message2 = NotificationCompat.MessagingStyle.Message("Pong", System.currentTimeMillis(), user2)

        val notificationManager = NotificationManagerCompat.from(this)

        val channel = NotificationChannel("channel_id", "MessageNoti", NotificationManager.IMPORTANCE_DEFAULT)
        notificationManager.createNotificationChannel(channel)

        val notification = NotificationCompat.Builder(this, "channel_id")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setStyle(NotificationCompat.MessagingStyle(user1)
                        .addMessage(message1)
                        .addMessage(message2))
                .build();

        notificationManager.notify(notificationId, notification)
    }

}