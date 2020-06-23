package wizley.android.playground.ui.dialog

import android.os.Bundle
import wizley.android.playground.components.activity.fragment.lifecycle.FragmentActivity

class KTCustomDialogActivity : FragmentActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val fm = supportFragmentManager
        val dialogFragment = KTCustomDialogFragment()
        dialogFragment.show(fm, "dialog fragment")
    }

}