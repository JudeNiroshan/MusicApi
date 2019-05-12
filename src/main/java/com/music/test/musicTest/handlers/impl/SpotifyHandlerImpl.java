package com.music.test.musicTest.handlers.impl;

import com.music.test.musicTest.exceptions.SpotifyException;
import com.music.test.musicTest.handlers.SpotifyHandler;
import com.music.test.musicTest.models.PlayListInfo;
import com.music.test.musicTest.services.SpotifyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SpotifyHandlerImpl implements SpotifyHandler {

    @Autowired
    SpotifyService spotifyService;

    private Logger logger = LoggerFactory.getLogger(SpotifyHandlerImpl.class);

    @Override
    public List<PlayListInfo> getSpotifyData() throws SpotifyException {

        try {
            return spotifyService.getPlayListInfoList();
        } catch (Exception e) {
            logger.error(e.getMessage(), e.getCause());
            throw new SpotifyException(e.getMessage(), e);
        }
    }

}
