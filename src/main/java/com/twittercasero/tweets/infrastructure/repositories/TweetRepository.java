package com.twittercasero.tweets.infrastructure.repositories;

import com.twittercasero.tweets.domain.entities.Tweet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TweetRepository extends MongoRepository<Tweet, String> {

    Page<Tweet> findByMentionsContaining(String mentions, Pageable pageable);

    Page<Tweet> findByHashtagsContaining(String Hashtags, Pageable pageable);

    Page<Tweet> findByOwnerInOrderByPostedDesc(List<String> owners, Pageable pageable);

}
