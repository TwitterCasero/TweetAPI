package com.twittercasero.tweets.application.useCases.impl;

import com.twittercasero.tweets.application.port.output.TweetOutputPort;
import com.twittercasero.tweets.application.useCases.GetTweetByMentionsUseCase;
import com.twittercasero.tweets.domain.entities.Tweet;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class GetTweetByMentionsUseCaseImpl implements GetTweetByMentionsUseCase {

    private final TweetOutputPort tweetOutputPort;

    public GetTweetByMentionsUseCaseImpl(TweetOutputPort tweetOutputPort) {
        this.tweetOutputPort = tweetOutputPort;
    }

    @Override
    public List<Tweet> apply(String mentions, Pageable pageable) {
        return tweetOutputPort.findByMention(mentions, pageable);
    }
}
