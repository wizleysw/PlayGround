package wizley.android.playground.appbar

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import wizley.android.playground.R

class KTAppbarActivity : AppCompatActivity(){
    private val TAG = "KTAppbarActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e(TAG, "onCreate")

        setContentView(R.layout.activity_appbar)

        val toolbar = findViewById<Toolbar>(R.id.my_toolbar)
        setSupportActionBar(toolbar)

        val ab = supportActionBar
        ab!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        Log.e(TAG, "onCreateOptionsMenu")
        menuInflater.inflate(R.menu.appbar_action, menu)

        val menuItem : MenuItem = menu!!.findItem(R.id.action_search)

        val expandListener = object : MenuItem.OnActionExpandListener{
            override fun onMenuItemActionExpand(item: MenuItem?): Boolean {
                Log.e(TAG, "onMenuItemActionExpand")
                return true;
            }

            override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
                Log.e(TAG, "onMenuItemActionCollapse")
                return true;
            }
        }

        menuItem.setOnActionExpandListener(expandListener)

        val searchView : SearchView = menuItem.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                Log.e(TAG, "onQueryTextSubmit")
                Log.e(TAG, query);
                return true;
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                Log.e(TAG, "onQueryTextChange")
                Log.e(TAG, newText);
                return true;
            }
        })

        searchView.setOnCloseListener {
            Log.e(TAG, "onClose")
            true
        }

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.action_search -> {
                Log.e(TAG, "Action Search")
                true
            }
            R.id.action_add -> {
                Log.e(TAG, "Action Add")
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }
}