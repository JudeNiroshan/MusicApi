package com.music.test.musicTest.models;

import org.springframework.lang.NonNull;

import java.util.List;

public class PlayListInfo {
    private String name;
    private String external_url;
    private List<Artist> artists;

    public PlayListInfo(@NonNull String name,@NonNull String external_url, List<Artist> artists) {
        this.name = name;
        this.external_url = external_url;
        this.artists = artists;
    }

    public PlayListInfo(@NonNull Playlist playlist, List<Artist> artists) {
        this.name = playlist.getName();
        this.external_url = playlist.getExternal_url();
        this.artists = artists;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExternal_url() {
        return external_url;
    }

    public void setExternal_url(String external_url) {
        this.external_url = external_url;
    }

    public List<Artist> getArtists() {
        return artists;
    }

    public void setArtists(List<Artist> artists) {
        this.artists = artists;
    }
}
