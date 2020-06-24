package wizley.android.playground.ui.clipboard;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class StringClipBoardActivity extends AppCompatActivity {
    private static final String TAG = "StringClipBoardActivity";

    private String pasteData = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "OnCreate");

        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);

        ClipData clip = ClipData.newPlainText("label", "What I copied to ClipBoard");
        if(clipboard != null && clip != null) {
            clipboard.setPrimaryClip(clip);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "onResume");
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        Log.e(TAG, "onWindowFocusChanged");

        // from Android 10 or higher, app can not access clipboard data unless current app has focus
        if(hasFocus) {
            checkClipBoard();
        }
    }

    private void checkClipBoard(){
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);

        if (clipboard != null) {
            if(!(clipboard.hasPrimaryClip())) {
                Log.e(TAG, "No PrimaryClip");
            } else if (clipboard.getPrimaryClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)){
                ClipData.Item item = clipboard.getPrimaryClip().getItemAt(0);
                pasteData = (String) item.getText();
                Log.e(TAG, pasteData);
            } else {
                Log.e(TAG, "No Text Plain");
            }
        }
    }
}
