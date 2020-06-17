package wizley.android.playground.viewmodel.chronometer

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Chronometer
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import org.jetbrains.anko.find
import wizley.android.playground.R

class ktViewModelChronometerActivity : AppCompatActivity() {
    private val TAG = "ktViewModelChronometerActivity"

    private var model : ktMyViewModel?= null
    private var chronometer : Chronometer?= null

    @SuppressLint("LongLogTag")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_viewmodel_chronometer)

        chronometer = findViewById(R.id.chronometer)

        model = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(ktMyViewModel::class.java)

        chronometer!!.base = model!!.getStartTime()
        chronometer!!.setOnChronometerTickListener {
            Chronometer.OnChronometerTickListener { _ -> model!!.setTimeTick(model!!.getTimeTick() + 1)
        }}

        val tickObserver = Observer<Long> {
            value -> Log.e(TAG, model!!.getTimeTick().toString())
        }

        model!!.getTick().observe(this, tickObserver)

        chronometer!!.start()
    }
}