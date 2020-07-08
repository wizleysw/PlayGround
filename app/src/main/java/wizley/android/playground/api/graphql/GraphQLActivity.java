package wizley.android.playground.api.graphql;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.ApolloClient;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;

import org.jetbrains.annotations.NotNull;

import okhttp3.OkHttpClient;
import wizley.android.playground.AddStudentMutation;
import wizley.android.playground.DeleteStudentMutation;
import wizley.android.playground.R;
import wizley.android.playground.StudentTypeQuery;

public class GraphQLActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "GraphQLActivity";

    private static final String GRAPHQL_URL = "http://10.0.2.2:8888/graphql/";
    private Button button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button);

        button = findViewById(R.id.button);
        button.setOnClickListener(this);
    }

    private void queryStudent(){
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        ApolloClient apolloClient = ApolloClient.builder().serverUrl(GRAPHQL_URL).okHttpClient(okHttpClient).build();

        final StudentTypeQuery query =  StudentTypeQuery.builder().name("Wizley").build();
        apolloClient.query(query).enqueue(new ApolloCall.Callback<StudentTypeQuery.Data>() {
            @Override
            public void onResponse(@NotNull Response<StudentTypeQuery.Data> response) {
                Log.e(TAG, "onResponse");
                Log.e(TAG, response.data().students().get(0).name());

                deleteStudent();
            }

            @Override
            public void onFailure(@NotNull ApolloException e) {
                Log.e(TAG, "onFailure");
            }
        });
    }

    private void addStudent(){
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        ApolloClient apolloClient = ApolloClient.builder().serverUrl(GRAPHQL_URL).okHttpClient(okHttpClient).build();

        final AddStudentMutation mutation = AddStudentMutation.builder().name("Wizley").age(10).build();
        apolloClient.mutate(mutation).enqueue(new ApolloCall.Callback<AddStudentMutation.Data>() {
            @Override
            public void onResponse(@NotNull Response<AddStudentMutation.Data> response) {
                Log.e(TAG, "onResponse");
                Log.e(TAG, String.valueOf(response.data().addStudent().success()));

                queryStudent();
            }

            @Override
            public void onFailure(@NotNull ApolloException e) {
                Log.e(TAG, "onFailure");
            }
        });
    }

    private void deleteStudent(){
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        ApolloClient apolloClient = ApolloClient.builder().serverUrl(GRAPHQL_URL).okHttpClient(okHttpClient).build();

        final DeleteStudentMutation mutation = DeleteStudentMutation.builder().name("Wizley").build();
        apolloClient.mutate(mutation).enqueue(new ApolloCall.Callback<DeleteStudentMutation.Data>() {
            @Override
            public void onResponse(@NotNull Response<DeleteStudentMutation.Data> response) {
                Log.e(TAG, "onResponse");
                Log.e(TAG, String.valueOf(response.data().deleteStudent().success()));
            }

            @Override
            public void onFailure(@NotNull ApolloException e) {
                Log.e(TAG, "onFailure");
            }
        });

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.button){
            addStudent();
        }
    }
}