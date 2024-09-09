package com.twittercasero.tweets.application.dto;

import com.twittercasero.tweets.domain.entities.Tweet;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AddReplayDto {

    private String tweetId;
    private Tweet.Reply reply;
}
