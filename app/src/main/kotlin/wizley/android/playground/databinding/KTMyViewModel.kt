package wizley.android.playground.databinding

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class KTMyViewModel : ViewModel(){
    var counter : MutableLiveData<Integer>? = null

    init{
        counter = MutableLiveData()
        counter!!.value = Integer(0)
    }

    fun increase(){
        counter!!.value = Integer((counter!!.value?.toInt() ?: 0) + 1)
    }

    fun decrease(){
        if(counter!!.value?.toInt()!! >= 1) {
            counter!!.value = Integer((counter!!.value?.toInt() ?: 0) - 1)
        }
    }
}