package wizley.android.playground.components.activity.data.parcelable

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import wizley.android.playground.R

class ktParcelableActivity : Activity(), View.OnClickListener {
    private val TAG = "ktParcelableActivity"

    private var text_id : TextView? = null
    private var text_title : TextView? = null
    private var text_author : TextView? = null
    private var text_publisher : TextView? = null
    private var text_price : TextView? = null
    private var button : Button? = null

    private var book : ktBookData? = null
    private var new_book : ktBookData? = null
    private var book_list : ArrayList<ktBookData>? = null
    private var new_book_list : ArrayList<ktBookData>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parcelable)

        button = findViewById<Button>(R.id.click)
        button!!.setOnClickListener(this)

        SetBook();

        if(intent.hasExtra("bookInfo")){
            new_book = intent.getParcelableExtra<ktBookData>("bookInfo")
            book_list!!.add(new_book!!)

            text_id = findViewById<TextView>(R.id.id)
            text_title = findViewById<TextView>(R.id.title)
            text_author = findViewById<TextView>(R.id.author)
            text_publisher = findViewById<TextView>(R.id.publisher)
            text_price = findViewById<TextView>(R.id.price)

            text_id!!.text = new_book!!.id.toString()
            text_title!!.text = new_book!!.title
            text_author!!.text = new_book!!.author
            text_publisher!!.text = new_book!!.publisher
            text_price!!.text = new_book!!.price.toString()
        }

        if(intent.hasExtra("bookList")){
            new_book_list = intent.getParcelableArrayListExtra("bookList")
            Log.e(TAG, new_book_list!!.size.toString())
        }
    }

    fun SetBook(){
        book = ktBookData(1, "Little Prince", "Saint Exupery", "Ley", 2000)

        book_list = ArrayList<ktBookData>()
        book_list!!.add(book!!)
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.click -> {
                intent = Intent(this, ktParcelableActivity::class.java)
                if(book_list!!.size > 1){
                    intent.putParcelableArrayListExtra("bookList", book_list)
                }
                intent.putExtra("bookInfo", book);
                startActivity(intent)
            }
        }
    }

}