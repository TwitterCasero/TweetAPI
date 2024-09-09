package com.twittercasero.tweets.application.useCases.impl;

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
public class AddLikeOrDislikeUseCaseImplTest {

    @Mock
    private TweetInputPort tweetInputPort;

    @Mock
    private TweetOutputPort tweetOutputPort;

    @InjectMocks
    private AddLikeOrDislikeUseCaseImpl addLikeOrDislikeUseCase;

    @Test
    void whenLikedIncrementLikes() {

        Tweet tweet = Tweet.builder()
                .id("123")
                .message("nice tweet")
                .likes(5)
                .build();

        when(tweetOutputPort.findById("someId")).thenReturn(tweet);

        addLikeOrDislikeUseCase.accept("someId", true);

        verify(tweetOutputPort).findById("someId");
        verify(tweetInputPort).save(tweet);
        assertEquals(6, tweet.getLikes());
    }

    @Test
    void whenDislikedDecrementLikes() {

        Tweet tweet = Tweet.builder()
                .id("123")
                .message("nice tweet")
                .likes(5)
                .build();

        when(tweetOutputPort.findById("someId")).thenReturn(tweet);

        addLikeOrDislikeUseCase.accept("someId", false);

        verify(tweetOutputPort).findById("someId");
        verify(tweetInputPort).save(tweet);
        assertEquals(4, tweet.getLikes());
    }

    @Test
    void whenDislikedWithZeroLikes() {
        Tweet tweet = Tweet.builder()
                .id("123")
                .message("nice tweet")
                .likes(0)
                .build();

        when(tweetOutputPort.findById("someId")).thenReturn(tweet);

        addLikeOrDislikeUseCase.accept("someId", false);

        verify(tweetOutputPort).findById("someId");
        verify(tweetInputPort).save(tweet);
        assertEquals(0, tweet.getLikes());
    }

    @Test
    void whenTweetNotFoundThrowException() {
        when(tweetOutputPort.findById("someId")).thenReturn(null);

        assertThrows(IllegalArgumentException.class, () -> {
            addLikeOrDislikeUseCase.accept("someId", true);
        });

        verify(tweetOutputPort).findById("someId");
        verifyNoInteractions(tweetInputPort);
    }
}
