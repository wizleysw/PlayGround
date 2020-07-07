package wizley.android.playground.viewpager.viewpager;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import wizley.android.playground.R;

public class ScreenSlidePageFragment extends Fragment {
    private static final String TAG = "ScreenSlidePageFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e(TAG, "onCreateView");
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.layout_slide_page, container, false);
        return rootView;
    }
}
