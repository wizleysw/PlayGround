package wizley.android.playground.notification.reply;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.RemoteInput;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationManagerCompat;

import wizley.android.playground.R;

public class ReplyNotiReceiver extends BroadcastReceiver {
    private static final String TAG = "ReplyNotiReceiver";

    private static final String KEY_TEXT_REPLY = "key_text_reply";

    @RequiresApi(api = Build.VERSION_CODES.KITKAT_WATCH)
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e(TAG, "onReceive");
        getMessage(context, intent);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT_WATCH)
    private CharSequence getMessage(Context context, Intent intent){
        Log.e(TAG, "getMessage");
        Bundle remoteInput = RemoteInput.getResultsFromIntent(intent);
        if(remoteInput != null){
            CharSequence res = remoteInput.getCharSequence(KEY_TEXT_REPLY);
            Log.e(TAG, res.toString());

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                Notification repliedNotification = new Notification.Builder(context, "channelId")
                        .setSmallIcon(R.drawable.ic_launcher_foreground)
                        .setContentText("OK")
                        .build();

                final int notificationId = 100;
                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
                notificationManager.notify(notificationId, repliedNotification);

            }

            return res;
        }
        return null;
    }

}
