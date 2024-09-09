package com.twittercasero.tweets.application.port.input;

import com.twittercasero.tweets.domain.entities.Tweet;

public interface TweetInputPort {
    void save(Tweet tweet);
}
