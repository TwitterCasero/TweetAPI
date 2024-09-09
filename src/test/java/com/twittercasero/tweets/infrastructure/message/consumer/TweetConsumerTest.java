package com.twittercasero.tweets.infrastructure.message.consumer;

import com.twittercasero.tweets.application.dto.AddLikeDto;
import com.twittercasero.tweets.application.dto.AddReplayDto;
import com.twittercasero.tweets.application.useCases.AddLikeOrDislikeUseCase;
import com.twittercasero.tweets.application.useCases.AddReplyUseCase;
import com.twittercasero.tweets.application.useCases.AddRetweetUseCase;
import com.twittercasero.tweets.application.useCases.CreateTweetUseCase;
import com.twittercasero.tweets.domain.entities.Tweet;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class TweetConsumerTest {

    @Mock
    private CreateTweetUseCase createTweetUseCase;

    @Mock
    private AddLikeOrDislikeUseCase addLikeOrDislikeUseCase;

    @Mock
    private AddRetweetUseCase addRetweetUseCase;

    @Mock
    private AddReplyUseCase addReplyUseCase;

    @InjectMocks
    private TweetConsumer tweetConsumer;

    @Test
    void testReceiveMessageToNewTweet() {
        Tweet tweet = Tweet.builder().build();
        tweetConsumer.receiveMessageToNewTweet(tweet);
        verify(createTweetUseCase).accept(tweet);
    }

    @Test
    void testReceiveMessageToAddLike() {
        AddLikeDto addLikeDto = AddLikeDto.builder()
                .tweetId("123")
                .like(true)
                .build();
        tweetConsumer.receiveMessageToAddLike(addLikeDto);
        verify(addLikeOrDislikeUseCase).accept(addLikeDto.getTweetId(), addLikeDto.getLike());
    }

    @Test
    void testReceiveMessageToAddReply() {
        AddReplayDto addReplyDto = AddReplayDto.builder()
                .tweetId("123")
                .reply(Tweet.Reply.builder()
                        .owner("owner")
                        .message("Nice Tweet")
                        .build())
                .build();
        tweetConsumer.receiveMessageToAddReply(addReplyDto);
        verify(addReplyUseCase).accept(addReplyDto.getTweetId(), addReplyDto.getReply());
    }

    @Test
    void testReceiveMessageToAddRetweet() {
        String tweetId = "123";
        tweetConsumer.receiveMessageToAddRetweet(tweetId);
        verify(addRetweetUseCase).accept(tweetId);
    }
}
