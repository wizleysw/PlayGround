package wizley.android.playground.ui.systemui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import wizley.android.playground.R

class KTNavigationBarActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.decorView.apply {
            systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN
        }

        setContentView(R.layout.activity_button)


    }

}