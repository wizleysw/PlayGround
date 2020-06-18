package wizley.android.playground.components.activity.pending

import android.app.NotificationManager
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import wizley.android.playground.databinding.ActivityPendingBinding

class KTPendingNotificationActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityPendingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val notificationId = intent.getIntExtra("notificationId", 0)
        binding.button.text = notificationId.toString()

        val notificationManager : NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.cancel(notificationId)
    }
}

