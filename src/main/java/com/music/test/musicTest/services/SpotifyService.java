package com.music.test.musicTest.services;

import com.music.test.musicTest.models.Artist;
import com.music.test.musicTest.models.Playlist;

import java.util.List;

public interface SpotifyService {

    List<Playlist> getFeaturedPlaylists();

    List<Artist> getArtistsByPlaylistId(String playlistId);

}
