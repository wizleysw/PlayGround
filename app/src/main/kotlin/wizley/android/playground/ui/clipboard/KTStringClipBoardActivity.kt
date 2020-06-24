package wizley.android.playground.ui.clipboard

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.ClipDescription
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class KTStringClipBoardActivity : AppCompatActivity(){
    private val TAG = "KTStringClipBoardActivity"

    private var pasteData = ""

    @SuppressLint("LongLogTag")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e(TAG, "onCreate")

        val clipboard : ClipboardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

        val clip : ClipData = ClipData.newPlainText("label", "What I copied to ClipBoard")
        clipboard.setPrimaryClip(clip)
    }

    @SuppressLint("LongLogTag")
    override fun onResume() {
        super.onResume()
        Log.e(TAG, "onResume")
    }

    @SuppressLint("LongLogTag")
    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        Log.e(TAG, "onWindowFocusChanged")

        if(hasFocus){
            checkClipBoard()
        }
    }

    @SuppressLint("LongLogTag")
    private fun checkClipBoard() {
        val clipboard : ClipboardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

        if(clipboard.hasPrimaryClip()){
            if(clipboard.primaryClipDescription!!.hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)){
                val item : ClipData.Item = clipboard.primaryClip!!.getItemAt(0)
                pasteData = item.text.toString()
                Log.e(TAG, pasteData)
            }
        }
    }


}