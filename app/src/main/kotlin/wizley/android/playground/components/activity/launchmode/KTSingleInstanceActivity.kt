package wizley.android.playground.components.activity.launchmode

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import wizley.android.playground.R

class KTSingleInstanceActivity : Activity(), View.OnClickListener {
    private val TAG = "ktSingleInstanceActivity"

    @SuppressLint("LongLogTag")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.e(TAG, "onCreate")
    }

    @SuppressLint("LongLogTag")
    override fun onStart() {
        super.onStart()

        Log.e(TAG, "onStart")
    }

    @SuppressLint("LongLogTag")
    override fun onResume() {
        super.onResume()

        Log.e(TAG, "onResume")
    }

    @SuppressLint("LongLogTag")
    override fun onPause() {
        super.onPause()

        Log.e(TAG, "onPause")
    }

    @SuppressLint("LongLogTag")
    override fun onStop() {
        super.onStop()

        Log.e(TAG, "onStop")
    }

    @SuppressLint("LongLogTag")
    override fun onDestroy() {
        super.onDestroy()

        Log.e(TAG, "onDestroy")
    }

    @SuppressLint("LongLogTag")
    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)

        Log.e(TAG, "onNewIntent")
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_standard -> {
                val standardIntent = Intent(this, KTStandardActivity::class.java)
                startActivity(standardIntent)
            }
            R.id.btn_singletop -> {
                val singletopIntent = Intent(this, KTSingleTopActivity::class.java)
                startActivity(singletopIntent)
            }
            R.id.btn_singletask -> {
                val singletaskIntent = Intent(this, KTSingleTaskActivity::class.java)
                startActivity(singletaskIntent)
            }
            R.id.btn_singleinstance -> {
                val singleinstanceIntent = Intent(this, KTSingleInstanceActivity::class.java)
                startActivity(singleinstanceIntent)
            }
        }
    }


}
