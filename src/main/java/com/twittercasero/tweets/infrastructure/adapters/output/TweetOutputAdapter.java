package com.twittercasero.tweets.infrastructure.adapters.output;

import com.twittercasero.tweets.application.port.output.TweetOutputPort;
import com.twittercasero.tweets.domain.entities.Tweet;
import com.twittercasero.tweets.domain.exceptions.TweetNotFoundException;
import com.twittercasero.tweets.infrastructure.repositories.TweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TweetOutputAdapter implements TweetOutputPort {

    @Autowired
    private TweetRepository tweetRepository;

    @Override
    public Tweet findById(String tweetId) {
        return tweetRepository.findById(tweetId)
                .orElseThrow(() -> new TweetNotFoundException("Tweet not found with nickName: " + tweetId));
    }

    @Override
    public List<Tweet> findByHashtag(String hashtag, Pageable pageable) {
        return tweetRepository.findByHashtagsContaining(hashtag, pageable).getContent();
    }

    @Override
    public List<Tweet> findByMention(String mention, Pageable pageable) {
        return tweetRepository.findByMentionsContaining(mention, pageable).getContent();
    }

    @Override
    public List<Tweet> findByOwners(List<String> owners, Pageable pageable) {
        return tweetRepository.findByOwnerInOrderByPostedDesc(owners, pageable).getContent();
    }
}
