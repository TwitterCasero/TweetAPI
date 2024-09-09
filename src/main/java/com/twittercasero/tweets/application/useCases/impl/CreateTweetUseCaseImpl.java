package com.twittercasero.tweets.application.useCases.impl;

import com.twittercasero.tweets.application.port.input.TweetInputPort;
import com.twittercasero.tweets.application.useCases.CreateTweetUseCase;
import com.twittercasero.tweets.domain.entities.Tweet;

public class CreateTweetUseCaseImpl implements CreateTweetUseCase {

    private final TweetInputPort tweetInputPort;

    public CreateTweetUseCaseImpl(TweetInputPort tweetInputPort) {
        this.tweetInputPort = tweetInputPort;
    }

    @Override
    public void accept(Tweet tweet) {
        tweetInputPort.save(tweet);
    }
}
