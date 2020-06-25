package wizley.android.playground.storage

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class KTSharedPrefActivity : AppCompatActivity(){
    private val TAG = "KTSharedPrefActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        writeOK()
        readOK()
    }

    private fun writeOK(){
        val sharedPreference = this.getSharedPreferences("customPref", Context.MODE_PRIVATE)
        val editor : SharedPreferences.Editor = sharedPreference.edit()
        editor.putBoolean("is_ok", true)
        editor.apply()
    }

    private fun readOK() : Boolean{
        val sharedPreference = this.getSharedPreferences("customPref", Context.MODE_PRIVATE)
        val is_ok = sharedPreference.getBoolean("is_ok", false)
        Log.e(TAG, is_ok.toString())

        return is_ok
    }
}