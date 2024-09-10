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

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetTweetByOwnersUseCaseImplTest {

    @Mock
    private TweetOutputPort tweetOutputPort;

    @InjectMocks
    private GetTweetByOwnersUseCaseImpl getTweetByOwnersUseCase;

    @Test
    void whenApplyIsCalled_thenReturnTweetsList() {

        List<String> owners = Arrays.asList("user1", "user2");
        List<Tweet> expectedTweets = List.of(Tweet.builder().build(), Tweet.builder().build());
        Pageable pageable = PageRequest.of(0, 2);
        when(tweetOutputPort.findByOwners(owners, pageable)).thenReturn(expectedTweets);

        List<Tweet> resultTweets = getTweetByOwnersUseCase.apply(owners, pageable);

        verify(tweetOutputPort).findByOwners(owners, pageable);
        assertEquals(expectedTweets, resultTweets, "The tweets returned should match the expected ones.");
    }
}
