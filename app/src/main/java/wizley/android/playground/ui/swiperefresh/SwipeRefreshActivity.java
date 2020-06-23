package wizley.android.playground.ui.swiperefresh;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


import wizley.android.playground.R;

// implementation "androidx.swiperefreshlayout:swiperefreshlayout:1.0.0"
public class SwipeRefreshActivity extends AppCompatActivity {
    private static final String TAG = "SwipeRefreshActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swiperefresh);

        final SwipeRefreshLayout swipeRefreshLayout = findViewById(R.id.swiperefresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.e(TAG, "onRefresh");
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }
}
