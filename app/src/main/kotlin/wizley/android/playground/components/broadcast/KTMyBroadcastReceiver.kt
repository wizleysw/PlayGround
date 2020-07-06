package wizley.android.playground.components.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.util.Log
import android.widget.Toast
import java.lang.StringBuilder

class KTMyBroadcastReceiver : BroadcastReceiver(){
    private val TAG = "KTMyBroadcastReceiver"

    override fun onReceive(context: Context?, intent: Intent?) {
        Log.e(TAG, "onReceive")
        val sb : StringBuilder = StringBuilder()
        sb.append("Action: ").append(intent!!.action).append("\n")
        sb.append("URI: ").append(intent.toUri(Intent.URI_INTENT_SCHEME).toString()).append("\n")

        val log : String = sb.toString()
        Log.e(TAG, log)

        Toast.makeText(context, log, Toast.LENGTH_LONG).show()

        // if need to execute in other thread use this method
        val pendingResult : PendingResult = goAsync()
        val asyncTask = Task(pendingResult, intent)
        asyncTask.execute()
    }

    private class Task(
            private val pendingResult: PendingResult,
            private val intent: Intent
    ) : AsyncTask<String, Int, String>(){
        private val TAG = "Task"

        override fun doInBackground(vararg params: String?): String? {
            Log.e(TAG, "doInBackground")

            Log.e(TAG, intent.extras?.get("data") as String)
            return null
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)

            // this should be called to be recycled
            pendingResult.finish()
        }

    }


}