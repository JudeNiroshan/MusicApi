package com.music.test.musicTest.util;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.credentials.ClientCredentials;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class SpotifyUtil {

    @Value("${spotify.clientid}")
    private String clientId;

    @Value("${spotify.clientsecret}")
    private String clientSecret;

    private SpotifyApi getApiClient() {

        return new SpotifyApi.Builder()
                .setClientId(clientId)
                .setClientSecret(clientSecret)
                .build();
    }

    private ClientCredentials getClientCredentials() throws IOException, SpotifyWebApiException {
        return getApiClient()
                .clientCredentials()
                .build()
                .execute();
    }

    private String getApiToken() throws IOException, SpotifyWebApiException {
        return getClientCredentials().getAccessToken();
    }

    public SpotifyApi getSpotifyApiClient() throws IOException, SpotifyWebApiException {

        return new SpotifyApi.Builder()
                .setAccessToken(getApiToken())
                .build();
    }
}
