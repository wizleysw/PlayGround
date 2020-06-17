package wizley.android.playground.livedata;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

// See observer pattern in viewModel package
public class NameViewModel extends ViewModel {
    private MutableLiveData<String> currentName;

    private String getCurrentName(){
        if(currentName == null){
            currentName = new MutableLiveData<String>();
        }
        return currentName.getValue();
    }

    public void setCurrentName(String currentName) {
        this.currentName.setValue(currentName);
    }
}
