package wizley.android.playground.components.activity.implicit

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.CalendarContract
import android.view.View
import android.widget.Button
import wizley.android.playground.R
import java.util.*

class ktCalendarActivity : Activity(), View.OnClickListener {
    private val TAG = "ktCalendarActivity"
    private var button : Button?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_implicit)

        button = findViewById<Button>(R.id.implicit_button)
        button!!.text = "Calendar"
        button!!.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.implicit_button -> {
                val calendarIntent = Intent(Intent.ACTION_INSERT, CalendarContract.Events.CONTENT_URI).apply {
                    val beginTime: Calendar = Calendar.getInstance().apply {
                        set(2012, 0, 1, 7, 30)
                    }
                    val endTime = Calendar.getInstance().apply {
                        set(2012, 0, 2, 7, 30)
                    }
                    putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, beginTime.timeInMillis)
                    putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endTime.timeInMillis)
                    putExtra(CalendarContract.Events.TITLE, "Android Class")
                    putExtra(CalendarContract.Events.EVENT_LOCATION, "Secret dojo")
                }
                startActivity(calendarIntent)
            }
        }
    }
}