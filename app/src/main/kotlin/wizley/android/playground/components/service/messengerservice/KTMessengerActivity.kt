package wizley.android.playground.components.service.messengerservice

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.*
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import wizley.android.playground.R

class KTMessengerActivity : AppCompatActivity(), View.OnClickListener {
    private var messenger : Messenger ?= null
    private var bound : Boolean = false
    private lateinit var button : Button

    private val connection = object : ServiceConnection {

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            messenger = Messenger(service)
            bound = true
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            messenger = null
            bound = false
        }
    }

    fun sayHello(){
        if(!bound) return

        val msg: Message = Message.obtain(null, MSG_SAY_HELLO, 0, 0)

        try {
            messenger?.send(msg)
        } catch (e : RemoteException){
            e.printStackTrace()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_button)

        button = findViewById(R.id.button)
        button.setOnClickListener(this)
    }

    override fun onStart() {
        super.onStart()

        bindService(Intent(this, KTMessengerService::class.java), connection, Context.BIND_AUTO_CREATE)
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onStop() {
        super.onStop()

        if(bound){
            unbindService(connection)
            bound = false
        }
    }

    override fun onClick(v: View?) {
        if(v!!.id == R.id.button && bound){
            sayHello()
        }
    }

}