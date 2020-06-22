package wizley.android.playground.notification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import wizley.android.playground.R;
import wizley.android.playground.components.activity.pending.PendingNotificationActivity;

public class BasicNotiActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "BasicNotiActivity";

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

        Intent notificationIntent = new Intent(this, PendingNotificationActivity.class);
        notificationIntent.putExtra("notificationId", notificationId);

        PendingIntent pendingIntent = PendingIntent.getActivity(this,
                0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        NotificationChannel channel = new NotificationChannel("channelId", "pendingActivity", NotificationManager.IMPORTANCE_DEFAULT);
        assert notificationManager != null;
        notificationManager.createNotificationChannel(channel);

        Notification notification = new Notification.Builder(this, "channelId")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setTicker("pendingActivity")
                .setWhen(System.currentTimeMillis())
                .setContentTitle("pendingActivity")
                .setContentText("pendingActivity")
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .addAction(R.drawable.ic_baseline_add_24, "ADD", pendingIntent)
                .addAction(R.drawable.ic_baseline_remove_24, "REMOVE", pendingIntent)
                .build();

        notificationManager.notify(notificationId, notification);
    }
}
