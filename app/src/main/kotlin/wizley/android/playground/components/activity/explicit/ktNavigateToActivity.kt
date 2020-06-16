package wizley.android.playground.components.activity.explicit

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import wizley.android.playground.R

class ktNavigateToActivity : Activity(), View.OnClickListener {
    private val NEW_INTENT_RESPONSE = 300
    private val TAG = "ktNavigateToActivity"
    private var ok : Button? = null
    private var status : Int? = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_navigateto)

        status = intent.getIntExtra("status", -1)

        if(status == -1){
            finish()
        }

        ok = findViewById<Button>(R.id.ok)
        ok!!.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.ok -> {
                val intent = Intent()
                if(status == 2){
                    setResult(NEW_INTENT_RESPONSE)
                }
                Log.e(TAG, "RETURN TO ktNavigateFromActivity")
                finish();
            }
        }
    }
}