package wizley.android.playground.viewpager.viewpager;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import wizley.android.playground.R;

public class ScreenSlidePagerActivity extends FragmentActivity {
    public static final int NUM_PAGES = 5;
    private ViewPager pager;
    private PagerAdapter pagerAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager);

        pager = findViewById(R.id.pager);
        pager.setPageTransformer(true, new ZoomOutPageTransformer());
        pagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        pager.setAdapter(pagerAdapter);
    }

    @Override
    public void onBackPressed() {
        if(pager.getCurrentItem() == 0){
            super.onBackPressed();
        } else {
            pager.setCurrentItem(pager.getCurrentItem() -1);
        }
    }
}
