package wizley.android.playground.components.activity.implicit;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;

import java.util.Calendar;

import wizley.android.playground.R;

public class CalendarActivity extends Activity implements View.OnClickListener {
    private static String TAG = "CalendarActivity";

    private Button button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_implicit);

        button = (Button) findViewById(R.id.implicit_button);
        button.setText("Calendar");
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.implicit_button){
            Intent calendarIntent = new Intent(Intent.ACTION_INSERT, CalendarContract.Events.CONTENT_URI);
            Calendar beginTime = Calendar.getInstance();
            beginTime.set(2020, 0, 0, 0, 0);
            Calendar endTime = Calendar.getInstance();
            endTime.set(2020, 0, 0, 0, 55);
            calendarIntent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, beginTime.getTimeInMillis());
            calendarIntent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endTime.getTimeInMillis());
            calendarIntent.putExtra(CalendarContract.Events.TITLE, "Android Class");
            calendarIntent.putExtra(CalendarContract.Events.EVENT_LOCATION, "secret dojo");
            startActivity(calendarIntent);
        }
    }
}
