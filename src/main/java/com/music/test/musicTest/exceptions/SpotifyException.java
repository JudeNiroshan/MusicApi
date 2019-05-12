package com.music.test.musicTest.exceptions;

/**
 * Wrapper exception class to identify exceptions that may occur
 * while communicating with spotify API
 * @author JudeNiroshan
 */
public class SpotifyException extends Exception {

    public SpotifyException(String message) {
        super(message);
    }

    public SpotifyException(String message, Throwable cause) {
        super(message, cause);
    }
}
