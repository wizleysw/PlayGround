package wizley.android.playground.dialog

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class KTAlertDialogActivity : AppCompatActivity(){
    private val TAG = "KTAlertDialogActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val items = arrayOf("Red", "Green", "Blue")

        val builder = AlertDialog.Builder(this)
        builder.setTitle("This is Title")
                .setItems(items, DialogInterface.OnClickListener{dialog, which ->
                    Log.e(TAG, "OnClicked")
                })

        val dialog : AlertDialog = builder.create()
        dialog.show()
    }

}