package wizley.android.playground.components.activity.implicit

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import wizley.android.playground.R

class KTEmailActivity : Activity(), View.OnClickListener {
    private val TAG = "ktEmailActivity"
    private var button : Button?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_implicit)

        button = findViewById<Button>(R.id.implicit_button)
        button!!.text = "EmailSend"
        button!!.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.implicit_button -> {
                val emailIntent = Intent(Intent.ACTION_SEND).apply {
                    type = "text/plain"
                    putExtra(Intent.EXTRA_EMAIL, arrayOf("wizley@kakao.com"))
                    putExtra(Intent.EXTRA_SUBJECT, "Email Send")
                    putExtra(Intent.EXTRA_TEXT, "Hello Wizley!")
                    putExtra(Intent.EXTRA_STREAM, Uri.parse("content://path/to/email/attachment"))
                }
                startActivity(emailIntent)
            }
        }
    }
}