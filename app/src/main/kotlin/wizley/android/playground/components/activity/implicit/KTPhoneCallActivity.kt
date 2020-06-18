package wizley.android.playground.components.activity.implicit

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import wizley.android.playground.R

class KTPhoneCallActivity : Activity(), View.OnClickListener {
    private val TAG = "ktPhoneCallActivity"
    private var button : Button?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_implicit)

        button = findViewById<Button>(R.id.implicit_button)
        button!!.text = "PhoneCall"
        button!!.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.implicit_button -> {
               val callIntent: Intent = Uri.parse("tel:5551234").let {
                   number -> Intent(Intent.ACTION_DIAL, number)
               }
               startActivity(callIntent) 
            }
        }
    }
}