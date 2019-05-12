package com.music.test.musicTest.services;

import com.music.test.musicTest.models.PlayListInfo;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;

import java.io.IOException;
import java.util.List;

public interface SpotifyService {

    List<PlayListInfo> getPlayListInfoList() throws IOException, SpotifyWebApiException;
}
