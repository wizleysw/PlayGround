package wizley.android.playground.components.activity.implicit

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import wizley.android.playground.R

class ktWebviewActivity : Activity(), View.OnClickListener {
    private val TAG = "ktWebviewActivity"
    private var button : Button?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_implicit)

        button = findViewById<Button>(R.id.implicit_button)
        button!!.text = "Webview"
        button!!.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.implicit_button -> {
                val webIntent : Intent = Uri.parse("https://bughunting.kr").let {
                    webpage -> Intent(Intent.ACTION_VIEW, webpage)
                }
                startActivity(webIntent)
            }
        }
    }
}