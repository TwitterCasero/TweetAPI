package com.twittercasero.tweets.application.useCases;

import com.twittercasero.tweets.domain.entities.Tweet;

import java.util.function.BiConsumer;

public interface AddReplyUseCase extends BiConsumer<String, Tweet.Reply> {
}
