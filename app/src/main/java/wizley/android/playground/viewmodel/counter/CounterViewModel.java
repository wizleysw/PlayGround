package wizley.android.playground.viewmodel.counter;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CounterViewModel extends ViewModel {
    private MutableLiveData<Integer> counter = new MutableLiveData<>();

    public CounterViewModel(){
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

    public MutableLiveData<Integer> getCounter(){
        return this.counter;
    }

    public Integer getValue(){
        return this.counter.getValue();
    }

    public void setValue(Integer val){
        counter.setValue(val);
    }
}
