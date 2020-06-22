package wizley.android.playground.notification.message;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.app.Person;
import androidx.core.graphics.drawable.IconCompat;

import wizley.android.playground.R;

public class MessageNotiActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MessageNotiActivity";

    private Button button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button);

        button = findViewById(R.id.button);
        button.setOnClickListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.button){
            generateNotification();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void generateNotification() {
        final int notificationId = 100;

        Person user1 = new Person.Builder().setIcon(IconCompat.createWithResource(this,R.drawable.ic_launcher_foreground)).setName("Wizley").build();
        Person user2 = new Person.Builder().setIcon(IconCompat.createWithResource(this,R.drawable.ic_launcher_foreground)).setName("Genie").build();

        NotificationCompat.MessagingStyle.Message message1 =
                new NotificationCompat.MessagingStyle.Message("Ping", System.currentTimeMillis(), user1);

        NotificationCompat.MessagingStyle.Message message2 =
                new NotificationCompat.MessagingStyle.Message("Pong", System.currentTimeMillis(), user2);

        final NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

        final NotificationChannel channel = new NotificationChannel("channel_id", "ProgressNoti", NotificationManager.IMPORTANCE_DEFAULT);
        notificationManager.createNotificationChannel(channel);

        Notification notification = new NotificationCompat.Builder(this, "channel_id")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setStyle(new NotificationCompat.MessagingStyle(user1)
                        .addMessage(message1)
                        .addMessage(message2))
                .build();

        notificationManager.notify(notificationId, notification);
    }
}
