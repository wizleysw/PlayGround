package wizley.android.playground.ui.snackbar

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import wizley.android.playground.R

class KTSnackBarActivity : AppCompatActivity(){
    private val TAG = "KTSnackBarActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_snackbar)

        val layout : View = layoutInflater.inflate(R.layout.activity_snackbar, findViewById(R.id.coordinatorLayout))

        val snackbar = Snackbar.make(layout, "SnackBar", Snackbar.LENGTH_LONG)
        snackbar.setAction("UNDO", UndoListener())
        snackbar.show();

        // Snackbar.make(findViewById(R.id.coordinatorLayout), "SnackBar", Snackbar.LENGTH_LONG).show()

    }

    class UndoListener : View.OnClickListener{
        private val TAG = "KTSnackBarActivity"

        override fun onClick(v: View?) {
            Log.e(TAG,  "OnClick")
        }
    }
}