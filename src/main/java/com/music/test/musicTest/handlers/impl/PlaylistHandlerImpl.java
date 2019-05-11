package com.music.test.musicTest.handlers.impl;

import com.music.test.musicTest.handlers.PlaylistHandler;
import com.music.test.musicTest.models.Artist;
import com.music.test.musicTest.models.PlayListInfo;
import com.music.test.musicTest.models.Playlist;
import com.music.test.musicTest.services.SpotifyService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static com.music.test.musicTest.common.SpotifyConstants.RESPONSE_ROOT_NAME;

@Component
public class PlaylistHandlerImpl implements PlaylistHandler {

    @Autowired
    SpotifyService spotifyService;

    @Override
    public JSONObject getPlaylistInfo() {
        List<PlayListInfo> playListInfos = spotifyService.getFeaturedPlaylists()
                .parallelStream()
                .map(this::generatePlaylistInfo)
                .collect(Collectors.toList());

        return getResultAsJson(playListInfos);
    }

    private PlayListInfo generatePlaylistInfo(Playlist playlist){
        List<Artist> artists = spotifyService.getArtistsByPlaylistId(playlist.getId());
        return new PlayListInfo(playlist, artists);
    }

    private JSONObject getResultAsJson(List<PlayListInfo> playListInfos){
        JSONObject JSONObject = new JSONObject();
        JSONObject.put(RESPONSE_ROOT_NAME, playListInfos);

        return JSONObject;
    }
}
