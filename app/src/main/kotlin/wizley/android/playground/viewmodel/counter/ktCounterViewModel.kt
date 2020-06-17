package wizley.android.playground.viewmodel.counter

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ktCounterViewModel : ViewModel(){
    private var counter : MutableLiveData<Long>? = null

    init {
        counter = MutableLiveData<Long>()
        counter!!.value = 0
    }

    fun increase(){
        counter!!.value = counter!!.value?.plus(1)
    }

    fun decrease(){
        if(counter!!.value!! >= 1) counter!!.value = counter!!.value?.minus(1)
    }

    fun getCounter() : MutableLiveData<Long> {
        return this.counter!!
    }

    fun setValue(value : Long){
        counter!!.value = value
    }

    fun getValue() : Long? {
        return this.counter!!.value
    }

}