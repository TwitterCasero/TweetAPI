package com.twittercasero.tweets.infrastructure.adapters.input;

import com.twittercasero.tweets.application.port.input.TweetInputPort;
import com.twittercasero.tweets.domain.entities.Tweet;
import com.twittercasero.tweets.infrastructure.repositories.TweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TweetInputAdapter implements TweetInputPort {

    @Autowired
    private TweetRepository tweetRepository;

    @Override
    public void save(Tweet tweet) {
        tweetRepository.save(tweet);
    }
}
