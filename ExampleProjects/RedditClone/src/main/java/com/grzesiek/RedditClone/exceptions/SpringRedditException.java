package com.grzesiek.RedditClone.exceptions;



public class SpringRedditException extends RuntimeException {
    public SpringRedditException(String exMessage) {
        super(exMessage);
    }
}
