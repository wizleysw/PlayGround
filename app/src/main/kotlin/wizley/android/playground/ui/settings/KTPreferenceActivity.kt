package wizley.android.playground.ui.settings

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import wizley.android.playground.R

class KTPreferenceActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preference)

        supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment, KTPreferenceFragment())
                .commit()

    }


}