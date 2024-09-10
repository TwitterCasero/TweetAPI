package com.twittercasero.tweets.application.useCases.impl;

import com.twittercasero.tweets.application.port.input.TweetInputPort;
import com.twittercasero.tweets.application.port.output.TweetOutputPort;
import com.twittercasero.tweets.application.useCases.AddLikeOrDislikeUseCase;
import com.twittercasero.tweets.domain.entities.Tweet;

public class AddLikeOrDislikeUseCaseImpl implements AddLikeOrDislikeUseCase {

    private final TweetInputPort tweetInputPort;

    private final TweetOutputPort tweetOutputPort;

    public AddLikeOrDislikeUseCaseImpl(TweetInputPort tweetInputPort, TweetOutputPort tweetOutputPort) {
        this.tweetInputPort = tweetInputPort;
        this.tweetOutputPort = tweetOutputPort;
    }

    @Override
    public void accept(String tweetId, Boolean liked) {

        Tweet currentTweet = tweetOutputPort.findById(tweetId);
        if (currentTweet == null) {
            throw new IllegalArgumentException("Tweet not found with ID: " + tweetId);
        }

        if (liked) {
            currentTweet.setLikes(currentTweet.getLikes() + 1);
        } else {
            int newLikeCount = currentTweet.getLikes() - 1;
            currentTweet.setLikes(Math.max(0, newLikeCount));
        }

        tweetInputPort.save(currentTweet);
    }

}
