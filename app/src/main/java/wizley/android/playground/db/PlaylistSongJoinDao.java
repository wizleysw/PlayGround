package wizley.android.playground.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PlaylistSongJoinDao {
    @Insert
    void insert(PlaylistSongJoin playlistSongJoin);

    @Query("SELECT * FROM playlist " +
            "INNER JOIN playlist_song_join " +
            "ON playlist.id=playlist_song_join.playlistid " +
            "WHERE playlist_song_join.songId=:songId")
    List<Playlist> getPlaylistsForSong(final int songId);

    @Query("SELECT * FROM song " +
            "INNER JOIN playlist_song_join " +
            "ON song.id=playlist_song_join.songId " +
            "WHERE playlist_song_join.playlistId=:playlistId")
    List<Song> getSongsForPlaylist(final int playlistId);
}
