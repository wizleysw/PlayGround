package wizley.android.playground.ui.dialog

import android.os.Bundle
import android.util.Log
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity
import wizley.android.playground.R

class KTSignInDialogActivity : FragmentActivity(), KTSignInDialogFragment.SignInDialogListener {
    private val TAG = "KTSignInDialogActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val fm = supportFragmentManager
        val dialogFragment = KTSignInDialogFragment()
        dialogFragment.show(fm, "dialog fragment")
    }

    override fun onDialogPositiveClick(dialog: DialogFragment) {
        Log.e(TAG, "onDialogPositiveClick")
        val username = dialog.dialog!!.findViewById<EditText>(R.id.username)
        val password = dialog.dialog!!.findViewById<EditText>(R.id.password)
        Log.e(TAG, username.text.toString())
        Log.e(TAG, password.text.toString())
    }

    override fun onDialogNegativeClick(dialog: DialogFragment) {
        Log.e(TAG, "onDialogNegativeClick")
    }

}