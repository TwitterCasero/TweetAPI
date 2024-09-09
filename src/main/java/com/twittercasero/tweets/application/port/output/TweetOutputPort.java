package com.twittercasero.tweets.application.port.output;

import com.twittercasero.tweets.domain.entities.Tweet;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TweetOutputPort {

    Tweet findById(String tweetId);

    List<Tweet> findByHashtag(String hashtag, Pageable pageable);

    List<Tweet> findByMention(String mention, Pageable pageable);

    List<Tweet> findByOwners(List<String> owners, Pageable pageable);
}

