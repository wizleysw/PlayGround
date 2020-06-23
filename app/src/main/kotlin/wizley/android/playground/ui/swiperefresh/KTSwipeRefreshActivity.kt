package wizley.android.playground.ui.swiperefresh

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import wizley.android.playground.R

// implementation "androidx.swiperefreshlayout:swiperefreshlayout:1.0.0"
class KTSwipeRefreshActivity : AppCompatActivity(){
    private val TAG = "KTSwipeRefreshActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_swiperefresh)

        val swipeRefreshLayout : SwipeRefreshLayout = findViewById(R.id.swiperefresh)
        swipeRefreshLayout.setOnRefreshListener {
            Log.e(TAG, "onRefresh")
            swipeRefreshLayout.isRefreshing = false
        }
    }

}