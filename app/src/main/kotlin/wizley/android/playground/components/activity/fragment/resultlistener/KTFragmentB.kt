package wizley.android.playground.components.activity.fragment.resultlistener

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment

class KTFragmentB : Fragment(){
    private val TAG = "KTFragmentB"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e(TAG, "onCreate")

        val result = Bundle()
        result.putString("bundleKey", "This is FragmentResultListener");

        parentFragmentManager.setFragmentResult("requestKey", result)
    }
}