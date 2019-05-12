package com.music.test.musicTest.controllers;

import com.music.test.musicTest.handlers.PlaylistHandler;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Main entry point for all http requests for the API
 * @author JudeNiroshan
 */
@RestController
public class MainController {

    @Autowired
    private PlaylistHandler playlistHandler;

    /**
     * Returns featured playlist information with their artist information
     * @return JSONObject json represent the response
     */
    @RequestMapping(path = "/info", method = RequestMethod.GET, produces = "application/json")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Returns featured playlist information with their artist information")})
    public JSONObject info() {
        return playlistHandler.getPlaylistInfo();
    }
}
