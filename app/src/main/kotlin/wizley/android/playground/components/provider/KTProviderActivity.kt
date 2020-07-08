package wizley.android.playground.components.provider

import android.annotation.SuppressLint
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import wizley.android.playground.R

class KTProviderActivity : AppCompatActivity(), View.OnClickListener, View.OnLongClickListener{
    private val TAG = "KTProviderActivity"
    private lateinit var button: Button

    private val PROVIDER_URI = "content://wizley.android.playground.components.provider.KTmyProvider"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_button)

        button = findViewById(R.id.button)
        button.setOnClickListener(this)
        button.setOnLongClickListener(this)
    }

    override fun onClick(v: View?) {
        if(v!!.id == R.id.button){
            val newValue = ContentValues()

            newValue.put("number", "12345");
            newValue.put("name", "m_name");
            newValue.put("department", "computer");
            newValue.put("age", "1");
            newValue.put("grade", 4);

            contentResolver.insert(Uri.parse(PROVIDER_URI), newValue)
            button.text = "insertion OK"
        }
    }

    @SuppressLint("Recycle")
    override fun onLongClick(v: View?): Boolean {
        if(v!!.id == R.id.button){
            val columns = arrayOf("_id", "number", "name", "department", "age", "grade")

            val c : Cursor = contentResolver.query(
                    Uri.parse(PROVIDER_URI),
                    columns,
                    null, null, null, null)!!

            while(c.moveToNext()){
                val name = c.getString(2)
                Log.e(TAG, name)

                button.text = name
                Thread.sleep(2000)
            }

            button.text = "query OK"
            c.close()
        }

        return true
    }

}