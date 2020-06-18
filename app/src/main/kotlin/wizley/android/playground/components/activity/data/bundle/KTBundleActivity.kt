package wizley.android.playground.components.activity.data.bundle

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import wizley.android.playground.R

class KTBundleActivity : Activity(), View.OnClickListener {
    private val TAG = "ktBundleActivity"

    private var text_id : TextView? = null
    private var text_title : TextView? = null
    private var text_author : TextView? = null
    private var text_publisher : TextView? = null
    private var text_price : TextView? = null
    private var button : Button? = null

    private var book : KTBookData? = null
    private var new_book : KTBookData? = null
    private var book_list : ArrayList<KTBookData>? = null
    private var new_book_list : ArrayList<KTBookData>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parcelable)

        button = findViewById<Button>(R.id.click)
        button!!.setOnClickListener(this)

        book = KTBookData(1, "Little Prince", "Saint Exupery", "Ley", 2000)

        book_list = ArrayList<KTBookData>()
        book_list!!.add(book!!)

        if(intent.hasExtra("bookInfo")){
            new_book = intent.getSerializableExtra("bookInfo") as KTBookData?
            book_list!!.add(new_book!!)
            Log.e(TAG, new_book!!.author)
        }

        if(intent.hasExtra("bookList")){
            new_book_list = intent.getSerializableExtra("bookList") as ArrayList<KTBookData>?
            Log.e(TAG, new_book_list!!.size.toString())
        }
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.click -> {
                intent = Intent(this, KTBundleActivity::class.java)
                intent.putExtra("bookInfo", book)
                intent.putExtra("bookList", book_list)
                startActivity(intent)
            }
        }
    }
}