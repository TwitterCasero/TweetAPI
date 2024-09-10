package com.twittercasero.tweets.application.useCases.impl;

import com.twittercasero.tweets.application.dto.AddRetweetDTO;
import com.twittercasero.tweets.application.port.input.TweetInputPort;
import com.twittercasero.tweets.application.port.output.TweetOutputPort;
import com.twittercasero.tweets.application.useCases.AddRetweetUseCase;
import com.twittercasero.tweets.domain.entities.Tweet;

public class AddRetweetUseCaseImpl implements AddRetweetUseCase {

    private final TweetInputPort tweetInputPort;

    private final TweetOutputPort tweetOutputPort;

    public AddRetweetUseCaseImpl(TweetInputPort tweetInputPort, TweetOutputPort tweetOutputPort) {
        this.tweetInputPort = tweetInputPort;
        this.tweetOutputPort = tweetOutputPort;
    }

    @Override
    public void accept(AddRetweetDTO addRetweetDTO) {

        String tweetId = addRetweetDTO.getTweetId();
        Tweet currentTweet = tweetOutputPort.findById(tweetId);
        if (currentTweet == null) {
            throw new IllegalArgumentException("Tweet not found with ID: " + tweetId);
        }

        currentTweet.setRetweets(currentTweet.getRetweets() + 1);

        Tweet tweetShare = Tweet.builder()
                .message(currentTweet.getMessage())
                .owner(addRetweetDTO.getNickName())
                .mentions(currentTweet.getMentions())
                .hashtags(currentTweet.getHashtags())
                .build();

        tweetInputPort.save(currentTweet);
        tweetInputPort.save(tweetShare);

    }
}
