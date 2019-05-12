package com.music.test.musicTest.services.impl;

import com.music.test.musicTest.config.MockContextConfiguration;
import com.music.test.musicTest.models.PlayListInfo;
import com.music.test.musicTest.services.SpotifyService;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes= MockContextConfiguration.class)
public class SpotifyServiceImplTest {

    @Autowired
    SpotifyService spotifyService;

    @Test
    public void getPlayListInfoListTest() {
        List<PlayListInfo> result = Collections.EMPTY_LIST;
        try {
            result = spotifyService.getPlayListInfoList();
        } catch (IOException | SpotifyWebApiException e) {
            fail(e.getMessage());
        }
        assertFalse(result.isEmpty());
    }
}