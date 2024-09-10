package com.twittercasero.tweets.infrastructure.controllers;

import com.twittercasero.tweets.application.useCases.GetTweetByHashtagUseCase;
import com.twittercasero.tweets.application.useCases.GetTweetByIdUseCase;
import com.twittercasero.tweets.application.useCases.GetTweetByMentionsUseCase;
import com.twittercasero.tweets.application.useCases.GetTweetByOwnersUseCase;
import com.twittercasero.tweets.domain.entities.Tweet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tweets")
public class TweetController {

    @Autowired
    private GetTweetByOwnersUseCase getTweetByOwnersUseCase;

    @Autowired
    private GetTweetByHashtagUseCase getTweetByHashtagUseCase;

    @Autowired
    private GetTweetByMentionsUseCase getTweetByMentionsUseCase;

    @Autowired
    private GetTweetByIdUseCase getTweetByIdUseCase;

    @GetMapping()
    public ResponseEntity<List<Tweet>> getTweetByOwners(@RequestParam List<String> owners,
                                                        @RequestParam(defaultValue = "1") int page,
                                                        @RequestParam(defaultValue = "25") int size) {

        List<Tweet> tweets = getTweetByOwnersUseCase.apply(owners, PageRequest.of(page - 1, size));

        if (tweets != null) {
            return ResponseEntity.ok(tweets);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{tweetId}")
    public ResponseEntity<Tweet> getTweetById(@PathVariable String tweetId) {

        Tweet tweets = getTweetByIdUseCase.apply(tweetId);

        if (tweets != null) {
            return ResponseEntity.ok(tweets);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{nickname}/mentions")
    public ResponseEntity<List<Tweet>> getTweetByMention(@PathVariable String nickname,
                                                         @RequestParam(defaultValue = "1") int page,
                                                         @RequestParam(defaultValue = "25") int size) {

        List<Tweet> tweets = getTweetByMentionsUseCase.apply(nickname, PageRequest.of(page - 1, size));

        if (tweets != null) {
            return ResponseEntity.ok(tweets);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{hashtag}/hashtag")
    public ResponseEntity<List<Tweet>> getTweetByHashtag(@PathVariable String hashtag,
                                                         @RequestParam(defaultValue = "1") int page,
                                                         @RequestParam(defaultValue = "25") int size) {

        List<Tweet> tweets = getTweetByHashtagUseCase.apply(hashtag, PageRequest.of(page - 1, size));

        if (tweets != null) {
            return ResponseEntity.ok(tweets);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
