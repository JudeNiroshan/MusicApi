package com.music.test.musicTest.config;

import com.music.test.musicTest.services.SpotifyService;
import com.music.test.musicTest.services.impl.SpotifyServiceImpl;
import com.music.test.musicTest.util.SpotifyUtil;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class MockContextConfiguration {

    @Value("${spotify.clientid}")
    private String testClientId;

    @Value("${spotify.clientsecret}")
    private String testClientSecret;

    @Bean
    SpotifyUtil getSpotifyUtil(){
        return new SpotifyUtil(testClientId, testClientSecret);
    }

    @Bean
    SpotifyService getSpotifyService(){
        return new SpotifyServiceImpl(getSpotifyUtil());
    }

}
