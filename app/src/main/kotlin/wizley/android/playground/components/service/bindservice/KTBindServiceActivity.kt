package wizley.android.playground.components.service.bindservice

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import wizley.android.playground.R

class KTBindServiceActivity : AppCompatActivity(), View.OnClickListener{
    private lateinit var service : KTMyBinderService
    private var bound : Boolean = false
    private lateinit var button : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_button)

        button = findViewById(R.id.button)
        button.setOnClickListener(this)
    }

    override fun onStart() {
        super.onStart()

        val intent : Intent = Intent(this, KTMyBinderService::class.java)
        bindService(intent, connection, Context.BIND_AUTO_CREATE)
    }

    override fun onStop() {
        super.onStop()
    }

    private val connection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder = service as KTMyBinderService.KTLocalBinder
            this@KTBindServiceActivity.service = binder.getService()
            bound = true
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            bound = false
        }
    }

    override fun onClick(v: View?) {
        if(v!!.id == R.id.button && bound){
            val num = service.randomNumber
            Toast.makeText(this, "number : $num", Toast.LENGTH_LONG).show()
        }
    }

}