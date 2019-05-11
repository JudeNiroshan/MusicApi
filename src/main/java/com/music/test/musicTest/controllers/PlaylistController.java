package com.music.test.musicTest.controllers;

import com.music.test.musicTest.handlers.PlaylistHandler;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlaylistController {

    @Autowired
    private PlaylistHandler playlistHandler;


    @RequestMapping("/info")
    public JSONObject info() {
        return playlistHandler.getPlaylistInfo();
    }
}
