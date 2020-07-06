package wizley.android.playground.thread

import android.util.Log

class KTMyRunnable : Runnable {
    private val TAG = "KTMyRunnable"

    override fun run() {
        Log.e(TAG, "run")

        android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_BACKGROUND)
    }

}