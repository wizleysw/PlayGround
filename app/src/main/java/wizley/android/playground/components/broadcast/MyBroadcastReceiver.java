package wizley.android.playground.components.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class MyBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = "MyBroadcastReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e(TAG, "onReceive");
        StringBuilder sb = new StringBuilder();
        sb.append("Action: ").append(intent.getAction()).append("\n");
        sb.append("URI: ").append(intent.toUri(Intent.URI_INTENT_SCHEME)).append("\n");

        String log = sb.toString();
        Log.e(TAG, log);

        Toast.makeText(context, log, Toast.LENGTH_LONG).show();

        // if need to execute in other thread use this method
        final PendingResult pendingResult = goAsync();
        Task asyncTask = new Task(pendingResult, intent);
        asyncTask.execute();
    }

    private static class Task extends AsyncTask<String, Integer, String> {
        private final PendingResult pendingResult;
        private final Intent intent;

        private Task(PendingResult pendingResult, Intent intent){
            this.pendingResult = pendingResult;
            this.intent = intent;
        }

        @Override
        protected String doInBackground(String... strings) {
            Log.e(TAG, "doInBackground");

            Log.e(TAG, (String) intent.getExtras().get("data"));
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            // this should be called to be recycled
            pendingResult.finish();
        }
    }

}
