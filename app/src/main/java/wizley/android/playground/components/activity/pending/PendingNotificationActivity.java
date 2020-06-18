package wizley.android.playground.components.activity.pending;

import android.annotation.SuppressLint;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import wizley.android.playground.databinding.ActivityPendingBinding;

public class PendingNotificationActivity extends AppCompatActivity {
    private static final String TAG = "PendingNotificationActivity";

    @SuppressLint("LongLogTag")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.e(TAG, "onCreate");

        ActivityPendingBinding binding = ActivityPendingBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        Intent intent = getIntent();

        final int notificationId = intent.getIntExtra("notificationId", 0);
        binding.button.setText(String.valueOf(notificationId));

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.cancel(notificationId);
    }
}
