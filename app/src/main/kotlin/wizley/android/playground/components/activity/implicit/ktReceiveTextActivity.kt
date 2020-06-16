package wizley.android.playground.components.activity.implicit

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.view.View
import android.widget.Button
import wizley.android.playground.R

class ktReceiveTextActivity : Activity(), View.OnClickListener {
    private val TAG = "ktReceiveTextActivity"

    private var button : Button?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_implicit)

        button = findViewById<Button>(R.id.implicit_button)
        button!!.setOnClickListener(this)

        if(intent.action == Intent.ACTION_SEND && intent.type == "text/plain") {
            handleSendText(intent)
        }
    }

    fun handleSendText(intent: Intent) {
        val sharedText = intent.getStringExtra(Intent.EXTRA_TEXT)
        if(sharedText != null) {
            button!!.text = sharedText
        }
        setResult(RESULT_OK)
    }

    override fun onClick(v: View?) {
        if(v!!.id == R.id.implicit_button) {
            this.finish();
        }
    }
}