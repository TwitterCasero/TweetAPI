package com.twittercasero.tweets.application.useCases.impl;

import com.twittercasero.tweets.application.port.input.TweetInputPort;
import com.twittercasero.tweets.domain.entities.Tweet;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class CreateTweetUseCaseImplTest {

    @Mock
    private TweetInputPort tweetInputPort;

    @InjectMocks
    private CreateTweetUseCaseImpl createTweetUseCase;

    @Test
    void whenCreateTweetThenSaveTweet() {
        Tweet tweet = Tweet.builder()
                .id("123")
                .owner("user1")
                .message("nice tweet")
                .build();

        createTweetUseCase.accept(tweet);

        verify(tweetInputPort).save(tweet);
    }

}
