package wizley.android.playground.db

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room

class KTRoomUserActivity : AppCompatActivity(){
    private val TAG = "KTRoomUserActivity"

    private var userDao : KTUserDao ?= null
    private var db : KTAppDatabase ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        db = Room.databaseBuilder(applicationContext, KTAppDatabase::class.java, "database2-name").build()
        userDao = db!!.userDao()
    }

    override fun onResume() {
        super.onResume()

        val thread = Thread(){
            var users : List<KTUser> = userDao!!.getAll()
            Log.e(TAG, users.size.toString());

            val u = KTUser(firstName = "firstName", lastName = "lastName", picture = null, uid = 0)

            Log.e(TAG, "insertAll")
            userDao!!.insertAll(u)

            users = userDao!!.getAll()
            Log.e(TAG, users.size.toString());

            Log.e(TAG, "findByName");
            var user_result = userDao!!.findByName("firstName", "lastName")
            Log.e(TAG, user_result!!.firstName)

            Log.e(TAG, "update");
            user_result!!.firstName = "secondName"
            userDao!!.update(user_result)

            Log.e(TAG, "findByName");
            user_result = userDao!!.findByName("secondName", "lastName");
            Log.e(TAG, user_result!!.firstName);

            Log.e(TAG, "delete");
            userDao!!.delete(user_result);

            users = userDao!!.getAll()
            Log.e(TAG, users.size.toString());
        }

        thread.start()
    }

}