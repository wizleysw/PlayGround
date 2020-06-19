package wizley.android.playground.components.activity.fragment.resultlistener

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentResultListener
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener

class KTFragmentA : Fragment(){
    private val TAG = "KTFragmentA"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e(TAG, "onCreate")

        parentFragmentManager.setFragmentResultListener("requestKey", this,
                FragmentResultListener{ _, result ->
                    Log.e(TAG, "FragmentResultListener listened")
                    val res = result.getString("bundleKey")
                    Log.e(TAG, res)
        })
    }
}