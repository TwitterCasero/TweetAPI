package com.twittercasero.tweets.infrastructure.message.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.twittercasero.tweets.application.dto.AddLikeDto;
import com.twittercasero.tweets.application.dto.AddReplayDto;
import com.twittercasero.tweets.application.dto.AddRetweetDTO;
import com.twittercasero.tweets.application.useCases.AddLikeOrDislikeUseCase;
import com.twittercasero.tweets.application.useCases.AddReplyUseCase;
import com.twittercasero.tweets.application.useCases.AddRetweetUseCase;
import com.twittercasero.tweets.application.useCases.CreateTweetUseCase;
import com.twittercasero.tweets.domain.entities.Tweet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class TweetConsumer {

    @Autowired
    private CreateTweetUseCase createTweetUseCase;

    @Autowired
    private AddLikeOrDislikeUseCase addLikeOrDislikeUseCase;

    @Autowired
    private AddRetweetUseCase addRetweetUseCase;

    @Autowired
    private AddReplyUseCase addReplyUseCase;

    ObjectMapper mapper = new ObjectMapper();

    @KafkaListener(topics = "new-tweet-topic", groupId = "my-tweet-consumer-group")
    public void receiveMessageToNewTweet(String message) {

        try {
            Tweet tweet = mapper.readValue(message, Tweet.class);
            createTweetUseCase.accept(tweet);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

    @KafkaListener(topics = "add-like-topic", groupId = "my-tweet-consumer-group")
    public void receiveMessageToAddLike(String message) {

        try {
            AddLikeDto addLikeDto = mapper.readValue(message, AddLikeDto.class);
            addLikeOrDislikeUseCase.accept(addLikeDto.getTweetId(), addLikeDto.getLike());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }


    }

    @KafkaListener(topics = "add-reply-topic", groupId = "my-tweet-consumer-group")
    public void receiveMessageToAddReply(String message) {

        try {
            AddReplayDto addReplayDto = mapper.readValue(message, AddReplayDto.class);
            addReplyUseCase.accept(addReplayDto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }


    }

    @KafkaListener(topics = "add-retweet-topic", groupId = "my-tweet-consumer-group")
    public void receiveMessageToAddRetweet(String message) {

        try {
            AddRetweetDTO addRetweetDTO = mapper.readValue(message, AddRetweetDTO.class);
            addRetweetUseCase.accept(addRetweetDTO);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
