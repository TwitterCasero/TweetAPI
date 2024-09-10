package com.twittercasero.tweets.application.useCases;

import com.twittercasero.tweets.domain.entities.Tweet;

import java.util.function.Function;

public interface GetTweetByIdUseCase extends Function<String, Tweet> {
}
