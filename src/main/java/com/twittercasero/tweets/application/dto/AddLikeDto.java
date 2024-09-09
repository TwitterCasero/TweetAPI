package com.twittercasero.tweets.application.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AddLikeDto {

    private String tweetId;
    private Boolean like;
}
