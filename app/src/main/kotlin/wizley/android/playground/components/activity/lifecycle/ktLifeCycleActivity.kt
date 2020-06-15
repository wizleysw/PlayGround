package wizley.android.playground.components.activity.lifecycle

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import wizley.android.playground.R
import androidx.lifecycle.LifecycleRegistry
import wizley.android.playground.components.activity.lifecycle.ktCustomLifeCycleObserver

class ktLifeCycleActivity : Activity(), LifecycleOwner{
    private val VALUE = ""

    private var v: String? = null
    private var btn: Button? = null
    private var lifecycleRegistry: LifecycleRegistry? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        v = savedInstanceState?.getString(VALUE);

        lifecycleRegistry = LifecycleRegistry(this)
        lifecycleRegistry!!.addObserver(ktCustomLifeCycleObserver())
        lifecycleRegistry!!.currentState = Lifecycle.State.CREATED

        setContentView(R.layout.activity_lifecycle);

        btn = findViewById<Button>(R.id.button)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        btn!!.text = savedInstanceState?.getString(VALUE)
        super.onRestoreInstanceState(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString(VALUE, v);
        super.onSaveInstanceState(outState)
    }

    override fun onStart() {
        super.onStart()

        lifecycleRegistry!!.currentState = Lifecycle.State.STARTED
    }

    override fun onResume() {
        super.onResume()

        lifecycleRegistry!!.currentState = Lifecycle.State.RESUMED
    }

    override fun onPause() {
        super.onPause()

        lifecycleRegistry!!.currentState  = Lifecycle.State.STARTED
    }

    override fun onStop() {
        super.onStop()

        lifecycleRegistry!!.currentState  = Lifecycle.State.CREATED
    }

    override fun onDestroy() {
        super.onDestroy()

        lifecycleRegistry!!.currentState = Lifecycle.State.DESTROYED
    }

    override fun onRestart() {
        super.onRestart()

        lifecycleRegistry!!.currentState  = Lifecycle.State.CREATED
    }

    override fun getLifecycle(): Lifecycle {
        return this.lifecycleRegistry!!
    }
}