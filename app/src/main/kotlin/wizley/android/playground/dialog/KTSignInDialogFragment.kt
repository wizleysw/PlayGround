package wizley.android.playground.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.DialogFragment
import wizley.android.playground.R
import java.lang.ClassCastException

class KTSignInDialogFragment : DialogFragment(){
    private val TAG = "KTSignInDialogFragment"

    internal lateinit var listener : SignInDialogListener

    interface SignInDialogListener{
        fun onDialogPositiveClick(dialog: DialogFragment)
        fun onDialogNegativeClick(dialog: DialogFragment)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try{
            listener = context as SignInDialogListener
        } catch (e : ClassCastException){
            throw ClassCastException(context.toString())
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity)

        val inflater = requireActivity().layoutInflater

        builder.setView(inflater.inflate(R.layout.layout_signin, null))
                .setPositiveButton("OK",
                DialogInterface.OnClickListener{ dialog, which ->
                    Log.e(TAG, "OK")
                    listener.onDialogPositiveClick(this)
                })
                .setNegativeButton("Cancel",
                DialogInterface.OnClickListener{dialog, which ->
                    Log.e(TAG, "Cancel")
                    listener.onDialogNegativeClick(this)
                })

        return builder.create()
    }
}