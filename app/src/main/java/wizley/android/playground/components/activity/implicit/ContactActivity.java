package wizley.android.playground.components.activity.implicit;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;

import wizley.android.playground.R;

public class ContactActivity extends Activity implements View.OnClickListener {
    private static String TAG = "ContactActivity";
    private static int PICK_CONTACT_REQUEST = 100;

    private Button button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_implicit);

        button = (Button) findViewById(R.id.implicit_button);
        button.setText("Contact");
        button.setOnClickListener(this);
    }

    @SuppressLint("IntentReset")
    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.implicit_button){
            Intent pickContactIntent = new Intent(Intent.ACTION_PICK, Uri.parse("content://contacts"));
            pickContactIntent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
            startActivityForResult(pickContactIntent, PICK_CONTACT_REQUEST);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == PICK_CONTACT_REQUEST){
            if(resultCode == RESULT_OK){
                Log.e(TAG, "RESULT_OK");

                Uri contactUri = data.getData();
                String[] projection = {ContactsContract.CommonDataKinds.Phone.NUMBER};

                assert contactUri != null;
                @SuppressLint("Recycle")
                Cursor cursor = getContentResolver().query(contactUri, projection, null, null, null);
                if(cursor != null) cursor.moveToFirst();

                assert cursor != null;
                int column = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                String number = cursor.getString(column);

                Log.e(TAG, number);
            } else if (resultCode == RESULT_CANCELED){
                Log.e(TAG, "RESULT_CANCELLED");
            }
        }
    }
}
