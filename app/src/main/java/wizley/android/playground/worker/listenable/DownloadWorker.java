package wizley.android.playground.worker.listenable;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.work.Data;
import androidx.work.ForegroundInfo;
import androidx.work.WorkManager;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import wizley.android.playground.R;

public class DownloadWorker extends Worker {
    private static final String KEY_INPUT_URL = "KEY_INPUT_URL";
    private static final String KEY_OUTPUT_FILE_NAME = "KEY_OUTPUT_FILE_NAME";

    private NotificationManager notificationManager;

    public DownloadWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
    }

    @NonNull
    @Override
    public Result doWork() {
        Data inputData = getInputData();
        String inputUrl = inputData.getString(KEY_INPUT_URL);
        String outputFile = inputData.getString(KEY_OUTPUT_FILE_NAME);

        String progress = "Starting Download";
        setForegroundAsync(createForegroundInfo(progress));
        download(inputUrl, outputFile);
        return Result.success();
    }

    private void download(String inputUrl, String outputFile) {
        // Download a file and update byte read
    }

    private ForegroundInfo createForegroundInfo(String progress) {
        Context context = getApplicationContext();
        String id = "notificationChannel";
        String title = "notification";
        String cancel = "cancel";

        PendingIntent intent = WorkManager.getInstance(context).createCancelPendingIntent(getId());

        Notification notification = new NotificationCompat.Builder(context, id)
                .setContentTitle(title)
                .setTicker(title)
                .setSmallIcon(R.drawable.ic_baseline_add_24)
                .setOngoing(true)
                .addAction(android.R.drawable.ic_delete, cancel, intent) // Cancel Action
                .build();

        return new ForegroundInfo(1, notification);
    }
}
