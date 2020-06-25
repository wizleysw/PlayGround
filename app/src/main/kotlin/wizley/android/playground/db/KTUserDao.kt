package wizley.android.playground.db

import androidx.room.*

@Dao
interface KTUserDao{
    @Query("SELECT * FROM ktuser")
    fun getAll() : List<KTUser>

    @Query("SELECT * FROM ktuser WHERE uid IN (:userIds)")
    fun loadAllByIds(userIds: Array<Int>) : List<KTUser>

    @Query("SELECT * FROM ktuser WHERE first_name LIKE :first AND " + "last_name LIKE :last LIMIT 1")
    fun findByName(first : String, last: String) : KTUser

    @Insert
    fun insertAll(vararg users: KTUser)

    @Delete
    fun delete(user : KTUser)

    @Update
    fun update(user : KTUser)
}