package wizley.android.playground.components.activity.fragment.lifecycle

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.fragment.app.Fragment
import wizley.android.playground.R
import java.lang.ClassCastException

class KTSimpleFragment : Fragment(){
    private val TAG = "KTSimpleFragment"
    private var vtag : String ?= ""
    private var init = -1
    private var imageView : ImageView?= null

    private var callback: FragmentCallback?= null

    interface FragmentCallback{
        fun onImageChanged()
    }

    override fun onInflate(context: Context, attrs: AttributeSet, savedInstanceState: Bundle?) {
        super.onInflate(context, attrs, savedInstanceState)
        Log.e(TAG, "onInfate")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.e(TAG, "onAttach")

        callback = context as? FragmentCallback
        if(callback == null){
            throw ClassCastException("Error")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e(TAG, "onCreate")

        vtag = arguments?.getString("tag").toString()
        Log.e(TAG, vtag);

        init = 1
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        Log.e(TAG, "onCreateView")
        return inflater.inflate(R.layout.fragment_simple, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.e(TAG, "onViewCreated")

        imageView = view.findViewById(R.id.imageView)

        if(vtag!=null && vtag == "A"){
            imageView!!.setImageDrawable(resources.getDrawable(R.drawable.ic_launcher_foreground))
        } else if(vtag!=null && vtag == "B"){
            imageView!!.setImageDrawable(resources.getDrawable(R.drawable.ic_launcher_background))
        }
        callback!!.onImageChanged()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.e(TAG, "onActivityCreated")
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        Log.e(TAG, "onViewStateRestored")
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

    override fun onDestroyView() {
        super.onDestroyView()
        Log.e(TAG, "onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e(TAG, "onDestroy")
    }

    override fun onDetach() {
        super.onDetach()
        Log.e(TAG, "onDetach")
    }

}