package wizley.android.playground.components.activity.fragment.resultlistener

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.FragmentActivity
import wizley.android.playground.R

class KTFragmentActivity : FragmentActivity(){
    private val TAG = "KTFragmentActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e(TAG, "onCreate")

        setContentView(R.layout.activity_fragment)

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        val fragmentA = KTFragmentA()
        val fragmentB = KTFragmentB()

        fragmentTransaction.add(R.id.fragmentA, fragmentA, "A")
        fragmentTransaction.add(R.id.fragmentB, fragmentB, "B")
        fragmentTransaction.commit()
    }

}