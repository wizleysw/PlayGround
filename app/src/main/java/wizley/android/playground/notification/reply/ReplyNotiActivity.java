package wizley.android.playground.notification.reply;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.RemoteInput;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import wizley.android.playground.R;
import wizley.android.playground.components.activity.pending.PendingNotificationActivity;

public class ReplyNotiActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "ReplyNotiActivity";

    private static final String KEY_TEXT_REPLY = "key_text_reply";

    private Button button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "onCreate");

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

        String replyLabel = "Reply";
        RemoteInput remoteInput = new RemoteInput.Builder(KEY_TEXT_REPLY)
                .setLabel(replyLabel)
                .build();

        PendingIntent replyPendingIntent = PendingIntent.getBroadcast(this, 0,
                new Intent(this, ReplyNotiReceiver.class), PendingIntent.FLAG_UPDATE_CURRENT);

        Notification.Action action = new Notification.Action.Builder(R.drawable.ic_baseline_add_24,
                "REPLY", replyPendingIntent)
                .addRemoteInput(remoteInput)
                .build();

        Intent notificationIntent = new Intent(this, PendingNotificationActivity.class);
        notificationIntent.putExtra("notificationId", notificationId);

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        NotificationChannel channel = new NotificationChannel("channelId", "pendingActivity", NotificationManager.IMPORTANCE_DEFAULT);
        assert notificationManager != null;
        notificationManager.createNotificationChannel(channel);

        Notification notification = new Notification.Builder(this, "channelId")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setTicker("pendingActivity")
                .setWhen(System.currentTimeMillis())
                .setContentTitle("Notification")
                .setContentText("Plez Reply")
                .setAutoCancel(true)
                .addAction(action)
                .build();

        notificationManager.notify(notificationId, notification);
    }
}