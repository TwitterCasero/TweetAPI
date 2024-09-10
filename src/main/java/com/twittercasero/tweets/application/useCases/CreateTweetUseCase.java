package com.twittercasero.tweets.application.useCases;

import com.twittercasero.tweets.domain.entities.Tweet;

import java.util.function.Consumer;

public interface CreateTweetUseCase extends Consumer<Tweet> {
}
