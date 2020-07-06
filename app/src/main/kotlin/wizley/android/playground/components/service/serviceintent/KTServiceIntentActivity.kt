package wizley.android.playground.components.service.serviceintent

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class KTServiceIntentActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = Intent(this, KTMyIntentService::class.java)
        startService(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

}