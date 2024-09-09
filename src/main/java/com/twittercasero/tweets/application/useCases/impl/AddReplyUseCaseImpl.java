package com.twittercasero.tweets.application.useCases.impl;

import com.twittercasero.tweets.application.port.input.TweetInputPort;
import com.twittercasero.tweets.application.port.output.TweetOutputPort;
import com.twittercasero.tweets.application.useCases.AddReplyUseCase;
import com.twittercasero.tweets.domain.entities.Tweet;

public class AddReplyUseCaseImpl implements AddReplyUseCase {

    private final TweetInputPort tweetInputPort;

    private final TweetOutputPort tweetOutputPort;

    public AddReplyUseCaseImpl(TweetInputPort tweetInputPort, TweetOutputPort tweetOutputPort) {
        this.tweetInputPort = tweetInputPort;
        this.tweetOutputPort = tweetOutputPort;
    }

    @Override
    public void accept(String tweetId, Tweet.Reply reply) {
        Tweet currentTweet = tweetOutputPort.findById(tweetId);
        if (currentTweet == null) {
            throw new IllegalArgumentException("Tweet not found with ID: " + tweetId);
        }

        currentTweet.getReplies().add(reply);

        tweetInputPort.save(currentTweet);

    }

}
