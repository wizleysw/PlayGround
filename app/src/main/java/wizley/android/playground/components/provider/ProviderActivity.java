package wizley.android.playground.components.provider;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import wizley.android.playground.R;

public class ProviderActivity extends AppCompatActivity implements View.OnClickListener, View.OnLongClickListener {
    private static final String TAG = "ProviderActivity";
    private static final String PROVIDER_URI = "content://wizley.android.playground.components.provider.myProvider";
    private Button button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button);

        button = findViewById(R.id.button);
        button.setOnClickListener(this);
        button.setOnLongClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.button){
            ContentValues newValue = new ContentValues();

            newValue.put("number", "12345");
            newValue.put("name", "name");
            newValue.put("department", "computer");
            newValue.put("age", "1");
            newValue.put("grade", 4);

            getContentResolver().insert(Uri.parse(PROVIDER_URI), newValue);
            button.setText("insertion OK");
        }
    }

    @Override
    public boolean onLongClick(View v) {
        if(v.getId() == R.id.button) {
            String[] columns = new String[]{"_id", "number", "name", "department", "age", "grade"};

            Cursor c = getContentResolver().query(
                    Uri.parse(PROVIDER_URI),
                    columns,
                    null, null, null, null);

            if(c != null){
                button.setText("");
            }

            while(c.moveToNext()){
                String name = c.getString(2);
                try {
                    button.setText(name);
                    Log.e(TAG, name);
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            button.setText("query OK");
            c.close();
        }
        return true;
    }
}
