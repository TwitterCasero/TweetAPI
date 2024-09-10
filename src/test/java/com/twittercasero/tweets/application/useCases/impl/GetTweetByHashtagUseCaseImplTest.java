package com.twittercasero.tweets.application.useCases.impl;

import com.twittercasero.tweets.application.port.output.TweetOutputPort;
import com.twittercasero.tweets.domain.entities.Tweet;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetTweetByHashtagUseCaseImplTest {

    @Mock
    private TweetOutputPort tweetOutputPort;

    @InjectMocks
    private GetTweetByHashtagUseCaseImpl getTweetByHashtagUseCase;

    @Test
    void whenApplyIsCalledThenReturnTweetsList() {

        List<Tweet> expectedTweets = List.of(Tweet.builder().build(), Tweet.builder().build());
        Pageable pageable = PageRequest.of(0, 2);
        when(tweetOutputPort.findByHashtag("tech", pageable)).thenReturn(expectedTweets);

        List<Tweet> resultTweets = getTweetByHashtagUseCase.apply("tech", pageable);

        verify(tweetOutputPort).findByHashtag("tech", pageable);
        assertEquals(expectedTweets, resultTweets, "The tweets returned should match the expected ones.");
    }
}
