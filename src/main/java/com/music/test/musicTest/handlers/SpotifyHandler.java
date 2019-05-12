package com.music.test.musicTest.handlers;

import com.music.test.musicTest.exceptions.SpotifyException;
import com.music.test.musicTest.models.PlayListInfo;

import java.util.List;

public interface SpotifyHandler {

    List<PlayListInfo> getSpotifyData() throws SpotifyException;
}
