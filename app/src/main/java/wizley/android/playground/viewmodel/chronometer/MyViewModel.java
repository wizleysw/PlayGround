package wizley.android.playground.viewmodel.chronometer;

import android.os.SystemClock;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyViewModel extends ViewModel {

    private long startTime = SystemClock.elapsedRealtime();
    private MutableLiveData<Long> timetick;

    public MyViewModel(){
        timetick = new MutableLiveData<>();
        timetick.setValue((long)0);
    }

    public long getStartTime() {
        return startTime;
    }

    public long getTimeTick() {
        return this.timetick.getValue();
    }

    public MutableLiveData<Long> getTick(){
        return this.timetick;
    }

    public void setTimeTick(long timetick) {
        this.timetick.setValue(timetick);
    }
}
