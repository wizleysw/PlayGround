package wizley.android.playground.livedata

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ktLiveData : ViewModel(){
    private var currentName : MutableLiveData<String> ? = null

    fun getCurrentName(): String? {
        if(currentName == null){
            currentName = MutableLiveData();
        }
        return currentName!!.value
    }

    fun setCurrentName(name: String?){
        this.currentName!!.value = name
    }
}