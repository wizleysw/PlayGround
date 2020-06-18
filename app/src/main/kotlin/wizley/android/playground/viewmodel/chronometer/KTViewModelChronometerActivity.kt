package wizley.android.playground.viewmodel.chronometer

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Chronometer
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import wizley.android.playground.R

class KTViewModelChronometerActivity : AppCompatActivity(), Chronometer.OnChronometerTickListener{
    private val TAG = "ktViewModelChronometerActivity"

    private var model : KTMyViewModel?= null
    private var chronometer : Chronometer?= null

    @SuppressLint("LongLogTag")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_viewmodel_chronometer)

        chronometer = findViewById(R.id.chronometer)

        model = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(KTMyViewModel::class.java)

        chronometer!!.base = model!!.getStartTime()
        chronometer!!.setOnChronometerTickListener(this)

        val tickObserver = Observer<Long> { _ -> Log.e(TAG, model!!.getTimeTick().toString())
        }

        model!!.getTick().observe(this, tickObserver)

        chronometer!!.start()
    }

    override fun onChronometerTick(chronometer: Chronometer?) {
        model!!.setTimeTick(model!!.getTimeTick() + 1)
    }

}