package wizley.android.playground.thread;

import android.os.Process;
import android.util.Log;

public class MyRunnable implements Runnable {
    private static final String TAG = "MyRunnable";

    @Override
    public void run() {
        Log.e(TAG, "run");

        android.os.Process.setThreadPriority(Process.THREAD_PRIORITY_BACKGROUND);
    }
}
