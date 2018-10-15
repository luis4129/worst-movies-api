package com.texoit.worstmovies.exception;

public class WinnerDeleteException extends Exception {

    public WinnerDeleteException() {
        super("Winners cannot be deleted.");
    }
}
