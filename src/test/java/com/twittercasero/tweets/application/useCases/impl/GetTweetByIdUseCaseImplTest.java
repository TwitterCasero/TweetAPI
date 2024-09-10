package com.twittercasero.tweets.application.useCases.impl;

import com.twittercasero.tweets.application.port.output.TweetOutputPort;
import com.twittercasero.tweets.domain.entities.Tweet;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetTweetByIdUseCaseImplTest {

    @Mock
    private TweetOutputPort tweetOutputPort;

    @InjectMocks
    private GetTweetByIdUseCaseImpl getTweetByIdUseCase;

    @Test
    void testApply_WhenTweetExists() {
        // Arrange
        String tweetId = "1";
        Tweet expectedTweet = Tweet.builder()
                .id(tweetId)
                .message("Test tweet")
                .build();

        when(tweetOutputPort.findById(tweetId)).thenReturn(expectedTweet);

        // Act
        Tweet result = getTweetByIdUseCase.apply(tweetId);

        // Assert
        assertEquals(expectedTweet, result);
        verify(tweetOutputPort).findById(tweetId);
    }

    @Test
    void testApply_WhenTweetDoesNotExist() {
        // Arrange
        String tweetId = "2";
        when(tweetOutputPort.findById(tweetId)).thenReturn(null);

        // Act
        Tweet result = getTweetByIdUseCase.apply(tweetId);

        // Assert
        assertNull(result);
        verify(tweetOutputPort).findById(tweetId);
    }
}
