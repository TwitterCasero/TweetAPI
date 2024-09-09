package com.twittercasero.tweets.infrastructure.message.consumer;

import com.twittercasero.tweets.application.dto.AddLikeDto;
import com.twittercasero.tweets.application.dto.AddReplayDto;
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

    @KafkaListener(topics = "new-tweet-topic", groupId = "my-tweet-consumer-group")
    public void receiveMessageToNewTweet(Tweet tweet) {

        createTweetUseCase.accept(tweet);
    }

    @KafkaListener(topics = "add-like-topic", groupId = "my-tweet-consumer-group")
    public void receiveMessageToAddLike(AddLikeDto message) {

        addLikeOrDislikeUseCase.accept(message.getTweetId(), message.getLike());
    }

    @KafkaListener(topics = "add-reply-topic", groupId = "my-tweet-consumer-group")
    public void receiveMessageToAddReply(AddReplayDto message) {

        addReplyUseCase.accept(message.getTweetId(), message.getReply());
    }

    @KafkaListener(topics = "add-retweet-topic", groupId = "my-tweet-consumer-group")
    public void receiveMessageToAddRetweet(String message) {

        addRetweetUseCase.accept(message);
    }

}
