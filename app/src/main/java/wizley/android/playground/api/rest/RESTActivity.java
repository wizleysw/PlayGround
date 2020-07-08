package wizley.android.playground.api.rest;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import wizley.android.playground.R;

public class RESTActivity extends AppCompatActivity implements View.OnClickListener, View.OnLongClickListener {
    private static final String TAG = "RESTActivity";

    private Button button;
    private String REST_URL = "http://10.0.2.2:8000/";
    private Student student;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button);

        button = findViewById(R.id.button);
        button.setOnClickListener(this);
        button.setOnLongClickListener(this);

        button.setText("REST");
    }

    private void getStudent(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(REST_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        StudentAPI api = retrofit.create(StudentAPI.class);
        Call<Student> call = api.query("jay");
        // call.execute() is synchronized way
        call.enqueue(new Callback<Student>() {
            @Override
            public void onResponse(Call<Student> call, Response<Student> response) {
                if(!response.isSuccessful()){
                    return;
                }
                Log.e(TAG, "onResponse");

                student = response.body();

                Log.e(TAG, student.name);
                Log.e(TAG, String.valueOf(student.age));
            }

            @Override
            public void onFailure(Call<Student> call, Throwable t) {
                Log.e(TAG, "onFailure");
            }
        });
    }

    private void addStudent(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(REST_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        StudentAPI api = retrofit.create(StudentAPI.class);
        Call<Void> call = api.insert("jay", 20);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.e(TAG, "onResponse");
                getStudent();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e(TAG, "onFailure");
            }
        });
    }

    private void rmStudent(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(REST_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        StudentAPI api = retrofit.create(StudentAPI.class);
        Call<Void> call = api.delete("jay");
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.e(TAG, "onResponse");
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
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

    @Override
    public boolean onLongClick(View v) {
        if(v.getId() == R.id.button){
            rmStudent();
        }
        return true;
    }
}
