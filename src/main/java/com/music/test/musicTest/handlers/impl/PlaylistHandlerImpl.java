package com.music.test.musicTest.handlers.impl;

import com.music.test.musicTest.exceptions.SpotifyException;
import com.music.test.musicTest.handlers.PlaylistHandler;
import com.music.test.musicTest.handlers.SpotifyHandler;
import com.music.test.musicTest.models.PlayListInfo;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

import static com.music.test.musicTest.common.SpotifyConstants.ERROR_RESPONSE_ROOT_NAME;
import static com.music.test.musicTest.common.SpotifyConstants.RESPONSE_ROOT_NAME;

@Component
public class PlaylistHandlerImpl implements PlaylistHandler {

    @Autowired
    SpotifyHandler spotifyHandler;

    @Override
    public JSONObject getPlaylistInfo() {

        try {
            return getResultAsJson(spotifyHandler.getSpotifyData());
        } catch (SpotifyException e) {
            return getErrorAsJson(e);
        }
    }

    private JSONObject getResultAsJson(List<PlayListInfo> playListInfos) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(RESPONSE_ROOT_NAME, playListInfos);

        return jsonObject;
    }

    private JSONObject getErrorAsJson(SpotifyException spotifyException) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(ERROR_RESPONSE_ROOT_NAME, spotifyException.getMessage());
        jsonObject.put(RESPONSE_ROOT_NAME, Collections.EMPTY_LIST);

        return jsonObject;
    }
}
