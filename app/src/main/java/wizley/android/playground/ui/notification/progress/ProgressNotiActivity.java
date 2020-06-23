package wizley.android.playground.ui.notification.progress;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import wizley.android.playground.R;

public class ProgressNotiActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "ProgressNotiActivity";

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
        final String CHANNEL_ID = "channel_id";

        final NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

        final NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "ProgressNoti", NotificationManager.IMPORTANCE_DEFAULT);
        assert notificationManager != null;
        notificationManager.createNotificationChannel(channel);

        final NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID);
        builder.setContentTitle("New Download")
                .setContentText("Downloading...")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                final int PROGRESS_MAX = 100;
                int PROGRESS_CURRENT = 0;

                while(PROGRESS_CURRENT <= 100){
                    builder.setProgress(PROGRESS_MAX, PROGRESS_CURRENT, false);
                    notificationManager.notify(notificationId, builder.build());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    PROGRESS_CURRENT += 10;
                }

                builder.setContentText("Download Complete");
                builder.setProgress(PROGRESS_MAX, PROGRESS_CURRENT, false);
                notificationManager.notify(notificationId, builder.build());
            }
        });

        thread.start();
    }
}