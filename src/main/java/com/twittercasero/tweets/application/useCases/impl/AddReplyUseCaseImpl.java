package com.twittercasero.tweets.application.useCases.impl;

import com.twittercasero.tweets.application.dto.AddReplayDto;
import com.twittercasero.tweets.application.port.input.TweetInputPort;
import com.twittercasero.tweets.application.port.output.TweetOutputPort;
import com.twittercasero.tweets.application.useCases.AddReplyUseCase;
import com.twittercasero.tweets.domain.entities.Tweet;

import java.util.Date;

public class AddReplyUseCaseImpl implements AddReplyUseCase {

    private final TweetInputPort tweetInputPort;

    private final TweetOutputPort tweetOutputPort;

    public AddReplyUseCaseImpl(TweetInputPort tweetInputPort, TweetOutputPort tweetOutputPort) {
        this.tweetInputPort = tweetInputPort;
        this.tweetOutputPort = tweetOutputPort;
    }

    @Override
    public void accept(AddReplayDto addReplayDto) {
        String tweetId = addReplayDto.getTweetId();
        Tweet currentTweet = tweetOutputPort.findById(tweetId);
        if (currentTweet == null) {
            throw new IllegalArgumentException("Tweet not found with ID: " + tweetId);
        }

        currentTweet.getReplies().add(Tweet.Reply.builder()
                .owner(addReplayDto.getNickName())
                .message(addReplayDto.getMessage())
                .posted(new Date())
                .build());

        tweetInputPort.save(currentTweet);

    }

}
