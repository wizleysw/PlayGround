package wizley.android.playground.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [KTUser::class], version = 1)
abstract class KTAppDatabase : RoomDatabase(){
    abstract fun userDao() : KTUserDao
}
