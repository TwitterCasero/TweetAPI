package com.twittercasero.tweets.infrastructure.message.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.twittercasero.tweets.application.dto.AddLikeDto;
import com.twittercasero.tweets.application.useCases.AddLikeOrDislikeUseCase;
import com.twittercasero.tweets.application.useCases.AddReplyUseCase;
import com.twittercasero.tweets.application.useCases.AddRetweetUseCase;
import com.twittercasero.tweets.application.useCases.CreateTweetUseCase;
import com.twittercasero.tweets.domain.entities.Tweet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class TweetConsumerTest {

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

    private final ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        // Puedes configurar comportamientos adicionales aquí si es necesario
    }

    @Test
    void testReceiveMessageToNewTweet() throws Exception {
        String json = "{\"id\":\"1\", \"message\":\"Hello, world!\", \"owner\":\"user123\"}";
        Tweet tweet = mapper.readValue(json, Tweet.class);  // Simulando la deserialización para el test

        tweetConsumer.receiveMessageToNewTweet(json);

        verify(createTweetUseCase).accept(any(Tweet.class));
    }

    @Test
    void testReceiveMessageToAddLike() throws Exception {
        String json = "{\"tweetId\":\"1\", \"like\":true}";
        AddLikeDto addLikeDto = mapper.readValue(json, AddLikeDto.class);

        tweetConsumer.receiveMessageToAddLike(json);

        verify(addLikeOrDislikeUseCase).accept(addLikeDto.getTweetId(), addLikeDto.getLike());
    }

    @Test
    void testReceiveMessageToAddReply() throws Exception {
        String json = "{\"tweetId\":\"1\", \"nickName\":\"user1\", \"message\":\"best\"}}";

        tweetConsumer.receiveMessageToAddReply(json);

        verify(addReplyUseCase).accept(any());
    }

    @Test
    void testReceiveMessageToAddRetweet() {
        String json = "{\"tweetId\":\"1\", \"nickName\":\"user1\"}";

        tweetConsumer.receiveMessageToAddRetweet(json);

        verify(addRetweetUseCase).accept(any());
    }
}
