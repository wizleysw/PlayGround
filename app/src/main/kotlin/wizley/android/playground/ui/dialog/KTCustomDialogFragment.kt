package wizley.android.playground.ui.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.DialogFragment

class KTCustomDialogFragment : DialogFragment(){
    private val TAG = "KTCustomDialogFragment"

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        Log.e(TAG, "onCreateDialog")

        val builder = AlertDialog.Builder(activity)
        builder.setMessage("Are you OK?")
                .setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, id ->
                    Log.e(TAG, "Yes I am OK!")
                })
                .setNegativeButton("No", DialogInterface.OnClickListener { dialog, id ->
                    Log.e(TAG, "No I am not OK!")
                })

        return builder.create()
    }
}