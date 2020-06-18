package wizley.android.playground.databinding;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyViewModel extends ViewModel {
    public MutableLiveData<Integer> counter = new MutableLiveData<>();

    public MyViewModel(){
        counter.setValue(0);
    }

    public void increase(){
        counter.setValue(counter.getValue()+1);
    }

    public void decrease(){
        if(counter.getValue() >= 1) {
            counter.setValue(counter.getValue() - 1);
        }
    }

}
