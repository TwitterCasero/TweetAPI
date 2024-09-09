package com.twittercasero.tweets.application.useCases;

import com.twittercasero.tweets.domain.entities.Tweet;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.function.BiFunction;

public interface GetTweetByMentionsUseCase extends BiFunction<String, Pageable, List<Tweet>> {
}
