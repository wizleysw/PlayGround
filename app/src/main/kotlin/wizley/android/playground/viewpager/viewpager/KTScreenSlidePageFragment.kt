package wizley.android.playground.viewpager.viewpager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import wizley.android.playground.R

class KTScreenSlidePageFragment : Fragment(){

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ) : View = inflater.inflate(R.layout.layout_slide_page, container, false)
}