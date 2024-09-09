package com.twittercasero.tweets.application.useCases.impl;

import com.twittercasero.tweets.application.port.output.TweetOutputPort;
import com.twittercasero.tweets.application.useCases.GetTweetByHashtagUseCase;
import com.twittercasero.tweets.domain.entities.Tweet;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class GetTweetByHashtagUseCaseImpl implements GetTweetByHashtagUseCase {

    private final TweetOutputPort tweetOutputPort;

    public GetTweetByHashtagUseCaseImpl(TweetOutputPort tweetOutputPort) {
        this.tweetOutputPort = tweetOutputPort;
    }

    @Override
    public List<Tweet> apply(String hashtags, Pageable pageable) {
        return tweetOutputPort.findByHashtag(hashtags, pageable);
    }
}
