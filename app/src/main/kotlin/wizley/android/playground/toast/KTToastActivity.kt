package wizley.android.playground.toast

import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import wizley.android.playground.R

class KTToastActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val inflater = layoutInflater
        val layout : ViewGroup = inflater.inflate(R.layout.layout_toast, findViewById(R.id.custom_toast_container)) as ViewGroup
        val text : TextView = layout.findViewById(R.id.text)
        text.text = "Hello World!"

        with(Toast(applicationContext)){
            setGravity(Gravity.CENTER_VERTICAL, 0, 0)
            duration = Toast.LENGTH_LONG
            view = layout
            show()
        }
    }

}