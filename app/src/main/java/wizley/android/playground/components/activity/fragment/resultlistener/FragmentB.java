package wizley.android.playground.components.activity.fragment.resultlistener;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

// androidx.fragment:fragment:1.3.0-alpha04
public class FragmentB extends Fragment {
    private static final String TAG = "FragmentB";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "onCreate");

        Bundle result = new Bundle();
        result.putString("bundleKey", "This is FragmentResultListener");

        Log.e(TAG, "setFragmentResult");
        getParentFragmentManager().setFragmentResult("requestKey", result);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
