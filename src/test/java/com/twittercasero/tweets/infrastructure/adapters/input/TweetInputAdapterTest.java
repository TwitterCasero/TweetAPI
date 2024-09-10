package com.twittercasero.tweets.infrastructure.adapters.input;

import com.twittercasero.tweets.domain.entities.Tweet;
import com.twittercasero.tweets.infrastructure.repositories.TweetRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class TweetInputAdapterTest {

    @Mock
    private TweetRepository tweetRepository;

    @InjectMocks
    private TweetInputAdapter tweetInputAdapter;

    @Test
    void whenSaveTweetThenInvokeRepositorySave() {

        Tweet tweet = Tweet.builder().build();

        tweetInputAdapter.save(tweet);

        verify(tweetRepository).save(tweet);
    }
}
