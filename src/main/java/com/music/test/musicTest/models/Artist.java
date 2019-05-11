package com.music.test.musicTest.models;

import java.util.Objects;

public class Artist {

    private String name;
    private String external_url;

    public Artist(String name, String externalUrl) {
        this.name = name;
        this.external_url = externalUrl;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Artist artist = (Artist) o;
        return Objects.equals(name, artist.name) &&
                Objects.equals(external_url, artist.external_url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, external_url);
    }

    public void setExternal_url(String external_url) {
        this.external_url = external_url;
    }
}
