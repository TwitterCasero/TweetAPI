package com.twittercasero.tweets.application.useCases.impl;

import com.twittercasero.tweets.application.port.output.TweetOutputPort;
import com.twittercasero.tweets.application.useCases.GetTweetByOwnersUseCase;
import com.twittercasero.tweets.domain.entities.Tweet;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class GetTweetByOwnersUseCaseImpl implements GetTweetByOwnersUseCase {

    private final TweetOutputPort tweetOutputPort;

    public GetTweetByOwnersUseCaseImpl(TweetOutputPort tweetOutputPort) {
        this.tweetOutputPort = tweetOutputPort;
    }

    @Override
    public List<Tweet> apply(List<String> owners, Pageable pageable) {
        return tweetOutputPort.findByOwners(owners, pageable);
    }
}
