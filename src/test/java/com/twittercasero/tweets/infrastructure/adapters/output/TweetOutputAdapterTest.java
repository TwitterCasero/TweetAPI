package com.twittercasero.tweets.infrastructure.adapters.output;

import com.twittercasero.tweets.domain.entities.Tweet;
import com.twittercasero.tweets.domain.exceptions.TweetNotFoundException;
import com.twittercasero.tweets.infrastructure.repositories.TweetRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TweetOutputAdapterTest {

    @Mock
    private TweetRepository tweetRepository;

    @InjectMocks
    private TweetOutputAdapter tweetOutputAdapter;

    @Test
    void whenFindByIdThenSuccess() {
        Tweet tweet = Tweet.builder().build();
        when(tweetRepository.findById("1")).thenReturn(Optional.of(tweet));

        Tweet result = tweetOutputAdapter.findById("1");

        assertEquals(tweet, result);
        verify(tweetRepository).findById("1");
    }

    @Test
    void whenFindByIdThenNotFound() {
        when(tweetRepository.findById("1")).thenReturn(Optional.empty());

        assertThrows(TweetNotFoundException.class, () -> tweetOutputAdapter.findById("1"));
    }

    @Test
    void whenFindByHashtagThenReturnList() {
        List<Tweet> expectedTweets = List.of(Tweet.builder().build());
        Pageable pageable = PageRequest.of(0, 10);
        when(tweetRepository.findByHashtagsContaining("hash", pageable))
                .thenReturn(new PageImpl<>(expectedTweets));

        List<Tweet> results = tweetOutputAdapter.findByHashtag("hash", pageable);

        assertEquals(expectedTweets, results);
        verify(tweetRepository).findByHashtagsContaining("hash", pageable);
    }

    @Test
    void whenFindByMentionThenReturnList() {
        List<Tweet> expectedTweets = List.of(Tweet.builder().build());
        Pageable pageable = PageRequest.of(0, 10);
        when(tweetRepository.findByMentionsContaining("user", pageable))
                .thenReturn(new PageImpl<>(expectedTweets));

        List<Tweet> results = tweetOutputAdapter.findByMention("user", pageable);

        assertEquals(expectedTweets, results);
        verify(tweetRepository).findByMentionsContaining("user", pageable);
    }

    @Test
    void whenFindByOwnersThenReturnList() {
        List<Tweet> expectedTweets = List.of(Tweet.builder().build());
        List<String> owners = Arrays.asList("user1", "user2");
        Pageable pageable = PageRequest.of(0, 10);
        when(tweetRepository.findByOwnerInOrderByPostedDesc(owners, pageable))
                .thenReturn(new PageImpl<>(expectedTweets));

        List<Tweet> results = tweetOutputAdapter.findByOwners(owners, pageable);

        assertEquals(expectedTweets, results);
        verify(tweetRepository).findByOwnerInOrderByPostedDesc(owners, pageable);
    }
}
