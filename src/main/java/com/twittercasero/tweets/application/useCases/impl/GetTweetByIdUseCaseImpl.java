package com.twittercasero.tweets.application.useCases.impl;

import com.twittercasero.tweets.application.port.output.TweetOutputPort;
import com.twittercasero.tweets.application.useCases.GetTweetByIdUseCase;
import com.twittercasero.tweets.domain.entities.Tweet;

public class GetTweetByIdUseCaseImpl implements GetTweetByIdUseCase {

    private final TweetOutputPort tweetOutputPort;

    public GetTweetByIdUseCaseImpl(TweetOutputPort tweetOutputPort) {
        this.tweetOutputPort = tweetOutputPort;
    }

    @Override
    public Tweet apply(String tweetId) {
        return tweetOutputPort.findById(tweetId);
    }
}
