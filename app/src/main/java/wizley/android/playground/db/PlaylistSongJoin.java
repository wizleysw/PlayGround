package wizley.android.playground.db;

import androidx.room.Entity;
import androidx.room.ForeignKey;

@Entity(tableName = "playlist_song_join",
        primaryKeys = {"playlistId", "songId"},
        foreignKeys = {
                @ForeignKey(entity = Playlist.class,
                        parentColumns = "id",
                        childColumns = "playlistId"),
                @ForeignKey(entity = Song.class,
                        parentColumns = "id",
                        childColumns = "songId")
        })
public class PlaylistSongJoin {
    public int playlistId;
    public int songId;
}
