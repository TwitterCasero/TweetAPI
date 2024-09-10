package com.twittercasero.tweets.application.useCases.impl;

import com.twittercasero.tweets.application.dto.AddRetweetDTO;
import com.twittercasero.tweets.application.port.input.TweetInputPort;
import com.twittercasero.tweets.application.port.output.TweetOutputPort;
import com.twittercasero.tweets.domain.entities.Tweet;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AddRetweetUseCaseImplTest {

    @Mock
    private TweetInputPort tweetInputPort;

    @Mock
    private TweetOutputPort tweetOutputPort;

    @InjectMocks
    private AddRetweetUseCaseImpl addRetweetUseCase;

    @Test
    void whenRetweetAddedThenReturnUpdatedTweet() {

        Tweet tweet = Tweet.builder()
                .id("123")
                .message("nice tweet")
                .retweets(5)
                .build();
        when(tweetOutputPort.findById("tweetId")).thenReturn(tweet);

        addRetweetUseCase.accept(AddRetweetDTO.builder().tweetId("tweetId").build());

        verify(tweetOutputPort).findById("tweetId");
        verify(tweetInputPort).save(tweet);
        assertEquals(6, tweet.getRetweets());
    }

    @Test
    void whenTweetNotFoundThenThrowException() {

        when(tweetOutputPort.findById("tweetId")).thenReturn(null);

        assertThrows(IllegalArgumentException.class, () -> {
            addRetweetUseCase.accept(AddRetweetDTO.builder().tweetId("tweetId").build());
        });

        verify(tweetOutputPort).findById("tweetId");
        verifyNoInteractions(tweetInputPort);
    }
}
