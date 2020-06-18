package wizley.android.playground.components.activity.implicit

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.provider.Settings;

class KTSettingsActivity : AppCompatActivity(){
    private val TAG = "KTSettingsActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        openWifiSettings()
    }

    private fun openWifiSettings(){
        Log.e(TAG, "openWifiSettings")
        val intent = Intent(Settings.ACTION_WIFI_IP_SETTINGS)
        if(intent.resolveActivity(packageManager) != null){
            startActivity(intent)
        }
    }
}