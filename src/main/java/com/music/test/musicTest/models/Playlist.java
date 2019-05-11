package com.music.test.musicTest.models;


public class Playlist {
    private String id;
    private String name;
    private String external_url;

    public Playlist(String id, String name, String external_url) {
        this.id = id;
        this.name = name;
        this.external_url = external_url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
}
