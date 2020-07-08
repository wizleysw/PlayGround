package wizley.android.playground.api.graphql

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.apollographql.apollo.ApolloCall
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.exception.ApolloException
import okhttp3.OkHttpClient
import wizley.android.playground.AddStudentMutation
import wizley.android.playground.DeleteStudentMutation
import wizley.android.playground.R
import wizley.android.playground.StudentTypeQuery

class KTGraphQLActivity : AppCompatActivity(), View.OnClickListener {
    private val TAG = "KTGraphQLActivity"

    private val GRAPHQL_URL = "http://10.0.2.2:8888/graphql/"
    private lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_button)

        button = findViewById(R.id.button)
        button.setOnClickListener(this)
        button.text = "graphQL"
    }

    private fun queryStudent(){
        val okHttpClient : OkHttpClient = OkHttpClient.Builder().build()
        val apolloClient : ApolloClient = ApolloClient.builder().serverUrl(GRAPHQL_URL).okHttpClient(okHttpClient).build()

        val query : StudentTypeQuery = StudentTypeQuery.builder().name("jch").build()
        apolloClient.query(query).enqueue(object: ApolloCall.Callback<StudentTypeQuery.Data>(){
            override fun onResponse(response: Response<StudentTypeQuery.Data>) {
                Log.e(TAG, "onResponse")
                Log.e(TAG, response.data()?.students()?.get(0)?.name())

                deleteStudent()
            }

            override fun onFailure(e: ApolloException) {
                Log.e(TAG, "onFailure")
            }
        })
    }

    private fun addStudent(){
        val okHttpClient : OkHttpClient = OkHttpClient.Builder().build()
        val apolloClient : ApolloClient = ApolloClient.builder().serverUrl(GRAPHQL_URL).okHttpClient(okHttpClient).build()

        val mutation : AddStudentMutation = AddStudentMutation.builder().name("jch").age(10).build()
        apolloClient.mutate(mutation).enqueue(object: ApolloCall.Callback<AddStudentMutation.Data>(){
            override fun onResponse(response: Response<AddStudentMutation.Data>) {
                Log.e(TAG, "onResponse");
                Log.e(TAG, response.data()?.addStudent()?.success().toString());

                queryStudent()
            }
            override fun onFailure(e: ApolloException) {
                Log.e(TAG, "onFailure")
            }
        })

    }

    private fun deleteStudent(){
        val okHttpClient : OkHttpClient = OkHttpClient.Builder().build()
        val apolloClient : ApolloClient = ApolloClient.builder().serverUrl(GRAPHQL_URL).okHttpClient(okHttpClient).build()

        val mutation : DeleteStudentMutation = DeleteStudentMutation.builder().name("jch").build()
        apolloClient.mutate(mutation).enqueue(object: ApolloCall.Callback<DeleteStudentMutation.Data>(){
            override fun onResponse(response: Response<DeleteStudentMutation.Data>) {
                Log.e(TAG, "onResponse");
                Log.e(TAG, response.data()?.deleteStudent()?.success().toString());
            }
            override fun onFailure(e: ApolloException) {
                Log.e(TAG, "onFailure")
            }
        })
    }

    override fun onClick(v: View?) {
        if(v!!.id == R.id.button){
            addStudent()
        }
    }


}