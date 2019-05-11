package com.music.test.musicTest.util;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.credentials.ClientCredentials;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class SpotifyUtil {
    private SpotifyApi getApiClient() {

        return new SpotifyApi.Builder()
                .setClientId("ca49f472b8ac4c43b921e343039aab41")
                .setClientSecret("763bdae06dda4819a2e171e1dceaa43b")
                .build();
    }

    private ClientCredentials getClientCredentials() throws IOException, SpotifyWebApiException {
        return getApiClient().clientCredentials().build().execute();
    }

    public String getApiToken() {
        try {
            return getClientCredentials().getAccessToken();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SpotifyWebApiException e) {
            e.printStackTrace();
        }
        return "";
    }
}
