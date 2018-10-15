package com.texoit.worstmovies.exception;

public class EmptySearchException extends Exception {

    public EmptySearchException() {
        super("No data was found.");
    }
}
