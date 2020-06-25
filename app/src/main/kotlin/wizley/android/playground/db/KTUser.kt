package wizley.android.playground.db

import android.graphics.Bitmap
import androidx.room.*

@Entity(tableName = "ktuser")
data class KTUser(
        @PrimaryKey(autoGenerate = true) var uid: Int? = null,
        @ColumnInfo(name = "first_name") var firstName: String? = "",
        @ColumnInfo(name = "last_name") var lastName: String? = "",
        @Ignore var picture: Bitmap? = null
)