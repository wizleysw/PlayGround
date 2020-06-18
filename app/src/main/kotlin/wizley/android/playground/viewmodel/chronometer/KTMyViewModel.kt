package wizley.android.playground.viewmodel.chronometer

import android.os.SystemClock
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class KTMyViewModel : ViewModel() {
    private val startTime = SystemClock.elapsedRealtime()
    private var timetick : MutableLiveData<Long>?= null

    init{
        timetick = MutableLiveData()
        timetick!!.value = 0
    }

    fun getStartTime() : Long{
        return startTime
    }

    fun getTimeTick() : Long{
        return timetick!!.value!!
    }

    fun getTick() : MutableLiveData<Long>{
        return timetick!!
    }

    fun setTimeTick(tick : Long){
        timetick!!.value = tick
    }

}