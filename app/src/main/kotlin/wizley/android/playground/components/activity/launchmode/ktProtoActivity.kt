package wizley.android.playground.components.activity.launchmode

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import wizley.android.playground.R

class ktProtoActivity : Activity(), View.OnClickListener {
    val TAG = "ktProtoActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.e(TAG, "onCreate")
    }

    override fun onStart() {
        super.onStart()

        Log.e(TAG, "onStart")
    }

    override fun onResume() {
        super.onResume()

        Log.e(TAG, "onResume")
    }

    override fun onPause() {
        super.onPause()

        Log.e(TAG, "onPause")
    }

    override fun onStop() {
        super.onStop()

        Log.e(TAG, "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()

        Log.e(TAG, "onDestroy")
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)

        Log.e(TAG, "onNewIntent")
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_standard -> {
                val standardIntent = Intent(this, ktStandardActivity::class.java)
                startActivity(standardIntent)
            }
            R.id.btn_singletop -> {
                val singletopIntent = Intent(this, ktSingleTopActivity::class.java)
                startActivity(singletopIntent)
            }
            R.id.btn_singletask -> {
                val singletaskIntent = Intent(this, ktSingleTaskActivity::class.java)
                startActivity(singletaskIntent)
            }
            R.id.btn_singleinstance -> {
                val singleinstanceIntent = Intent(this, ktSingleInstanceActivity::class.java)
                startActivity(singleinstanceIntent)
            }
        }
    }


}
