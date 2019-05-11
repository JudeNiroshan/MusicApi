package com.music.test.musicTest.services.impl;

import com.music.test.musicTest.models.Artist;
import com.music.test.musicTest.models.Playlist;
import com.music.test.musicTest.services.SpotifyService;
import com.music.test.musicTest.util.SpotifyObjectMapper;
import com.music.test.musicTest.util.SpotifyUtil;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.special.FeaturedPlaylists;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.PlaylistTrack;
import com.wrapper.spotify.requests.data.browse.GetListOfFeaturedPlaylistsRequest;
import com.wrapper.spotify.requests.data.playlists.GetPlaylistsTracksRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class SpotifyServiceImpl implements SpotifyService {

    @Autowired
    private SpotifyUtil spotifyUtil;


    @Override
    public List<Playlist> getFeaturedPlaylists() {
        final String accessToken = spotifyUtil.getApiToken();

        SpotifyApi spotifyApi = new SpotifyApi.Builder()
                .setAccessToken(accessToken)
                .build();
        GetListOfFeaturedPlaylistsRequest playlistsRequest = spotifyApi
                .getListOfFeaturedPlaylists()
                .limit(10)
                .build();
        try {
            FeaturedPlaylists featuredPlaylists = playlistsRequest.execute();

            return Stream.of(featuredPlaylists.getPlaylists().getItems())
                    .map(SpotifyObjectMapper::toPlaylist)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SpotifyWebApiException e) {
            e.printStackTrace();
        }

        return Collections.EMPTY_LIST;
    }

    @Override
    public List<Artist> getArtistsByPlaylistId(String playlistId) {
        final String accessToken = spotifyUtil.getApiToken();

        SpotifyApi spotifyApi = new SpotifyApi.Builder()
                .setAccessToken(accessToken)
                .build();

        GetPlaylistsTracksRequest playlistsTracksRequest = spotifyApi
                .getPlaylistsTracks(playlistId)
                .limit(10)
                .build();

        try {
            Paging<PlaylistTrack> playlistTrackPaging = playlistsTracksRequest.execute();
            return Stream.of(playlistTrackPaging.getItems())
                    .flatMap(playlistTrack -> Stream.of(playlistTrack.getTrack().getArtists()))
                    .map(SpotifyObjectMapper::toArtist)
                    .unordered()
                    .distinct()
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SpotifyWebApiException e) {
            e.printStackTrace();
        }
        return Collections.EMPTY_LIST;
    }
}
