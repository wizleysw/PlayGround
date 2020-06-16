package wizley.android.playground.components.activity.implicit

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import wizley.android.playground.R

class ktMapCallActivity : Activity(), View.OnClickListener {
    private val TAG = "ktMapCallActivity"
    private var button : Button?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_implicit)

        button = findViewById<Button>(R.id.implicit_button)
        button!!.text = "MapCall"
        button!!.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.implicit_button -> {
                val mapIntent : Intent = Uri.parse("geo:0,0?q=1600+Amphitheatre+Parkway,+Mountain+View,+California").let {
                    location -> Intent(Intent.ACTION_VIEW, location)
                }
                startActivity(mapIntent)
            }
        }
    }
}