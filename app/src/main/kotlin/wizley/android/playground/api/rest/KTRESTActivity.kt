package wizley.android.playground.api.rest

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import wizley.android.playground.R

class KTRESTActivity : AppCompatActivity(), View.OnClickListener, View.OnLongClickListener {
    private val TAG = "KTRESTActivity"

    private lateinit var button : Button
    private val REST_URL = "http://10.0.2.2:8000"
    private var student : KTStudent? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_button)

        button = findViewById(R.id.button)
        button.setOnClickListener(this)
        button.setOnLongClickListener(this)
    }

    private fun getStudent(){
        val retrofit = Retrofit.Builder()
                .baseUrl(REST_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val api : KTStudentAPI = retrofit.create(KTStudentAPI::class.java)
        val call : Call<KTStudent> = api.query("huh")

        call.enqueue(object: Callback<KTStudent>{
            override fun onResponse(call: Call<KTStudent>, response: Response<KTStudent>) {
                Log.e(TAG, "onResponse")
                Log.e(TAG, response.body()?.name)
                Log.e(TAG, response.body()?.age.toString())
            }

            override fun onFailure(call: Call<KTStudent>, t: Throwable) {
                Log.e(TAG, "onFailure")
            }
        })
}

private fun addStudent(){
    val retrofit = Retrofit.Builder()
            .baseUrl(REST_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    val api : KTStudentAPI = retrofit.create(KTStudentAPI::class.java)
    val call : Call<Void> = api.insert("huh", 20)

    call.enqueue(object: Callback<Void> {
        override fun onResponse(call: Call<Void>, response: Response<Void>) {
            if(response.isSuccessful){
                Log.e(TAG, "onResponse")
                getStudent()
            }
        }

        override fun onFailure(call: Call<Void>, t: Throwable) {
            Log.e(TAG, "onFailure")
        }
    })
}

private fun rmStudent(){
    val retrofit = Retrofit.Builder()
            .baseUrl(REST_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    val api : KTStudentAPI = retrofit.create(KTStudentAPI::class.java)
    val call : Call<Void> = api.delete("huh")
    call.enqueue(object : Callback<Void>{
        override fun onResponse(call: Call<Void>, response: Response<Void>) {
            Log.e(TAG, "onResponse")
        }

        override fun onFailure(call: Call<Void>, t: Throwable) {
            Log.e(TAG, "onFailure")
        }
    })
}

override fun onClick(v: View?) {
    if(v!!.id == R.id.button){
        addStudent()
    }
}

override fun onLongClick(v: View?): Boolean {
    if(v!!.id == R.id.button){
        rmStudent()
    }
    return true
}
}