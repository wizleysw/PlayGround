package wizley.android.playground.components.activity.implicit

import android.app.Activity
import android.content.Intent
import android.content.pm.ResolveInfo
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.view.View
import android.widget.Button
import wizley.android.playground.R

class KTSendTextActivity : Activity(), View.OnClickListener {
    private val TAG = "ktSendTextActivity"
    private val SEND_TEXT = 100

    private var button : Button?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_implicit)

        button = findViewById<Button>(R.id.implicit_button)
        button!!.text = "Send Text"
        button!!.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.implicit_button -> {
                val newIntent = Intent(Intent.ACTION_SEND)
                newIntent.setType("text/plain")
                newIntent.putExtra(Intent.EXTRA_TEXT, "hello Wizley~")

                val activities : List<ResolveInfo> = packageManager.queryIntentActivities(newIntent, 0)
                val isIntentSafe : Boolean = activities.isNotEmpty()

                if(isIntentSafe){
                    val title = resources.getString(R.string.chooser_title)
                    val chooser = Intent.createChooser(newIntent, title)

                    if(newIntent.resolveActivity(packageManager) != null){
                        startActivityForResult(chooser, SEND_TEXT)
                    }
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == SEND_TEXT){
            if(resultCode == RESULT_OK){
                button!!.text = "Send Text OK"
            }
        }
    }

}