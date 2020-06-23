package wizley.android.playground.ui.appbar;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;

import wizley.android.playground.R;

public class AppbarActivity extends AppCompatActivity {
    private static final String TAG = "AppbarActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "onCreate");

        setContentView(R.layout.activity_appbar);

        Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

        // Has to add parentActivityName to Manifest
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.e(TAG, "onCreateOptionsMenu");
        getMenuInflater().inflate(R.menu.appbar_action, menu);

        MenuItem menuItem = menu.findItem(R.id.action_search);

        MenuItem.OnActionExpandListener expandListener = new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                Log.e(TAG, "onMenuItemActionExpand");
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                Log.e(TAG, "onMenuItemActionCollapse");
                return true;
            }
        };

        menuItem.setOnActionExpandListener(expandListener);

        SearchView searchView = (SearchView) menuItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener(){
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.e(TAG, "onQueryTextSubmit");
                Log.e(TAG, query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.e(TAG, "onQueryTextChange");
                Log.e(TAG, newText);
                return true;
            }

        });

        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                Log.e(TAG, "onClose");
                return true;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_search:
                Log.e(TAG, "Action Search");
                return true;
            case R.id.action_add:
                Log.e(TAG, "Action Add");
                return true;
            case R.id.action_remove:
                Log.e(TAG, "Action Remove");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
