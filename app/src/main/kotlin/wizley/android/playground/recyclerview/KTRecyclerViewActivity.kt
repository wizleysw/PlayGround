package wizley.android.playground.recyclerview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import wizley.android.playground.R

class KTRecyclerViewActivity : AppCompatActivity(){
    private lateinit var recyclerView : RecyclerView
    private lateinit var mAdapter : RecyclerView.Adapter<*>
    private lateinit var mLayoutManager : RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recyclerview)

        val dataset = arrayOf("Apple", "Banana", "Kiwi")

        mLayoutManager = LinearLayoutManager(this)
        mAdapter = KTMyAdapter(dataset)

        recyclerView = findViewById<RecyclerView>(R.id.recycler_view).apply {
            setHasFixedSize(true)
            layoutManager = mLayoutManager
            adapter = mAdapter
        }

    }

}