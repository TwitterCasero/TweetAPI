package com.twittercasero.tweets.application.useCases;

import com.twittercasero.tweets.domain.entities.Tweet;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.function.BiFunction;

public interface GetTweetByOwnersUseCase extends BiFunction<List<String>, Pageable, List<Tweet>> {
}
