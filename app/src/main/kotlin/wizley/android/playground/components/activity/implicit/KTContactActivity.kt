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

class KTContactActivity : Activity(), View.OnClickListener {
    private val TAG = "ktContactActivity"
    private val PICK_CONTACT_REQUEST = 100

    private var button : Button?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_implicit)

        button = findViewById<Button>(R.id.implicit_button)
        button!!.text = "Contact"
        button!!.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.implicit_button -> {
                Intent(Intent.ACTION_PICK, Uri.parse("content://contacts")).also {
                    pickContentIntent -> pickContentIntent.type = ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE
                    startActivityForResult(pickContentIntent, PICK_CONTACT_REQUEST)
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == PICK_CONTACT_REQUEST){
            if(resultCode == RESULT_OK){
                Log.e(TAG, "RESULT_OK")

                val projection: Array<String> = arrayOf(ContactsContract.CommonDataKinds.Phone.NUMBER)

                if (data != null) {
                    data.data?.also { contactUri ->
                        contentResolver.query(contactUri, projection, null, null, null)?.apply {
                            moveToFirst()

                            val column: Int = getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
                            val number: String? = getString(column)

                            Log.e(TAG, number);
                        }
                    }
                }
            } else if(resultCode == RESULT_CANCELED){
                Log.e(TAG, "RESULT_CANCELED")
            }
        }
    }
}