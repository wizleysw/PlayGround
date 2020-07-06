package wizley.android.playground.components.broadcast

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class KTBroadcastActivity : AppCompatActivity(){
    private lateinit var receiver: KTMyBroadcastReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        receiver = KTMyBroadcastReceiver()
        val filter = IntentFilter()
        filter.addAction("wizley.android.playground.ktbroadcast")
        registerReceiver(receiver, filter)
    }

    override fun onStart() {
        super.onStart()

        val intent = Intent()
        intent.action = "wizley.android.playground.ktbroadcast"
        intent.putExtra("data", "this is ktbroadcast value")
        sendBroadcast(intent)
    }

}