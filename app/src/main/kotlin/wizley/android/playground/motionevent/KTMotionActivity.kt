package wizley.android.playground.motionevent

import android.os.Bundle
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GestureDetectorCompat
import androidx.core.view.MotionEventCompat

class KTMotionActivity : AppCompatActivity(),
        GestureDetector.OnGestureListener,
        GestureDetector.OnDoubleTapListener {

    private val TAG = "KTMotionActivity"

    private lateinit var detector : GestureDetectorCompat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        detector = GestureDetectorCompat(this, MyGestureListener())

    }

    private class MyGestureListener : GestureDetector.SimpleOnGestureListener(){

        override fun onDown(e: MotionEvent?): Boolean {
            return super.onDown(e)
        }

        override fun onFling(e1: MotionEvent?, e2: MotionEvent?, velocityX: Float, velocityY: Float): Boolean {
            return super.onFling(e1, e2, velocityX, velocityY)
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {

            val action: Int = event.getActionMasked()

            return when (action) {
                MotionEvent.ACTION_DOWN -> {
                    Log.e(TAG, "Action was DOWN")
                    true
                }
                MotionEvent.ACTION_MOVE -> {
                    Log.e(TAG, "Action was MOVE")
                    true
                }
                MotionEvent.ACTION_UP -> {
                    Log.d(TAG, "Action was UP")
                    true
                }
                MotionEvent.ACTION_CANCEL -> {
                    Log.e(TAG, "Action was CANCEL")
                    true
                }
                MotionEvent.ACTION_OUTSIDE -> {
                    Log.e(TAG, "Movement occurred outside bounds of current screen element")
                    true
                }
                else -> super.onTouchEvent(event)
            }
        }

    override fun onShowPress(e: MotionEvent?) {
        TODO("Not yet implemented")
    }

    override fun onSingleTapUp(e: MotionEvent?): Boolean {
        TODO("Not yet implemented")
    }

    override fun onDown(e: MotionEvent?): Boolean {
        TODO("Not yet implemented")
    }

    override fun onFling(e1: MotionEvent?, e2: MotionEvent?, velocityX: Float, velocityY: Float): Boolean {
        TODO("Not yet implemented")
    }

    override fun onScroll(e1: MotionEvent?, e2: MotionEvent?, distanceX: Float, distanceY: Float): Boolean {
        TODO("Not yet implemented")
    }

    override fun onLongPress(e: MotionEvent?) {
        TODO("Not yet implemented")
    }

    override fun onDoubleTap(e: MotionEvent?): Boolean {
        TODO("Not yet implemented")
    }

    override fun onDoubleTapEvent(e: MotionEvent?): Boolean {
        TODO("Not yet implemented")
    }

    override fun onSingleTapConfirmed(e: MotionEvent?): Boolean {
        TODO("Not yet implemented")
    }


}