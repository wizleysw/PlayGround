package wizley.android.playground.components.activity.fragment.transaction

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import wizley.android.playground.R
import wizley.android.playground.components.activity.fragment.lifecycle.KTSimpleFragment

class KTReplaceFragmentActivity : FragmentActivity(), KTSimpleFragment.FragmentCallback, View.OnClickListener{
    private val TAG = "KTReplaceFragmentActivity"
    private var button : Button ?= null

    private var fragmentA : KTSimpleFragment ?= null
    private var fragmentB : KTSimpleFragment ?= null

    @SuppressLint("LongLogTag")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e(TAG, "onCreate")

        setContentView(R.layout.activity_replace_fragment)

        if(savedInstanceState != null){
            return
        }

        button = findViewById(R.id.button)

        val fragmentManager : FragmentManager = supportFragmentManager
        val fragmentTransaction : FragmentTransaction = fragmentManager.beginTransaction()

        fragmentA = KTSimpleFragment()
        val bundle : Bundle = Bundle()
        bundle.putString("tag", "A")
        fragmentA!!.arguments = bundle

        fragmentB = KTSimpleFragment()
        val bundle2 : Bundle = Bundle()
        bundle2.putString("tag", "B")
        fragmentB!!.arguments = bundle2

        fragmentTransaction.add(R.id.fragmentA, fragmentA!!)
        fragmentTransaction.addToBackStack("A")

        fragmentTransaction.commit()

        button!!.setOnClickListener(this)
    }

    @SuppressLint("LongLogTag")
    override fun onAttachFragment(fragment: Fragment) {
        super.onAttachFragment(fragment)
        Log.e(TAG, "onAttachFragment")
    }

    @SuppressLint("LongLogTag")
    override fun onStart() {
        super.onStart()
        Log.e(TAG, "onStart")
    }

    @SuppressLint("LongLogTag")
    override fun onResume() {
        super.onResume()
        Log.e(TAG, "onResume")
    }

    @SuppressLint("LongLogTag")
    override fun onPause() {
        super.onPause()
        Log.e(TAG, "onPause")
    }

    @SuppressLint("LongLogTag")
    override fun onStop() {
        super.onStop()
        Log.e(TAG, "onStop")
    }

    @SuppressLint("LongLogTag")
    override fun onDestroy() {
        super.onDestroy()
        Log.e(TAG, "onDestroy")
    }

    @SuppressLint("LongLogTag")
    override fun onImageChanged() {
        Log.e(TAG, "onImageChanged")
    }

    override fun onClick(v: View?) {
        if(v!!.id == R.id.button){
            val fragmentManager = supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()

            var idx = supportFragmentManager.backStackEntryCount-1
            val vtag = supportFragmentManager.getBackStackEntryAt(idx).name
            if(vtag == "A"){
                fragmentTransaction.replace(R.id.fragmentA, fragmentB!!, "B")
                fragmentTransaction.addToBackStack("B")
            } else if(vtag == "B"){
                fragmentTransaction.replace(R.id.fragmentA, fragmentA!!, "A")
                fragmentTransaction.addToBackStack("A")
            }
            fragmentTransaction.commit()
        }
    }

}