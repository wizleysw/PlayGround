package wizley.android.playground.components.service.startservice

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class KTServiceActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent : Intent = Intent(this, KTMyService::class.java)
        startService(intent)
    }

    override fun onDestroy() {
        super.onDestroy()

        val intent : Intent = Intent(this, KTMyService::class.java)
        stopService(intent)
    }

}