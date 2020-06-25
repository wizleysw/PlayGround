package wizley.android.playground.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class KTSong(
    @PrimaryKey val id : Int,
    val songName : String?,
    val artistName : String?
)