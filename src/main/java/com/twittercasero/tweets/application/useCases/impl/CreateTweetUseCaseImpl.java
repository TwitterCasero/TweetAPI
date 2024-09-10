package com.twittercasero.tweets.application.useCases.impl;

import com.twittercasero.tweets.application.port.input.TweetInputPort;
import com.twittercasero.tweets.application.useCases.CreateTweetUseCase;
import com.twittercasero.tweets.domain.entities.Tweet;

import java.util.List;

import static com.twittercasero.tweets.infrastructure.utils.TextExtractorUtil.extractStrings;

public class CreateTweetUseCaseImpl implements CreateTweetUseCase {

    private final TweetInputPort tweetInputPort;

    public CreateTweetUseCaseImpl(TweetInputPort tweetInputPort) {
        this.tweetInputPort = tweetInputPort;
    }

    @Override
    public void accept(Tweet tweet) {

        List<String> hashtags = extractStrings(tweet.getMessage(), "#\\w+");
        List<String> mentions = extractStrings(tweet.getMessage(), "@\\w+");
        tweet.getHashtags().addAll(hashtags);
        tweet.getMentions().addAll(mentions);

        tweetInputPort.save(tweet);
    }
}
