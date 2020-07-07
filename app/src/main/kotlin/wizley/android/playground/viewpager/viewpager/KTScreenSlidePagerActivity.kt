package wizley.android.playground.viewpager.viewpager

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.viewpager.widget.ViewPager
import wizley.android.playground.R

const val NUM_PAGES = 5
class KTScreenSlidePagerActivity : FragmentActivity(){
    private lateinit var pager : ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_viewpager)

        pager = findViewById(R.id.pager)
        pager.setPageTransformer(true, KTZoomOutPageTransformer())

        val pagerAdapter = KTScreenSlidePagerAdapter(supportFragmentManager)
        pager.adapter = pagerAdapter
    }

    override fun onBackPressed() {
        if(pager.currentItem == 0){
            super.onBackPressed()
        } else {
            pager.currentItem = pager.currentItem -1
        }
    }
}