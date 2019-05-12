package com.music.test.musicTest.util;

import com.music.test.musicTest.models.Artist;
import com.music.test.musicTest.models.Playlist;
import com.wrapper.spotify.model_objects.specification.ArtistSimplified;
import com.wrapper.spotify.model_objects.specification.ExternalUrl;
import com.wrapper.spotify.model_objects.specification.PlaylistSimplified;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static com.music.test.musicTest.common.SpotifyConstants.EXTERNAL_URL_KEY;
import static org.junit.Assert.*;

public class SpotifyObjectMapperTest {

    @Test
    public void toPlaylist() {
        Map<String, String> urlMap = new HashMap<>();
        urlMap.put(EXTERNAL_URL_KEY, "testUrl");

        PlaylistSimplified playlistSimplified = new PlaylistSimplified.Builder()
                .setId("123")
                .setName("testPlaylistName")
                .setExternalUrls(new ExternalUrl.Builder().setExternalUrls(urlMap).build())
                .build();

        Playlist result = SpotifyObjectMapper.toPlaylist(playlistSimplified);

        assertEquals("123", result.getId());
        assertEquals("testPlaylistName", result.getName());
        assertEquals("testUrl", result.getExternal_url());
    }

    @Test
    public void toPlaylist_noUrlFound() {
        Map<String, String> urlMap = new HashMap<>();

        PlaylistSimplified playlistSimplified = new PlaylistSimplified.Builder()
                .setId("123")
                .setName("testPlaylistName")
                .setExternalUrls(new ExternalUrl.Builder().setExternalUrls(urlMap).build())
                .build();

        Playlist result = SpotifyObjectMapper.toPlaylist(playlistSimplified);

        assertEquals("123", result.getId());
        assertEquals("testPlaylistName", result.getName());
        assertEquals("Not available", result.getExternal_url());
    }

    @Test
    public void toArtist() {
        Map<String, String> urlMap = new HashMap<>();
        urlMap.put(EXTERNAL_URL_KEY, "testUrl");

        ArtistSimplified artistSimplified = new ArtistSimplified.Builder()
                .setName("testArtistName")
                .setExternalUrls(new ExternalUrl.Builder().setExternalUrls(urlMap).build())
                .build();

        Artist result = SpotifyObjectMapper.toArtist(artistSimplified);

        assertEquals("testArtistName", result.getName());
        assertEquals("testUrl", result.getExternal_url());
    }


    @Test
    public void toArtist_noUrlFound() {
        Map<String, String> urlMap = new HashMap<>();

        ArtistSimplified artistSimplified = new ArtistSimplified.Builder()
                .setName("testArtistName")
                .setExternalUrls(new ExternalUrl.Builder().setExternalUrls(urlMap).build())
                .build();

        Artist result = SpotifyObjectMapper.toArtist(artistSimplified);

        assertEquals("testArtistName", result.getName());
        assertEquals("Not available", result.getExternal_url());
    }
}