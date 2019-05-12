package com.music.test.musicTest.util;

import com.music.test.musicTest.models.Artist;
import com.music.test.musicTest.models.Playlist;
import com.wrapper.spotify.model_objects.specification.ArtistSimplified;
import com.wrapper.spotify.model_objects.specification.PlaylistSimplified;

import java.util.Map;

import static com.music.test.musicTest.common.SpotifyConstants.EXTERNAL_URL_KEY;

public class SpotifyObjectMapper {

    public static Playlist toPlaylist(PlaylistSimplified playlistSimplified) {
        Map<String, String> externalUrls = playlistSimplified.getExternalUrls().getExternalUrls();
        final String externalUrl = externalUrls.getOrDefault(EXTERNAL_URL_KEY, "Not available");

        return new Playlist(playlistSimplified.getId(), playlistSimplified.getName(), externalUrl);
    }

    public static Artist toArtist(ArtistSimplified artistSimplified){

        Map<String, String> externalUrls = artistSimplified.getExternalUrls().getExternalUrls();
        final String externalUrl = externalUrls.getOrDefault(EXTERNAL_URL_KEY, "Not available");

        return new Artist(artistSimplified.getName(), externalUrl);
    }
}
