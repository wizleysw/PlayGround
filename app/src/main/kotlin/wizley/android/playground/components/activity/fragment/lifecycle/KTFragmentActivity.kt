package wizley.android.playground.components.activity.fragment.lifecycle

import android.os.Bundle
import android.util.Log
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import wizley.android.playground.R

class KTFragmentActivity : FragmentActivity(), KTSimpleFragment.FragmentCallback{
    private val TAG = "KTFragmentActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e(TAG, "onCreate")

        setContentView(R.layout.activity_fragment)

        if(savedInstanceState != null){
            return
        }

        val frameLayoutA : FrameLayout = findViewById(R.id.fragmentA)
        val frameLayoutB : FrameLayout = findViewById(R.id.fragmentB)

        val fragmentManager : FragmentManager = supportFragmentManager
        val fragmentTransaction : FragmentTransaction = fragmentManager.beginTransaction()

        val fragmentA : KTSimpleFragment = KTSimpleFragment()
        val fragmentB : KTSimpleFragment = KTSimpleFragment()

        fragmentTransaction.add(R.id.fragmentA, fragmentA)
        fragmentTransaction.add(R.id.fragmentB, fragmentB)

        fragmentTransaction.commit()
    }

    override fun onAttachFragment(fragment: Fragment) {
        super.onAttachFragment(fragment)
        Log.e(TAG, "onAttachFragment")
    }

    override fun onStart() {
        super.onStart()
        Log.e(TAG, "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.e(TAG, "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.e(TAG, "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.e(TAG, "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e(TAG, "onDestroy")
    }

    override fun onImageChanged() {
        Log.e(TAG, "onImageChanged")
    }

}