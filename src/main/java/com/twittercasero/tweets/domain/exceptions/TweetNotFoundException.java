package com.twittercasero.tweets.domain.exceptions;

public class TweetNotFoundException extends RuntimeException {
    public TweetNotFoundException(String message) {
        super(message);
    }
}
