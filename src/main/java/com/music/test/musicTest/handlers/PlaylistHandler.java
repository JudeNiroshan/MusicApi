package com.music.test.musicTest.handlers;


import org.json.simple.JSONObject;

/**
 * Handle playlist related functions
 */
public interface PlaylistHandler {

    /**
     * Provide playlist information along with artist information
     * @return
     */
    JSONObject getPlaylistInfo();
}
