package wizley.android.playground.db;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import java.util.List;

public class RoomUserActivity extends AppCompatActivity {
    private static final String TAG = "RoomUserActivity";

    private UserDao userDao;
    private AppDatabase db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "database-name").build();
        userDao = db.userDao();
    }

    @Override
    protected void onResume() {
        super.onResume();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                List<User> users = userDao.getAll();
                Log.e(TAG, String.valueOf(users.size()));

                Address add = new Address();
                add.street = "Street";
                add.state = "State";
                add.city = "City";
                add.postCode = 12345;

                User u = new User();
                u.firstName = "firstName";
                u.lastName = "lastName";
                u.address = add;

                Log.e(TAG, "insertAll");
                userDao.insertAll(u);

                Log.e(TAG, "getAll");
                users = userDao.getAll();
                Log.e(TAG, String.valueOf(users.size()));

                Log.e(TAG, "findByName");
                User user_result = userDao.findByName("firstName", "lastName");
                Log.e(TAG, user_result.address.city);

                Log.e(TAG, "update");
                user_result.address.city = "Seoul";
                userDao.update(user_result);

                Log.e(TAG, "findByName");
                user_result = userDao.findByName("firstName", "lastName");
                Log.e(TAG, user_result.address.city);

                Log.e(TAG, "delete");
                userDao.delete(user_result);

                Log.e(TAG, "getAll");
                users = userDao.getAll();
                Log.e(TAG, String.valueOf(users.size()));
            }
        });

        thread.start();
    }
}
