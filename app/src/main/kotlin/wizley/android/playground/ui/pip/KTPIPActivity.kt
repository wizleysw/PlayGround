package wizley.android.playground.ui.pip

import android.content.pm.PackageManager
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import wizley.android.playground.R

class KTPIPActivity : AppCompatActivity(), View.OnClickListener{
    private val TAG = "KTPIPActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pip)

        val btn : Button = findViewById(R.id.button)
        btn.setOnClickListener(this)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onClick(v: View?) {
        if(v!!.id == R.id.button){
            Log.e(TAG, "enterPictureInPictureMode")

            val pm : PackageManager = this.packageManager
            if(pm.hasSystemFeature(PackageManager.FEATURE_PICTURE_IN_PICTURE)){
                this.enterPictureInPictureMode()
            }
        }
    }

    override fun onPictureInPictureModeChanged(isInPictureInPictureMode: Boolean, newConfig: Configuration?) {
        if(isInPictureInPictureMode){
            Log.e(TAG, "isInPictureInPictureMode")
        } else {
            Log.e(TAG, "Not isInPictureInPictureMode")
        }
        super.onPictureInPictureModeChanged(isInPictureInPictureMode, newConfig)
    }

}