package com.twittercasero.tweets.application.useCases.impl;

import com.twittercasero.tweets.application.dto.AddReplayDto;
import com.twittercasero.tweets.application.port.input.TweetInputPort;
import com.twittercasero.tweets.application.port.output.TweetOutputPort;
import com.twittercasero.tweets.domain.entities.Tweet;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AddReplyUseCaseImplTest {

    @Mock
    private TweetInputPort tweetInputPort;

    @Mock
    private TweetOutputPort tweetOutputPort;

    @InjectMocks
    private AddReplyUseCaseImpl addReplyUseCase;

    @Test
    void whenReplyAddedThenReturnUpdatedTweet() {

        Tweet tweet = Tweet.builder().build();

        when(tweetOutputPort.findById("tweetId")).thenReturn(tweet);

        addReplyUseCase.accept(AddReplayDto.builder().tweetId("tweetId").build());

        verify(tweetOutputPort).findById("tweetId");
        verify(tweetInputPort).save(tweet);
    }

    @Test
    void whenTweetNotFoundThenThrowException() {

        when(tweetOutputPort.findById("tweetId")).thenReturn(null);

        assertThrows(IllegalArgumentException.class, () -> {

            addReplyUseCase.accept(AddReplayDto.builder().tweetId("tweetId").build());
        });

        verify(tweetOutputPort).findById("tweetId");
        verifyNoInteractions(tweetInputPort);
    }

}
