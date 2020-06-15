package wizley.android.playground.components.activity.activitymove

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import wizley.android.playground.R

class ktNavigateFromActivity : Activity(), View.OnClickListener {
    private val NEW_INTENT_REQUEST = 200
    private val NEW_INTENT_RESPONSE = 300
    private val TAG = "ktNavigateFromActivity"
    private var start_activity: Button? = null
    private var start_activity_for_result: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_navigatefrom)
        start_activity = findViewById<Button>(R.id.start_btn)
        start_activity_for_result = findViewById<Button>(R.id.start_for_result_btn)

        start_activity!!.setOnClickListener(this)
        start_activity_for_result!!.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.start_btn -> {
                Log.e(TAG, "startActivity")
                val start_intent = Intent(this, ktNavigateToActivity::class.java)
                start_intent.putExtra("status", 1)
                startActivity(start_intent)
            }
            R.id.start_for_result_btn -> {
                Log.e(TAG, "startActivityForResult")
                val start_for_result_intent = Intent(this, ktNavigateToActivity::class.java)
                start_for_result_intent.putExtra("status", 2)
                startActivityForResult(start_for_result_intent, NEW_INTENT_REQUEST)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when(requestCode){
            NEW_INTENT_REQUEST -> {
                if(resultCode == NEW_INTENT_RESPONSE){
                    Log.e(TAG, "Intent was successful")
                } else {
                    Log.e(TAG, "intent was not successful")
                }
            }
        }
    }
}