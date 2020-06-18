package wizley.android.playground.viewmodel.counter

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.floatingactionbutton.FloatingActionButton
import wizley.android.playground.R

class KTViewModelCounterActivity : AppCompatActivity(), View.OnClickListener{
    private val TAG = "ktViewModelCounterActivity"

    private var fabPlus : FloatingActionButton? = null
    private var fabMinus : FloatingActionButton? = null
    private var textView : TextView?= null
    private var model : KTCounterViewModel? = null

    @SuppressLint("LongLogTag")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_viewmodel_counter)

        model = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(KTCounterViewModel::class.java)

        fabPlus = findViewById(R.id.fabPlus)
        fabMinus = findViewById(R.id.fabMinus)
        textView = findViewById(R.id.textView)

        textView!!.text = model!!.getValue().toString()

        fabPlus!!.setOnClickListener(this)
        fabMinus!!.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.fabPlus -> {
                model!!.increase()
                textView!!.text = model!!.getValue().toString()
            }
            R.id.fabMinus -> {
                model!!.decrease()
                textView!!.text = model!!.getValue().toString()
            }
        }
    }
}