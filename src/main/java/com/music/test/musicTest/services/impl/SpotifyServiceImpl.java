package com.music.test.musicTest.services.impl;

import com.music.test.musicTest.models.Artist;
import com.music.test.musicTest.models.PlayListInfo;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class SpotifyServiceImpl implements SpotifyService {

    @Autowired
    private SpotifyUtil spotifyUtil;

    private Logger logger = LoggerFactory.getLogger(SpotifyServiceImpl.class);

    @Override
    public List<PlayListInfo> getPlayListInfoList() throws IOException, SpotifyWebApiException {
        SpotifyApi spotifyApiClient = spotifyUtil.getSpotifyApiClient();

        FeaturedPlaylists featuredPlaylists = getPlaylistRequest(spotifyApiClient).execute();

        return Stream.of(featuredPlaylists.getPlaylists().getItems())
                .parallel()
                .map(SpotifyObjectMapper::toPlaylist)
                .map(pl -> {
                    try {
                        return populatePlayListInfo(pl, spotifyApiClient);
                    } catch (IOException | SpotifyWebApiException e) {
                        logger.debug(e.getMessage(), e.getCause());
                    }
                    return null;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    private PlayListInfo populatePlayListInfo(Playlist playlist, SpotifyApi spotifyApi) throws IOException, SpotifyWebApiException {

        Paging<PlaylistTrack> playlistTrackPaging = getTrackRequest(playlist.getId(), spotifyApi).execute();

        List<Artist> artistList = Stream.of(playlistTrackPaging.getItems())
                .flatMap(playlistTrack -> Stream.of(playlistTrack.getTrack().getArtists()))
                .map(SpotifyObjectMapper::toArtist)
                .unordered()
                .distinct()
                .collect(Collectors.toList());

        return new PlayListInfo(playlist, artistList);
    }

    private GetListOfFeaturedPlaylistsRequest getPlaylistRequest(SpotifyApi apiClient){
        return apiClient
                .getListOfFeaturedPlaylists()
                .limit(10)
                .build();
    }

    private GetPlaylistsTracksRequest getTrackRequest(String playlistId, SpotifyApi apiClient){
        return apiClient
                .getPlaylistsTracks(playlistId)
                .limit(10)
                .build();
    }
}
