package com.twittercasero.tweets.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AddReplayDto {

    private String tweetId;
    private String nickName;
    private String message;
}
